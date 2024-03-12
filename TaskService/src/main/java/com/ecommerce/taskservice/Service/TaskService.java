package com.ecommerce.taskservice.Service;

import com.ecommerce.taskservice.Entity.Task;
import com.ecommerce.taskservice.Entity.TaskStatus;

import java.util.List;

public interface TaskService {

    Task createTask(Task task, String requestRole) throws Exception;

    Task getTaskById(Long taskId) throws Exception;


    List<Task> getAllTasks(TaskStatus taskStatus);

    Task updateTaskStatus(Long taskId, Task updatedTask, Long userId) throws Exception;

    void deleteTask(Long taskId) throws Exception;

    Task assignedToUser(Long taskId, Long userId) throws Exception;

    List<Task> assignedUserTasks(Long userId, TaskStatus taskStatus);

    Task completeTask(Long taskId) throws Exception;

    ;

}
