package com.epam.mongo.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Task {

    private long createdAtInEpoch;
    private long deadlineInEpoch;
    private String name;
    private String description;
    private List<SubTask> subtasks;
    private Category category;

    public Task() {
    }

    public Task(long createdAtInEpoch, long deadlineInEpoch, String name, String description, List<SubTask> subtasks, Category category) {
        this.createdAtInEpoch = createdAtInEpoch;
        this.deadlineInEpoch = deadlineInEpoch;
        this.name = name;
        this.description = description;
        this.subtasks = subtasks;
        this.category = category;
    }

    public long getCreatedAtInEpoch() {
        return createdAtInEpoch;
    }

    public void setCreatedAt(long createdAtInEpoch) {
        this.createdAtInEpoch = createdAtInEpoch;
    }

    public long getDeadlineInEpoch() {
        return deadlineInEpoch;
    }

    public void setDeadlineInEpoch(long deadlineInEpoch) {
        this.deadlineInEpoch = deadlineInEpoch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SubTask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<SubTask> subtasks) {
        this.subtasks = subtasks;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Task{" +
                "createdAtInEpoch=" + createdAtInEpoch +
                ", deadlineInEpoch=" + deadlineInEpoch +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", subtasks=" + subtasks +
                ", category=" + category +
                '}';
    }
}
