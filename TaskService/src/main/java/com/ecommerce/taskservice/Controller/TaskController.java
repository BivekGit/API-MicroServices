package com.ecommerce.taskservice.Controller;

import com.ecommerce.taskservice.Dto.UserDto;
import com.ecommerce.taskservice.Entity.Task;
import com.ecommerce.taskservice.Entity.TaskStatus;
import com.ecommerce.taskservice.Service.TaskService;
import com.ecommerce.taskservice.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        UserDto user = userService.getUserProfile(jwt);
        System.out.println(user.getRole());
        Task createTask = taskService.createTask(task, userService.getUserProfile(jwt).getRole());

        return new ResponseEntity<>(createTask, HttpStatus.CREATED);

    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(
            @RequestHeader("Authorization") String jwt, @PathVariable Long taskId) throws Exception {

        UserDto user = userService.getUserProfile(jwt);
        Task task = taskService.getTaskById(taskId);
        return new ResponseEntity<>(task, HttpStatus.OK);

    }

    @GetMapping("/user")
    public ResponseEntity<List<Task>> assignedUserTasks(@RequestHeader("Authorization") String jwt,
                                                        @RequestParam TaskStatus taskStatus) throws Exception {

        UserDto user = userService.getUserProfile(jwt);
        List<Task> task = taskService.assignedUserTasks(user.getUserId(), taskStatus);
        return new ResponseEntity<>(task, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(@RequestHeader("Authorization") String jwt,
                                                  @RequestParam TaskStatus taskStatus) throws Exception {

        UserDto user = userService.getUserProfile(jwt);
        List<Task> task = taskService.getAllTasks(taskStatus);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/{taskId}/user/{userId}/assigned")
    public ResponseEntity<Task> assignedTaskToUser(@RequestHeader("Authorization") String jwt,
                                                   @PathVariable Long taskId,
                                                   @PathVariable Long userId) throws Exception {

        UserDto user = userService.getUserProfile(jwt);

        Task task = taskService.assignedToUser(taskId, userId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@RequestHeader("Authorization") String jwt,
                                           @RequestBody Task updatedTask,
                                           @PathVariable Long taskId) throws Exception {


        UserDto user = userService.getUserProfile(jwt);
        Task task = taskService.updateTaskStatus(taskId, updatedTask, user.getUserId());
        return new ResponseEntity<>(task, HttpStatus.OK);
    }


    @PutMapping("/{taskId}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable Long taskId) throws Exception {

        Task task = taskService.completeTask(taskId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@RequestHeader("Authorization") String jwt,
                                           @PathVariable Long taskId) throws Exception {
        UserDto user = userService.getUserProfile(jwt);
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
