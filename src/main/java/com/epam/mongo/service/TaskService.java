package com.epam.mongo.service;

import com.epam.mongo.domain.Task;
import com.epam.mongo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void printAllTasks() {
        System.out.println("Printing all tasks...");
        taskRepository.findAll().forEach(
                task -> System.out.println(task + "\n")
        );
    }

    public void printOverdueTasks() {
        List<Task> overdueTasks = taskRepository.getOverdueTasks(BigInteger.valueOf(System.currentTimeMillis() / 1000));
        System.out.println("Printing overdue tasks...");
        overdueTasks.forEach(
                task -> System.out.println(task + "\n")
        );
    }
}
