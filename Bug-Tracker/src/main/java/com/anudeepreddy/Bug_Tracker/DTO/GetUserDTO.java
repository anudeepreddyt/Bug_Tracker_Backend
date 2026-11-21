package com.anudeepreddy.Bug_Tracker.DTO;

import com.anudeepreddy.Bug_Tracker.Common.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserDTO {

    private Integer userId;
    private String username;
    private String email;
    private Boolean isActive;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
