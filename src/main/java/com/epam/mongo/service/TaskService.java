package com.epam.mongo.service;

import com.epam.mongo.domain.Category;
import com.epam.mongo.domain.SubTask;
import com.epam.mongo.domain.Task;
import com.epam.mongo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void printAllTasks() {
        System.out.println("Printing all tasks...");
        List<Task> tasks = taskRepository.findAll();
        printElements(tasks);
    }

    public void printOverdueTasks() {
        List<Task> overdueTasks = taskRepository.findByDeadlineInEpochLessThan(System.currentTimeMillis() / 1000);
        printElements(overdueTasks);
    }

    public void printTasksWithGivenCategory(Category category) {
        List<Task> tasksWithGivenCategory = taskRepository.findByCategoryLike(category);
        printElements(tasksWithGivenCategory);
    }

    public void printSubTasksWithGivenCategory(Category category) {
        List<SubTask> tasksWithGivenCategory = taskRepository.findByCategoryLike(category)
                .stream()
                .filter(task -> category.equals(task.getCategory()))
                .map(Task::getSubtasks)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        printElements(tasksWithGivenCategory);
    }

    private void printElements(List<?> elements) {
        elements.forEach(
                task -> System.out.println(task + "\n")
        );
    }
}
