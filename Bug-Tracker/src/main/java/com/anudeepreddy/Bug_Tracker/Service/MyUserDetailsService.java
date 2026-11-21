package com.anudeepreddy.Bug_Tracker.Service;

import com.anudeepreddy.Bug_Tracker.Entity.UserPrincipal;
import com.anudeepreddy.Bug_Tracker.Entity.UsersEntity;
import com.anudeepreddy.Bug_Tracker.Exception.CommonException;
import com.anudeepreddy.Bug_Tracker.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity usersEntity=userRepository.findByUsername(username).orElseThrow(()->new CommonException("User not found!!!"));
        return new UserPrincipal(usersEntity);
    }
}
