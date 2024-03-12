package com.ecommerce.taskservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;
    private String title;
    private String image;
    private Long assignedUserId;
    private String description;
    private List<String> tags = new ArrayList<>();
    private TaskStatus status;
    private LocalDateTime deadline;
    private LocalDateTime createdAt;
}
