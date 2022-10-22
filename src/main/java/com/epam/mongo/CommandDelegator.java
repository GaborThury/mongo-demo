package com.epam.mongo;

import com.epam.mongo.domain.Command;
import com.epam.mongo.repository.TaskRepository;
import com.epam.mongo.service.TaskService;
import org.springframework.stereotype.Component;

import static com.epam.mongo.domain.Command.LIST_ALL;

@Component
public class CommandDelegator {

    private final TaskService taskService;

    public CommandDelegator(TaskService taskService) {
        this.taskService = taskService;
    }

    public void delegate(Command command) {
        switch (command) {
            case LIST_ALL -> taskService.printAllTasks();
            case LIST_OVERDUE -> taskService.printOverdueTasks();
        }
    }
}
