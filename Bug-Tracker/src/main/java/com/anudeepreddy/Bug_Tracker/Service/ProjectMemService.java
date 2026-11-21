package com.anudeepreddy.Bug_Tracker.Service;

import com.anudeepreddy.Bug_Tracker.Common.ProjectRole;
import com.anudeepreddy.Bug_Tracker.DTO.DeleteProjectMemDTO;
import com.anudeepreddy.Bug_Tracker.DTO.DisplayProjectMemDTO;
import com.anudeepreddy.Bug_Tracker.DTO.ProjectMemDTO;
import com.anudeepreddy.Bug_Tracker.Entity.ProjectEntity;
import com.anudeepreddy.Bug_Tracker.Entity.ProjectMemEntity;
import com.anudeepreddy.Bug_Tracker.Entity.UsersEntity;
import com.anudeepreddy.Bug_Tracker.Exception.AlreadyExistException;
import com.anudeepreddy.Bug_Tracker.Exception.CommonException;
import com.anudeepreddy.Bug_Tracker.Repository.ProjectMemRepository;
import com.anudeepreddy.Bug_Tracker.Repository.ProjectRepository;
import com.anudeepreddy.Bug_Tracker.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectMemService {

    @Autowired
    private ProjectMemRepository projectMemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Transactional
    public DisplayProjectMemDTO addProjectMem(ProjectMemDTO projectMemDTO, ProjectRole projectRole) {

        UsersEntity usersEntity=userRepository.findById(projectMemDTO.getUserid()).orElseThrow(()->new CommonException("User not found with this id!!!"));
        ProjectEntity projectEntity=projectRepository.findById(projectMemDTO.getProjectid()).orElseThrow(()->new CommonException("Project with id is not found!!!"));

        boolean existing=projectMemRepository.existsByUsersEntity_IdAndProjectEntity_Id(
                projectMemDTO.getUserid(),projectMemDTO.getProjectid()
        );
        if (existing) throw new AlreadyExistException("User already a member of this project!!!");

        ProjectMemEntity projectMemEntity=new ProjectMemEntity();
        projectMemEntity.setProjectRole(projectRole);
        projectMemEntity.setUsersEntity(usersEntity);
        projectMemEntity.setProjectEntity(projectEntity);
        projectMemEntity.setUserName(usersEntity.getUsername());
        projectMemEntity.setProjectName(projectEntity.getProjectName());

        projectMemRepository.save(projectMemEntity);

        return new DisplayProjectMemDTO(
                projectMemDTO.getUserid(),
                usersEntity.getUsername(),
                projectMemDTO.getProjectid(),
                projectEntity.getProjectName(),
                projectMemEntity.getJoinedAt(),
                projectMemEntity.getProjectRole()
        );
    }


    @Transactional
    public String deleteProjectMem(DeleteProjectMemDTO deleteProjectMemDTO) {
        ProjectMemEntity projectMemEntity=projectMemRepository.findByUsersEntity_IdAndProjectEntity_Id(deleteProjectMemDTO.getUserId(),deleteProjectMemDTO.getProjectId()).orElseThrow(()->new CommonException("Unable to project and connected user!!! "));
        projectMemRepository.delete(projectMemEntity);
        return "User deleted from the project...";
    }


}
