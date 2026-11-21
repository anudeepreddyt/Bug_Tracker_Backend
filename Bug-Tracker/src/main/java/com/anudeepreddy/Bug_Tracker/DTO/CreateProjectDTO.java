package com.anudeepreddy.Bug_Tracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProjectDTO {

    private String projectName;
    private String createdBy;
    private String description;
}
