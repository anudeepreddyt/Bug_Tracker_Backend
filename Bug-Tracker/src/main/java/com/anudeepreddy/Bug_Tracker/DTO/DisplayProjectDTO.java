package com.anudeepreddy.Bug_Tracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisplayProjectDTO {

    private Integer projectId;
    private String projectName;
    private String description;
    private String createdBy;
    private LocalDateTime createAt;


}
