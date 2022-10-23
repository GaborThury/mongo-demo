package com.epam.mongo;

import com.epam.mongo.domain.Category;
import com.epam.mongo.domain.Command;
import com.epam.mongo.domain.SubCommand;
import com.epam.mongo.domain.Task;
import com.epam.mongo.service.TaskService;
import org.springframework.stereotype.Component;

import static com.epam.mongo.domain.SubCommand.DELETE;
import static com.epam.mongo.domain.SubCommand.INSERT;
import static com.epam.mongo.domain.SubCommand.UPDATE;

@Component
public class CommandDelegator {

    private final TaskService taskService;

    public CommandDelegator(TaskService taskService) {
        this.taskService = taskService;
    }

    public void delegate(Command command, SubCommand subCommand, String query) {
        switch (command) {
            case LIST_ALL -> taskService.printAllTasks();
            case LIST_OVERDUE -> taskService.printOverdueTasks();
            case LIST_WITH_GIVEN_CATEGORY -> taskService.printTasksWithGivenCategory(Category.valueOf(query));
            case LIST_SUBTASKS_WITH_GIVEN_CATEGORY -> taskService.printSubTasksWithGivenCategory(Category.valueOf(query));
            case MANAGE_TASK ->  delegateTaskBasedOnSubcommand(subCommand);
            case SEARCH_TASK_BY_DESCRIPTION -> taskService.printTasksIfDescriptionContains(query);
        }
    }

    private void delegateTaskBasedOnSubcommand(SubCommand subCommand) {
        Task task = new Task(); // todo: this should be handled by user input ofc
        if (INSERT.equals(subCommand)) {
            taskService.createTask(task);
        } else if (UPDATE.equals(subCommand)) {
            taskService.updateTask(task);
        } else if (DELETE.equals(subCommand)) {
            taskService.deleteTask(task);
        }
    }
}
