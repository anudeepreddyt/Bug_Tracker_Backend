package com.anudeepreddy.Bug_Tracker.Service;

import com.anudeepreddy.Bug_Tracker.DTO.CreateProjectDTO;
import com.anudeepreddy.Bug_Tracker.DTO.DisplayProjectDTO;
import com.anudeepreddy.Bug_Tracker.Entity.ProjectEntity;
import com.anudeepreddy.Bug_Tracker.Exception.CommonException;
import com.anudeepreddy.Bug_Tracker.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public DisplayProjectDTO createProject(String username, CreateProjectDTO createProjectDTO) {
        createProjectDTO.setCreatedBy(username);
        ProjectEntity projectEntity=new ProjectEntity();
        projectEntity.setProjectName(createProjectDTO.getProjectName());
        projectEntity.setDescription(createProjectDTO.getDescription());
        projectEntity.setCreatedBy(createProjectDTO.getCreatedBy());
        projectRepository.save(projectEntity);
        return new DisplayProjectDTO(
                projectEntity.getId(),
                projectEntity.getProjectName(),
                projectEntity.getDescription(),
                projectEntity.getCreatedBy(),
                projectEntity.getCreatedAt()
        );
    }

    public String deleteProject(Integer id) {
        ProjectEntity projectEntity=projectRepository.findById(id).orElseThrow(()->new CommonException("Project with this id can't be found!!!"));
        projectRepository.delete(projectEntity);
        return "Project deleted successfully...";
    }

    public DisplayProjectDTO getProjectById(Integer id) {
        ProjectEntity projectEntity=projectRepository.findById(id).orElseThrow(()->new CommonException("Project with this id can't be found!!!"));
        return new DisplayProjectDTO(
                projectEntity.getId(),
                projectEntity.getProjectName(),
                projectEntity.getDescription(),
                projectEntity.getCreatedBy(),
                projectEntity.getCreatedAt()
        );
    }

    public DisplayProjectDTO getProjectByName(String projectName) {
        ProjectEntity projectEntity=projectRepository.findByProjectName(projectName).orElseThrow(()->new CommonException("Project with this name can't be found!!!!"));
        return new DisplayProjectDTO(
                projectEntity.getId(),
                projectEntity.getProjectName(),
                projectEntity.getDescription(),
                projectEntity.getCreatedBy(),
                projectEntity.getCreatedAt()
        );
    }




}
