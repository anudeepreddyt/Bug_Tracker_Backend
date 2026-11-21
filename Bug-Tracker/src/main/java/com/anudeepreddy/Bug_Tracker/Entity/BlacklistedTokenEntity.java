package com.anudeepreddy.Bug_Tracker.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "blacklisted_tokens")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlacklistedTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 512)
    private String token;

    private LocalDateTime blacklistedAt;

    @PrePersist
    protected void onCreate() {
        this.blacklistedAt = LocalDateTime.now();
    }
}
