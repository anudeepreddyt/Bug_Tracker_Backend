package com.anudeepreddy.Bug_Tracker.Repository;

import com.anudeepreddy.Bug_Tracker.Entity.UsersEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity,Integer> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<UsersEntity> findByUsername(String username);
}
