package com.anudeepreddy.Bug_Tracker.Entity;


import com.anudeepreddy.Bug_Tracker.Common.Priority;
import com.anudeepreddy.Bug_Tracker.Common.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class IssueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String issueTitle;
    private String issueDescription;
    private Status issueStatus;
    private Priority issuePriority;
    private String projectName;
    private Integer projectId;
    private String reporterName;
    private Integer reportedId;
    private String assigneeName;
    private Integer assigneeId;
    private LocalDateTime issueCreatedAt;
    private LocalDateTime issueupdatedAt;

    @PrePersist
    protected void onCreated(){
        this.issueCreatedAt= LocalDateTime.now();
        this.issueupdatedAt=LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdated(){
        this.issueupdatedAt=LocalDateTime.now();
    }
}
