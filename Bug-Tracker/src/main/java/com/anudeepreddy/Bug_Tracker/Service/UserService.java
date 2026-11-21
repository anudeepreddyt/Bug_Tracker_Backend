package com.anudeepreddy.Bug_Tracker.Service;

import com.anudeepreddy.Bug_Tracker.Common.Role;
import com.anudeepreddy.Bug_Tracker.DTO.GetUserDTO;
import com.anudeepreddy.Bug_Tracker.DTO.SignInDTO;
import com.anudeepreddy.Bug_Tracker.DTO.SignUpDTO;
import com.anudeepreddy.Bug_Tracker.Entity.BlacklistedTokenEntity;
import com.anudeepreddy.Bug_Tracker.Entity.UsersEntity;
import com.anudeepreddy.Bug_Tracker.Exception.AlreadyExistException;
import com.anudeepreddy.Bug_Tracker.Exception.CommonException;
import com.anudeepreddy.Bug_Tracker.Exception.UserNotFoundException;
import com.anudeepreddy.Bug_Tracker.Repository.BlacklistedTokenRepository;
import com.anudeepreddy.Bug_Tracker.Repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private BlacklistedTokenRepository blacklistedTokenRepository;

    public String signUp(@Valid SignUpDTO signUpDTO, Role role) {
        if(userRepository.existsByUsername(signUpDTO.getUsername()))
        {
         throw  new AlreadyExistException("username already in use!!!");
        }
        if (userRepository.existsByEmail(signUpDTO.getEmail()))
        {
            throw new AlreadyExistException("email already in use!!!");
        }
        UsersEntity usersEntity=new UsersEntity();
        usersEntity.setUsername(signUpDTO.getUsername());
        usersEntity.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        usersEntity.setEmail(signUpDTO.getEmail());
        usersEntity.setRole(role);
        userRepository.save(usersEntity);
        return "User created with username "+signUpDTO.getUsername()+" with "+role+" role.";
    }

    public GetUserDTO getUser(String username) {
        UsersEntity usersEntity=userRepository.findByUsername(username).orElseThrow(()->new AlreadyExistException("User not found!!!"));
        GetUserDTO getUserDTO=new GetUserDTO();
        getUserDTO.setUserId(usersEntity.getId());
        getUserDTO.setUsername(usersEntity.getUsername());
        getUserDTO.setEmail(usersEntity.getEmail());
        getUserDTO.setCreatedAt(usersEntity.getCreatedAt());
        getUserDTO.setUpdatedAt(usersEntity.getUpdatedAt());
        getUserDTO.setRole(usersEntity.getRole());
        getUserDTO.setIsActive(usersEntity.getIsActive());
        return getUserDTO;
    }

    public String deleteUser(String username) {
        UsersEntity usersEntity=userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException("User not found!!!"));
        userRepository.delete(usersEntity);
        return username+" successfully deleted...";
    }

    public String updatePassword(String username, String newPassword) {
        UsersEntity usersEntity=userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException("User not found!!!"));
        if (usersEntity.getPassword().equals(newPassword)){
            throw new CommonException("Old password and new password should be same!!!");
        }
        usersEntity.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(usersEntity);
        return "Password updated successfully for username "+usersEntity.getUsername();
    }

    public String updateDetails(String username, String newUsername, String newEmail, String newPassword) {
        UsersEntity usersEntity=userRepository.findByUsername(username).orElseThrow(()->new CommonException("User not found!!!"));
        if (newUsername!=null && !newUsername.isEmpty()){
            if(userRepository.existsByUsername(newUsername))
            {
                throw new AlreadyExistException("new username already existed try another one!!!");
            }
           usersEntity.setUsername(newUsername);
        }
        if (newEmail!=null && !newEmail.isEmpty()){
            usersEntity.setEmail(newEmail);
        }
        if (newPassword!=null && !newPassword.isEmpty()){
            usersEntity.setPassword(newPassword);
        }
        userRepository.save(usersEntity);
        return "Details updated successfully for username ";
    }

    public String signIn(SignInDTO signInDTO) {

        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDTO.getUsername(),signInDTO.getPassword()));
        if (authentication.isAuthenticated()){
            String authToken=jwtService.generateToken(signInDTO.getUsername());
            return authToken;
        }
        else{
            throw new CommonException("Unable to login!!!");
        }
    }



    public String signOut(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (!blacklistedTokenRepository.existsByToken(token)) {
                BlacklistedTokenEntity entity = new BlacklistedTokenEntity();
                entity.setToken(token);
                blacklistedTokenRepository.save(entity);
            }
        }
        return "Signed out successfully.";
    }
}
