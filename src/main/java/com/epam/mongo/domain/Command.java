package com.epam.mongo.domain;

public enum Command {
    LIST_ALL,
    LIST_OVERDUE,
    LIST_WITH_GIVEN_CATEGORY,
    LIST_SUBTASKS_WITH_GIVEN_CATEGORY,
    MANAGE_TASK,
    MANAGE_SUBTASK,
    SEARCH_TASK,
    SEARCH_SUBTASK,
    EXIT
}
