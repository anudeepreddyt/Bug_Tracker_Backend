package com.anudeepreddy.Bug_Tracker.DTO;

import com.anudeepreddy.Bug_Tracker.Common.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.AnyKeyJavaClass;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {

    @NotBlank
    @Pattern(regexp ="^[a-z]+$",message = "username should contain only lowercase letters")
    private String username;
    @NotBlank(message = "password shouldn't be null")
    private String password;
    @NotBlank(message = "email shouldn't be blank")
    private String email;

}
