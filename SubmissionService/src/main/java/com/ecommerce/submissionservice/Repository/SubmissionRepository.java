package com.ecommerce.submissionservice.Repository;

import com.ecommerce.submissionservice.Entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByTaskId(Long taskId);
}
