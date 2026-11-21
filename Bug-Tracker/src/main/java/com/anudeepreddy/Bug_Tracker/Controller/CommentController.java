package com.anudeepreddy.Bug_Tracker.Controller;

import com.anudeepreddy.Bug_Tracker.DTO.AddCommentDTO;
import com.anudeepreddy.Bug_Tracker.DTO.DisplayCommentDTO;
import com.anudeepreddy.Bug_Tracker.DTO.ProjectIssueDTO;
import com.anudeepreddy.Bug_Tracker.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/addcomment")
    public ResponseEntity<DisplayCommentDTO> addComment(@RequestParam Integer projectId, @RequestParam Integer issueId, @RequestBody AddCommentDTO addCommentDTO){
        return ResponseEntity.ok(commentService.addComment(projectId,issueId,addCommentDTO));
    }

    @GetMapping("/projectcomments")
    public ResponseEntity<List<DisplayCommentDTO>> projecComments(@RequestParam Integer projectId){
        return ResponseEntity.ok(commentService.projectComments(projectId));
    }

    @GetMapping("/getallcomments")
    public ResponseEntity<List<DisplayCommentDTO>> allComments(){
        return ResponseEntity.ok(commentService.allComments());
    }

    @DeleteMapping("/deletecomment")
    public ResponseEntity<String> deleteComment(@RequestParam Integer commentId){
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }

    @PatchMapping("/editcomment")
    public ResponseEntity<DisplayCommentDTO> editComment(@RequestParam Integer commentId,String newComment){
        return ResponseEntity.ok(commentService.editComment(commentId,newComment));
    }

}
