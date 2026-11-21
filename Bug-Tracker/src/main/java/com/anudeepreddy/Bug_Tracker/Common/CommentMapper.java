package com.anudeepreddy.Bug_Tracker.Common;

import com.anudeepreddy.Bug_Tracker.DTO.DisplayCommentDTO;
import com.anudeepreddy.Bug_Tracker.Entity.CommentEntity;
import com.anudeepreddy.Bug_Tracker.Entity.IssueEntity;
import com.anudeepreddy.Bug_Tracker.Entity.ProjectEntity;
import com.anudeepreddy.Bug_Tracker.Repository.IssueRepository;
import com.anudeepreddy.Bug_Tracker.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentMapper {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private IssueRepository issueRepository;

    public DisplayCommentDTO toDTO(CommentEntity commentEntity){
        DisplayCommentDTO displayCommentDTO=new DisplayCommentDTO();
        ProjectEntity projectEntity=projectRepository.getReferenceById(commentEntity.getProjectEntity().getId());
        IssueEntity issueEntity=issueRepository.getReferenceById(commentEntity.getIssueEntity().getId());
        displayCommentDTO.setProjectId(projectEntity.getId());
        displayCommentDTO.setProjectName(projectEntity.getProjectName());
        displayCommentDTO.setIssueId(issueEntity.getId());
        displayCommentDTO.setIssueTitle(issueEntity.getIssueTitle());
        displayCommentDTO.setIssueDescription(issueEntity.getIssueDescription());
        displayCommentDTO.setComment(commentEntity.getComment());
        return displayCommentDTO;

    }
    public List<DisplayCommentDTO> toDTOList(List<CommentEntity> commentEntities){
        return commentEntities.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
