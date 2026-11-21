package com.anudeepreddy.Bug_Tracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.DocFlavor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDetails
{
    private String username;
    private String email;
    private String password;

}
