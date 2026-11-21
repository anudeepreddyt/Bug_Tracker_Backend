package com.anudeepreddy.Bug_Tracker.Common;

import com.anudeepreddy.Bug_Tracker.DTO.ProjectIssueDTO;
import com.anudeepreddy.Bug_Tracker.Entity.IssueEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IssueMapper {

    public ProjectIssueDTO toDTO(IssueEntity entity) {
        ProjectIssueDTO dto = new ProjectIssueDTO();
        dto.setIssueId(entity.getId());
        dto.setReporterId(entity.getReportedId());
        dto.setReporterName(entity.getReporterName());
        dto.setAssigneeId(entity.getAssigneeId());
        dto.setAssigneeName(entity.getAssigneeName());
        dto.setIssueTitle(entity.getIssueTitle());
        dto.setIssueDescription(entity.getIssueDescription());
        dto.setIssueStatus(entity.getIssueStatus());
        dto.setIssuePriority(entity.getIssuePriority());
        return dto;
    }

    public List<ProjectIssueDTO> toDTOList(List<IssueEntity> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}