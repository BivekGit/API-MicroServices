package com.ecommerce.submissionservice.Service.Impl;

import com.ecommerce.submissionservice.Dto.TaskDto;
import com.ecommerce.submissionservice.Service.SubmissionService;
import com.ecommerce.submissionservice.Service.TaskService;
import com.ecommerce.submissionservice.Service.UserService;
import com.ecommerce.submissionservice.Entity.Submission;
import com.ecommerce.submissionservice.Repository.SubmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final TaskService taskService;
    private final UserService userService;

    @Override
    public Submission submitTask(Long userId, Long taskId, String link, String jwt) throws Exception {

        TaskDto task = taskService.getTaskById(taskId, jwt);

        if (task != null) {
            Submission submission = new Submission();
            submission.setTaskId(taskId);
            submission.setUserId(userId);
            submission.setLink(link);
            submission.setSubmissionTime(LocalDateTime.now());
            return submissionRepository.save(submission);
        }
        throw new Exception("Task not found with taskId: " + taskId);
    }

    @Override
    public Submission getTaskSubmissionById(Long submissionId) throws Exception {

        return submissionRepository.findById(submissionId)
                .orElseThrow(() -> new Exception("Submission not found with submissionId: " + submissionId));

    }

    @Override
    public List<Submission> getAllTaskSubmissions() {
        return submissionRepository.findAll();
    }

    @Override
    public List<Submission> getSubmissionsByTaskId(Long taskId) {
        return submissionRepository.findByTaskId(taskId);
    }

    @Override
    public Submission acceptDeclineSubmission(Long submissionId, String status) throws Exception {

        Submission submission = getTaskSubmissionById(submissionId);
        submission.setStatus(status);

        if (status.equals("ACCEPTED")) {
            taskService.completeTask(submission.getTaskId());
        }

        return submissionRepository.save(submission);
    }
}
