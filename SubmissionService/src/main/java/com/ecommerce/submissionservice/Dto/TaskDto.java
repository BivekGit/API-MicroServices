package com.ecommerce.submissionservice.Dto;

import com.ecommerce.submissionservice.Entity.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
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
