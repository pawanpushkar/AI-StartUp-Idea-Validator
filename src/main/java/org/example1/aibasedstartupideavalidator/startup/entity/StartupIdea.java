package org.example1.aibasedstartupideavalidator.startup.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "startup_ideas",
        indexes = {
                @Index(name = "idx_startup_name", columnList = "startup_name"),
                @Index(name = "idx_market", columnList = "market")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StartupIdea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "startup_name",
            nullable = false,
            length = 150
    )
    private String startupName;

    @Column(
            name = "idea",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String idea;

    @Column(
            name = "problem",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String problem;

    @Column(
            name = "target_customer",
            nullable = false,
            length = 255
    )
    private String targetCustomer;

    @Column(
            name = "market",
            nullable = false,
            length = 150
    )
    private String market;

    @Column(
            name = "business_model",
            nullable = false,
            length = 100
    )
    private String businessModel;

    @Column(
            name = "created_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createdAt;

    @Column(
            name = "updated_at",
            nullable = false
    )
    private LocalDateTime updatedAt;


    @PrePersist
    protected void onCreate() {

        LocalDateTime now = LocalDateTime.now();

        this.createdAt = now;
        this.updatedAt = now;
    }


    @PreUpdate
    protected void onUpdate() {

        this.updatedAt = LocalDateTime.now();
    }
}