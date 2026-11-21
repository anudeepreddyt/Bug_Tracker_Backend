package com.anudeepreddy.Bug_Tracker.Repository;

import com.anudeepreddy.Bug_Tracker.Entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Integer> {


    List<CommentEntity> findByProjectEntity_Id(Integer projectId);
}
