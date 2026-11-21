package com.anudeepreddy.Bug_Tracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisplayCommentDTO {

    private Integer commentId;
    private Integer projectId;
    private String projectName;
    private Integer issueId;
    private String issueTitle;
    private String issueDescription;
    private String comment;
}
