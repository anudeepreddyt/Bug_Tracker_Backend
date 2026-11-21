package com.anudeepreddy.Bug_Tracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInDTO {
    
    private String username;
    private String password;
}
