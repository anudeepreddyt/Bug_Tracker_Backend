package com.anudeepreddy.Bug_Tracker.Controller;


import com.anudeepreddy.Bug_Tracker.Common.Role;
import com.anudeepreddy.Bug_Tracker.DTO.GetUserDTO;
import com.anudeepreddy.Bug_Tracker.DTO.SignInDTO;
import com.anudeepreddy.Bug_Tracker.DTO.SignUpDTO;
import com.anudeepreddy.Bug_Tracker.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/healthcheck")
    public ResponseEntity<String> healthCheck(){
        return ResponseEntity.ok("Servers Up...");
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpDTO signUpDTO, @RequestParam Role role){
        return ResponseEntity.ok(userService.signUp(signUpDTO,role));
    }

    @GetMapping("/getuser")
    public ResponseEntity<GetUserDTO> getUser(Authentication authentication){
        String username=authentication.getName();
        return ResponseEntity.ok(userService.getUser(username));
    }

    @DeleteMapping("/deleteuser")
    public ResponseEntity<String> deleteUser(Authentication authentication){
        String username=authentication.getName();
        return ResponseEntity.ok(userService.deleteUser(username));
    }

    @PatchMapping("/updatepassword")
    public ResponseEntity<String> updatePassword(Authentication authentication,@RequestParam String newPassword){
        String username=authentication.getName();
        return ResponseEntity.ok(userService.updatePassword(username,newPassword));
    }

    @PutMapping("/updatedetails")
    public ResponseEntity<String> updateDetails(Authentication authentication,@RequestParam(required = false)String newUsername,@RequestParam(required = false) String newEmail,@RequestParam(required = false) String newPassword){
        String username=authentication.getName();
        return ResponseEntity.ok(userService.updateDetails(username,newUsername,newEmail,newPassword));
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody SignInDTO signInDTO){
        return ResponseEntity.ok(userService.signIn(signInDTO));
    }

    @DeleteMapping("/signout")
    public ResponseEntity<String> signOut(@RequestHeader("Authorization")String authHeader){
        return ResponseEntity.ok(userService.signOut(authHeader));
    }


}
