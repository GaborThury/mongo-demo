package com.epam.mongo;

import com.epam.mongo.domain.Command;
import com.epam.mongo.domain.SubCommand;
import com.epam.mongo.util.CommandResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

import static com.epam.mongo.domain.Command.EXIT;
import static com.epam.mongo.domain.Command.LIST_SUBTASKS_WITH_GIVEN_CATEGORY;
import static com.epam.mongo.domain.Command.LIST_WITH_GIVEN_CATEGORY;
import static com.epam.mongo.domain.Command.MANAGE_SUBTASK;
import static com.epam.mongo.domain.Command.MANAGE_TASK;

@Component
public class FlowManager {

    private final CommandDelegator commandDelegator;
    private final List<Command> queryNeededCommands = List.of(LIST_WITH_GIVEN_CATEGORY, LIST_SUBTASKS_WITH_GIVEN_CATEGORY);
    private final List<Command> subcommandNeededCommands = List.of(MANAGE_TASK, MANAGE_SUBTASK);

    @Autowired
    public FlowManager(CommandDelegator commandDelegator) {
        this.commandDelegator = commandDelegator;
    }

    private static final String commands =
            """
                    1. List all tasks
                    2. List overdue tasks
                    3. List all tasks with given category
                    4. List all subtasks related to tasks with the given category
                    5. Perform insert/update/delete of the task.
                    6. Perform insert/update/delete all subtasks of the given task (query parameter).
                    7. Support full-text search by word in task description.
                    8. Support full-text search by sub-task name.
                    9. Exit
                    """
    ;

    public void manage() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                Command command = getInputCommand(scanner);
                SubCommand subCommand = null;
                String query = null;
                if (EXIT.equals(command)) {
                    break;
                } else if (isQueryRequired(command)) {
                    System.out.println("please enter the query");
                    query = scanner.nextLine().toUpperCase();
                } else if (isSubCommandRequired(command)) {
                    System.out.println("please enter subcommand (insert/update/delete)");
                    subCommand = SubCommand.valueOf(scanner.nextLine().toUpperCase());
                }
                commandDelegator.delegate(command, subCommand, query);
            } catch (Exception e) {
                System.err.println("EXCEPTION: " + e);
            }
        }
        scanner.close();
    }



    private boolean isSubCommandRequired(Command command) {
        return false;
    }

    private boolean isQueryRequired(Command command) {
        return queryNeededCommands.contains(command);
    }

    private Command getInputCommand(Scanner scanner) {
        listCommands();
        String enteredCommand = scanner.nextLine();
        System.out.println("you entered: " + enteredCommand);

        return CommandResolver.resolve(Integer.parseInt(enteredCommand));
    }

    private void listCommands() {
        System.out.println("Please enter a number of the command you would like to run. Valid commands are: ");
        System.out.println(commands);
    }
}
