package com.anudeepreddy.Bug_Tracker.Controller;

import com.anudeepreddy.Bug_Tracker.Common.ProjectRole;
import com.anudeepreddy.Bug_Tracker.DTO.DeleteProjectMemDTO;
import com.anudeepreddy.Bug_Tracker.DTO.DisplayProjectMemDTO;
import com.anudeepreddy.Bug_Tracker.DTO.ProjectMemDTO;
import com.anudeepreddy.Bug_Tracker.Service.ProjectMemService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProjectMemController {

    @Autowired
    private ProjectMemService projectMemService;

    @PostMapping("/addprojectmem")
    public ResponseEntity<DisplayProjectMemDTO> addProjectMem(@RequestBody ProjectMemDTO projectMemDTO, @RequestParam ProjectRole projectRole){
        return ResponseEntity.ok(projectMemService.addProjectMem(projectMemDTO,projectRole));
    }

    @DeleteMapping("/deleteprojectmem")
    public ResponseEntity<String> deleteProjectMem(@RequestBody DeleteProjectMemDTO deleteProjectMemDTO){
        return ResponseEntity.ok(projectMemService.deleteProjectMem(deleteProjectMemDTO));

    }
    
}
