package com.ecommerce.submissionservice.Controller;

import com.ecommerce.submissionservice.Dto.UserDto;
import com.ecommerce.submissionservice.Service.SubmissionService;
import com.ecommerce.submissionservice.Service.TaskService;
import com.ecommerce.submissionservice.Service.UserService;
import com.ecommerce.submissionservice.Entity.Submission;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;
    private final TaskService taskService;
    private final UserService userService;


    @PostMapping()
    public ResponseEntity<Submission> submitTask(@RequestParam Long taskId,
                                                 @RequestHeader("Authorization") String jwt,
                                                 @RequestParam String link) throws Exception {

        UserDto user = userService.getUserProfile(jwt);
        Submission submission = submissionService.submitTask(taskId, user.getUserId(), link, jwt);
        return new ResponseEntity<>(submission, HttpStatus.CREATED);
    }

    @GetMapping("/{submissionId}")
    public ResponseEntity<Submission> getTaskSubmissionById(@PathVariable Long submissionId,
                                                            @RequestHeader("Authorization") String jwt) throws Exception {
        UserDto user = userService.getUserProfile(jwt);
        Submission submission = submissionService.getTaskSubmissionById(submissionId);
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Submission>> getAllSubmissions(@RequestHeader("Authorization") String jwt
    ) throws Exception {
        UserDto user = userService.getUserProfile(jwt);
        List<Submission> submissions = submissionService.getAllTaskSubmissions();
        return new ResponseEntity<>(submissions, HttpStatus.OK);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Submission>> getSubmissionsByTaskId(@RequestHeader("Authorization") String jwt,
                                                                   @PathVariable Long taskId
    ) throws Exception {
        UserDto user = userService.getUserProfile(jwt);
        List<Submission> submissions = submissionService.getSubmissionsByTaskId(taskId);
        return new ResponseEntity<>(submissions, HttpStatus.OK);
    }
@PutMapping("/{submissionId}")
    public ResponseEntity<Submission> acceptDeclineSubmission(@PathVariable Long submissionId,
                                                              @RequestHeader("Authorization") String jwt,
                                                              @RequestParam String status) throws Exception {

        UserDto user = userService.getUserProfile(jwt);
        Submission submission = submissionService.acceptDeclineSubmission(submissionId, status);
        return new ResponseEntity<>(submission, HttpStatus.OK);
    }
}
