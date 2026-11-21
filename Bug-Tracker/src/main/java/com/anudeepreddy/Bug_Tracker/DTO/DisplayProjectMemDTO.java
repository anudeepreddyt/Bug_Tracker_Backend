package com.anudeepreddy.Bug_Tracker.DTO;

import com.anudeepreddy.Bug_Tracker.Common.ProjectRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisplayProjectMemDTO {

    private Integer userId;
    private String  userName;
    private Integer projectId;
    private String projectName;
    private LocalDateTime joinedAt;
    private ProjectRole projectRole;

}
