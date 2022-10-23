package com.epam.mongo.util;

import com.epam.mongo.domain.Command;
import org.springframework.stereotype.Component;

import static com.epam.mongo.domain.Command.EXIT;
import static com.epam.mongo.domain.Command.LIST_ALL;
import static com.epam.mongo.domain.Command.LIST_OVERDUE;
import static com.epam.mongo.domain.Command.LIST_SUBTASKS_WITH_GIVEN_CATEGORY;
import static com.epam.mongo.domain.Command.LIST_WITH_GIVEN_CATEGORY;
import static com.epam.mongo.domain.Command.MANAGE_SUBTASK;
import static com.epam.mongo.domain.Command.MANAGE_TASK;
import static com.epam.mongo.domain.Command.SEARCH_SUBTASK;
import static com.epam.mongo.domain.Command.SEARCH_TASK;

@Component
public class CommandResolver {

    public static Command resolve(int input) {
        return switch (input) {
            case 1 -> LIST_ALL;
            case 2 -> LIST_OVERDUE;
            case 3 -> LIST_WITH_GIVEN_CATEGORY;
            case 4 -> LIST_SUBTASKS_WITH_GIVEN_CATEGORY;
            case 5 -> MANAGE_TASK;
            case 6 -> MANAGE_SUBTASK;
            case 7 -> SEARCH_TASK;
            case 8 -> SEARCH_SUBTASK;
            case 9 -> EXIT;
            default -> throw new IllegalArgumentException("Given command is invalid: " + input);
        };
    }
}
