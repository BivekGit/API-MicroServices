package com.ecommerce.submissionservice.Service;

import com.ecommerce.submissionservice.Entity.Submission;

import java.util.List;

public interface SubmissionService {

    Submission submitTask(Long userId, Long taskId, String link, String jwt) throws Exception;

    Submission getTaskSubmissionById(Long submissionId) throws Exception;

    List<Submission> getAllTaskSubmissions();

    List<Submission> getSubmissionsByTaskId(Long taskId);

    Submission acceptDeclineSubmission(Long submissionId, String status) throws Exception;
}
