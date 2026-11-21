package com.anudeepreddy.Bug_Tracker.Repository;

import com.anudeepreddy.Bug_Tracker.Entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity,Integer> {
    Optional<ProjectEntity> findByProjectName(String projectName);
}
