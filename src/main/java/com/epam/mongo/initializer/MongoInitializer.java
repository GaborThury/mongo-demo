package com.epam.mongo.initializer;

import com.epam.mongo.domain.Category;
import com.epam.mongo.domain.SubTask;
import com.epam.mongo.domain.Task;
import com.epam.mongo.repository.TaskRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Component
public class MongoInitializer {

    private final TaskRepository taskRepository;

    public MongoInitializer(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostConstruct
    public void init() {
        List<Task> commands = generateTasks();
        taskRepository.saveAll(commands);
    }

    private List<Task> generateTasks() {
        return List.of(
                new Task(
                        generateTime(),
                        generateCurrentTime(),
                        "Cooking",
                        "have to cook the dinner",
                        List.of(
                                new SubTask("prepare", "put things away from the kitchen desk"),
                                new SubTask("cook", "make sure nothing is raw")
                        ),
                        Category.HOUSEHOLD
                ),
                new Task(
                        generateTime(),
                        generateYesterdayTime(),
                        "Cleaning",
                        "need to clean the apartment",
                        List.of(
                                new SubTask("vacuum cleaning", ""),
                                new SubTask("cleaning the windows", "put vinegar to water")
                        ),
                        Category.HOUSEHOLD
                )
        );
    }

    private BigInteger generateTime() {
        return BigInteger.valueOf(LocalDateTime.of(2022, 10, 19, 10, 15).toEpochSecond(ZoneOffset.UTC));
    }

    private BigInteger generateCurrentTime() {
        return BigInteger.valueOf(System.currentTimeMillis() / 1000);
    }

    private BigInteger generateYesterdayTime() {
        return BigInteger.valueOf(LocalDateTime.now().minusDays(1).toEpochSecond(ZoneOffset.UTC));
    }
}
