package com.ecommerce.submissionservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long submissionId;
    private Long taskId;
    private Long userId;
    private String status = "PENDING";
    private String link;
    private LocalDateTime submissionTime;
}
