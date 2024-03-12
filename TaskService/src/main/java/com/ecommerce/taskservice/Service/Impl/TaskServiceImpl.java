package com.ecommerce.taskservice.Service.Impl;

import com.ecommerce.taskservice.Entity.Task;
import com.ecommerce.taskservice.Entity.TaskStatus;
import com.ecommerce.taskservice.Repository.TaskRepository;
import com.ecommerce.taskservice.Service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Task createTask(Task task, String requestRole) throws Exception {
//        if (!requestRole.equals("ROlE_ADMIN")) {
//            throw new Exception("Only ADMIN can create tasks");
//        }
        task.setStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long taskId) throws Exception {
        return taskRepository.findById(taskId).orElseThrow(() -> new Exception("Task not found" + taskId));
    }

    @Override
    public List<Task> getAllTasks(TaskStatus taskStatus) {
        List<Task> allTask = taskRepository.findAll();
        return allTask
                .stream().
                filter(task -> taskStatus == null || task.getStatus().name()
                        .equalsIgnoreCase(taskStatus.toString()))
                .toList();

    }

    @Override
    public Task updateTaskStatus(Long taskId, Task updatedTask, Long userId) throws Exception {
        Task existingTask = getTaskById(taskId);

        if (updatedTask != null) {
            existingTask.setTitle(updatedTask.getTitle());

        }
        if (updatedTask != null) {
            existingTask.setDescription(updatedTask.getDescription());

        }
        if (updatedTask != null) {
            existingTask.setTags(updatedTask.getTags());
        }
        if (updatedTask != null) {
            existingTask.setStatus(updatedTask.getStatus());
        }
        if (updatedTask != null) {
            existingTask.setDeadline(updatedTask.getDeadline());
        }
        if (updatedTask != null) {
            existingTask.setImage(updatedTask.getImage());
        }

        return taskRepository.save(existingTask);

    }

    @Override
    public void deleteTask(Long taskId) throws Exception {
        Task task = getTaskById(taskId);
        if (task == null) {
            throw new Exception("Task not found" + taskId);
        }

        taskRepository.deleteById(taskId);
    }

    @Override
    public Task assignedToUser(Long taskId, Long userId) throws Exception {
        Task task = getTaskById(taskId);
        task.setAssignedUserId(userId);
        task.setStatus(TaskStatus.DONE);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> assignedUserTasks(Long userId, TaskStatus taskStatus) {
        List<Task> allTask = taskRepository.findByAssignedUserId(userId);

        return allTask
                .stream()
                .filter(
                        task -> taskStatus == null || task.getStatus().name()
                                .equalsIgnoreCase(taskStatus.toString()))
                .toList();
    }

    @Override
    public Task completeTask(Long taskId) throws Exception {
        Task task = getTaskById(taskId);
        task.setStatus(TaskStatus.DONE);
        return taskRepository.save(task);
    }
}
