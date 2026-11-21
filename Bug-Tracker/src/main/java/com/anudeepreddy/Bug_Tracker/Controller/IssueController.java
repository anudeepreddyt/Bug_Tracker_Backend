package com.anudeepreddy.Bug_Tracker.Controller;

import com.anudeepreddy.Bug_Tracker.Common.Priority;
import com.anudeepreddy.Bug_Tracker.Common.Status;
import com.anudeepreddy.Bug_Tracker.DTO.ChangeIssueDTO;
import com.anudeepreddy.Bug_Tracker.DTO.CreateIssueDTO;
import com.anudeepreddy.Bug_Tracker.DTO.DisplayIssuseDTO;
import com.anudeepreddy.Bug_Tracker.DTO.ProjectIssueDTO;
import com.anudeepreddy.Bug_Tracker.Service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.renderable.RenderableImageProducer;
import java.util.List;

@RestController
@RequestMapping("/api")
public class IssueController {


    @Autowired
    private IssueService issueService;

    @PostMapping("/createissue")
    public ResponseEntity<DisplayIssuseDTO> createIssue(@RequestBody CreateIssueDTO createIssueDTO, @RequestParam Status status, @RequestParam Priority priority){
        return ResponseEntity.ok(issueService.createIssue(createIssueDTO,status,priority));
    }

    @PutMapping("/changeissue")
    public ResponseEntity<DisplayIssuseDTO> changeIssue(@RequestBody ChangeIssueDTO changeIssueDTO,@RequestParam Status status,@RequestParam Priority priority){
        return ResponseEntity.ok(issueService.changeIssue(changeIssueDTO,status,priority));
    }

    @PatchMapping("/changestatus")
    public ResponseEntity<String> changeStatus(@RequestParam Integer issueId,@RequestParam Status status){
        return ResponseEntity.ok(issueService.changeStatus(issueId,status));
    }

    @PatchMapping("/changepriority")
    public ResponseEntity<String> changePriority(@RequestParam Integer issueId,@RequestParam Priority priority){
        return ResponseEntity.ok(issueService.changePriority(issueId, priority));
    }

    @DeleteMapping("/deleteissue")
    public ResponseEntity<String> deleteIssue(@RequestParam Integer issueId){
        return ResponseEntity.ok(issueService.deleteIssue(issueId));
    }

    @GetMapping("/getprojectissue")
    public ResponseEntity<List<ProjectIssueDTO>> getprojectissue(@RequestParam Integer projectId){
        return ResponseEntity.ok(issueService.getProjectIssues(projectId));

    }
}
