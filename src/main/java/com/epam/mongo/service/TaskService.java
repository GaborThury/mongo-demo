package com.epam.mongo.service;

import com.epam.mongo.domain.Category;
import com.epam.mongo.domain.Task;
import com.epam.mongo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void printAllTasks() {
        System.out.println("Printing all tasks...");
        List<Task> tasks = taskRepository.findAll();
        printTasks(tasks);
    }

    public void printOverdueTasks() {
        List<Task> overdueTasks = taskRepository.findByDeadlineInEpochLessThan(System.currentTimeMillis() / 1000);
        printTasks(overdueTasks);
    }

    public void printTasksWithGivenCategory(Category category) {
        List<Task> tasksWithGivenCategory = taskRepository.findByCategoryLike(category);
        printTasks(tasksWithGivenCategory);
    }

    private void printTasks(List<Task> tasks) {
        tasks.forEach(
                task -> System.out.println(task + "\n")
        );
    }
}
