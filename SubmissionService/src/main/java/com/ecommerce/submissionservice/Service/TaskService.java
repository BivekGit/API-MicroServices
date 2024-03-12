package com.ecommerce.submissionservice.Service;

import com.ecommerce.submissionservice.Dto.TaskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "TASK-SERVICE", url = "http://localhost:8082")
public interface TaskService {

    @GetMapping("/api/tasks/{taskId}")
    TaskDto getTaskById(@PathVariable Long taskId,
                        @RequestHeader("Authorization") String jwt) throws Exception;

    @PutMapping("/api/tasks/{taskId}/complete")
    TaskDto completeTask(@PathVariable Long taskId) throws Exception;
}
