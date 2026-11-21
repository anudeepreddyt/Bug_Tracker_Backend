package com.anudeepreddy.Bug_Tracker.Service;

import com.anudeepreddy.Bug_Tracker.Common.CommentMapper;
import com.anudeepreddy.Bug_Tracker.DTO.AddCommentDTO;
import com.anudeepreddy.Bug_Tracker.DTO.DisplayCommentDTO;
import com.anudeepreddy.Bug_Tracker.Entity.CommentEntity;
import com.anudeepreddy.Bug_Tracker.Entity.IssueEntity;
import com.anudeepreddy.Bug_Tracker.Entity.ProjectEntity;
import com.anudeepreddy.Bug_Tracker.Exception.CommonException;
import com.anudeepreddy.Bug_Tracker.Repository.CommentRepository;
import com.anudeepreddy.Bug_Tracker.Repository.IssueRepository;
import com.anudeepreddy.Bug_Tracker.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentMapper commentMapper;

    public DisplayCommentDTO addComment(Integer projectId, Integer issueId, AddCommentDTO addCommentDTO) {
        ProjectEntity projectEntity=projectRepository.getReferenceById(projectId);
        if (projectEntity==null){
            throw new CommonException("Project can't be found with this project id");
        }
        IssueEntity issueEntity=issueRepository.getReferenceById(issueId);
        if (issueEntity==null){
            throw new CommonException("issue can't be found with this issue id");
        }
        CommentEntity commentEntity=new CommentEntity();
        commentEntity.setComment(addCommentDTO.getComment());
        commentEntity.setProjectEntity(projectEntity);
        commentEntity.setIssueEntity(issueEntity);
        CommentEntity newCommentEntity=commentRepository.save(commentEntity);
        return new DisplayCommentDTO(
                newCommentEntity.getId(),
                projectId,
                projectEntity.getProjectName(),
                issueId,
                issueEntity.getIssueTitle(),
                issueEntity.getIssueDescription(),
                addCommentDTO.getComment()
        );
    }


    public List<DisplayCommentDTO> projectComments(Integer projectId) {
       return commentMapper.toDTOList(commentRepository.findByProjectEntity_Id (projectId));
    }

    public List<DisplayCommentDTO> allComments() {
            return commentMapper.toDTOList(commentRepository.findAll());
    }

    public String deleteComment(Integer commentId) {
        CommentEntity commentEntity=commentRepository.getReferenceById(commentId);
        commentRepository.delete(commentEntity);
        return "Comment deleted successfully...";
    }

    public DisplayCommentDTO editComment(Integer commentId, String newComment) {
        CommentEntity commentEntity=commentRepository.getReferenceById(commentId);
        if (newComment!=null && !newComment.isBlank()){
            commentEntity.setComment(newComment);
        }
        commentRepository.save(commentEntity);
        CommentEntity newCommentEntity=commentRepository.getReferenceById(commentId);
        return new DisplayCommentDTO(
                newCommentEntity.getId(),
                newCommentEntity.getProjectEntity().getId(),
                newCommentEntity.getProjectEntity().getProjectName(),
                newCommentEntity.getIssueEntity().getId(),
                newCommentEntity.getIssueEntity().getIssueTitle(),
                newCommentEntity.getIssueEntity().getIssueDescription(),
                newCommentEntity.getComment()
        );

    }
}