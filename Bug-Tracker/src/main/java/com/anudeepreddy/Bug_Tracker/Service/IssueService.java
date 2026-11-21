package com.anudeepreddy.Bug_Tracker.Service;

import com.anudeepreddy.Bug_Tracker.Common.IssueMapper;
import com.anudeepreddy.Bug_Tracker.Common.Priority;
import com.anudeepreddy.Bug_Tracker.Common.Status;
import com.anudeepreddy.Bug_Tracker.DTO.ChangeIssueDTO;
import com.anudeepreddy.Bug_Tracker.DTO.CreateIssueDTO;
import com.anudeepreddy.Bug_Tracker.DTO.DisplayIssuseDTO;
import com.anudeepreddy.Bug_Tracker.DTO.ProjectIssueDTO;
import com.anudeepreddy.Bug_Tracker.Entity.IssueEntity;
import com.anudeepreddy.Bug_Tracker.Entity.ProjectEntity;
import com.anudeepreddy.Bug_Tracker.Entity.UsersEntity;
import com.anudeepreddy.Bug_Tracker.Exception.CommonException;
import com.anudeepreddy.Bug_Tracker.Repository.IssueRepository;
import com.anudeepreddy.Bug_Tracker.Repository.ProjectRepository;
import com.anudeepreddy.Bug_Tracker.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private IssueMapper issueMapper;

    @Transactional
    public DisplayIssuseDTO createIssue(CreateIssueDTO createIssueDTO, Status status, Priority priority) {
        ProjectEntity projectEntity=projectRepository.findById(createIssueDTO.getProjectId()).orElseThrow(()->new CommonException("Project unable to find with this project id"));
        UsersEntity reporterEntity=userRepository.findById(createIssueDTO.getUserId()).orElseThrow(()->new CommonException("Reporter can't be found with this id"));
        UsersEntity assigneeEntity=userRepository.findById(createIssueDTO.getAssigneeId()).orElseThrow(()->new CommonException("Assignee can't be found with this id"));
        IssueEntity issueEntity=new IssueEntity();

        issueEntity.setProjectId(createIssueDTO.getProjectId());
        issueEntity.setReportedId(createIssueDTO.getUserId());
        issueEntity.setProjectName(projectEntity.getProjectName());
        issueEntity.setReporterName(reporterEntity.getUsername());
        issueEntity.setAssigneeId(createIssueDTO.getAssigneeId());
        issueEntity.setAssigneeName(assigneeEntity.getUsername());
        issueEntity.setIssueTitle(createIssueDTO.getIssueTitle());
        issueEntity.setIssueDescription(createIssueDTO.getIssueDescription());
        issueEntity.setIssueStatus(status);
        issueEntity.setIssuePriority(priority);

        issueRepository.save(issueEntity);

        return new DisplayIssuseDTO(
                issueEntity.getProjectId(),
                issueEntity.getProjectName(),
                issueEntity.getReportedId(),
                issueEntity.getReporterName(),
                issueEntity.getAssigneeId(),
                issueEntity.getAssigneeName(),
                issueEntity.getIssueTitle(),
                issueEntity.getIssueDescription(),
                issueEntity.getIssueStatus(),
                issueEntity.getIssuePriority()
        );


    }

    @Transactional
    public DisplayIssuseDTO changeIssue(ChangeIssueDTO changeIssueDTO, Status status, Priority priority) {
        IssueEntity issueEntity = issueRepository.findById(changeIssueDTO.getIssueId())
                .orElseThrow(() -> new CommonException("Issue not found: " + changeIssueDTO.getIssueId()));
        if (changeIssueDTO.getIssueTitle()!=null && !changeIssueDTO.getIssueTitle().isEmpty()){
            issueEntity.setIssueTitle(changeIssueDTO.getIssueTitle());
        }
        if (changeIssueDTO.getIssueDescription()!=null && !changeIssueDTO.getIssueDescription().isEmpty()){
            issueEntity.setIssueDescription(changeIssueDTO.getIssueDescription());
        }
        if (status!=null){
            issueEntity.setIssueStatus(status);
        }
        if (priority!=null){
            issueEntity.setIssuePriority(priority);
        }
        if (changeIssueDTO.getAssigneeId()!=null){
            issueEntity.setAssigneeId(changeIssueDTO.getAssigneeId());
            UsersEntity newAssignee=userRepository.getById(changeIssueDTO.getAssigneeId());
            issueEntity.setAssigneeName(newAssignee.getUsername());
        }

        issueRepository.save(issueEntity);

        return new DisplayIssuseDTO(
                issueEntity.getProjectId(),
                issueEntity.getProjectName(),
                issueEntity.getReportedId(),
                issueEntity.getReporterName(),
                issueEntity.getAssigneeId(),
                issueEntity.getAssigneeName(),
                issueEntity.getIssueTitle(),
                issueEntity.getIssueDescription(),
                issueEntity.getIssueStatus(),
                issueEntity.getIssuePriority()
        );
    }


    public String changeStatus(Integer issueId, Status status) {
        IssueEntity issueEntity = issueRepository.findById(issueId)
                .orElseThrow(() -> new CommonException("Issue not found: " + issueId));
        String oldStatus=null;
        String newStatus=null;
        if (status!=null){
             oldStatus= issueEntity.getIssueStatus().toString();
            issueEntity.setIssueStatus(status);
             newStatus= issueEntity.getIssueStatus().toString();
        }
        return "Issue status successfully changed from "+oldStatus+" to "+newStatus;
    }

    public String changePriority(Integer issueId, Priority priority) {
        IssueEntity issueEntity = issueRepository.findById(issueId)
                .orElseThrow(() -> new CommonException("Issue not found: " + issueId));
        String oldPriority=null,newPriority=null;
        if (priority!=null){
            oldPriority=issueEntity.getIssuePriority().toString();
            issueEntity.setIssuePriority(priority);
            newPriority=issueEntity.getIssuePriority().toString();
        }
        return "Issue status successfully changed from"+oldPriority+" to "+newPriority;
    }


    public String deleteIssue(Integer issueId) {
        IssueEntity issueEntity = issueRepository.findById(issueId)
                .orElseThrow(() -> new CommonException("Issue not found: " + issueId));
        issueRepository.delete(issueEntity);
        return "Issue deleted successfully...";
    }

    public List<ProjectIssueDTO> getProjectIssues(Integer projectId) {
        return issueMapper.toDTOList(issueRepository.findByProjectId(projectId));
    }


}
