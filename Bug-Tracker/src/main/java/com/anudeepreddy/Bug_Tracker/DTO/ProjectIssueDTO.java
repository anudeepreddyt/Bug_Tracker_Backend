package com.anudeepreddy.Bug_Tracker.DTO;

import com.anudeepreddy.Bug_Tracker.Common.Priority;
import com.anudeepreddy.Bug_Tracker.Common.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectIssueDTO {


    private Integer issueId;
    private Integer reporterId;
    private String reporterName;
    private Integer assigneeId;
    private String assigneeName;
    private String issueTitle;
    private String issueDescription;
    private Status issueStatus;
    private Priority issuePriority;

}


