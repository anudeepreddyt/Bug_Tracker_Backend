package com.anudeepreddy.Bug_Tracker.Repository;

import com.anudeepreddy.Bug_Tracker.Entity.ProjectMemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ProjectMemRepository extends JpaRepository<ProjectMemEntity,Integer>{
    boolean existsByUsersEntity_IdAndProjectEntity_Id(Integer userid, Integer projectid);
    Optional<ProjectMemEntity> findByUsersEntity_IdAndProjectEntity_Id(Integer userid, Integer projectid);
}
