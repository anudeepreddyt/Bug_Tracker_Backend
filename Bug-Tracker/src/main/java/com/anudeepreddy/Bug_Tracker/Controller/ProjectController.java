package com.anudeepreddy.Bug_Tracker.Controller;

import com.anudeepreddy.Bug_Tracker.DTO.CreateProjectDTO;
import com.anudeepreddy.Bug_Tracker.DTO.DisplayProjectDTO;
import com.anudeepreddy.Bug_Tracker.Repository.ProjectRepository;
import com.anudeepreddy.Bug_Tracker.Service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProjectController {
    @Autowired
    private ProjectService projectService;



    @PostMapping("/createproject")
    public ResponseEntity<DisplayProjectDTO> createProject(Authentication authentication, @RequestBody CreateProjectDTO createProjectDTO){
        String username=authentication.getName();
        return ResponseEntity.ok(projectService.createProject(username,createProjectDTO));
    }

    @DeleteMapping("/deleteproject")
    public ResponseEntity<String> deleteProject(@RequestParam Integer id){
        return ResponseEntity.ok(projectService.deleteProject(id));
    }

    @GetMapping("/getprojectbyid")
    public ResponseEntity<DisplayProjectDTO> displayProjectById(@RequestParam Integer id){
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @GetMapping("/getprojectbyname")
    public ResponseEntity<DisplayProjectDTO> displayProjectByName(@RequestParam String projectName){
        return ResponseEntity.ok(projectService.getProjectByName(projectName));
    }

}
