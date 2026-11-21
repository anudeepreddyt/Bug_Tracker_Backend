package com.anudeepreddy.Bug_Tracker.Repository;

import com.anudeepreddy.Bug_Tracker.Entity.BlacklistedTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedTokenEntity,Integer> {
    boolean existsByToken(String token);
}
