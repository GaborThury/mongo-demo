package com.epam.mongo.repository;

import com.epam.mongo.domain.Category;
import com.epam.mongo.domain.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByDeadlineInEpochLessThan(long deadlineInEpoch);
    List<Task> findByCategoryLike(Category category);
    List<Task> findByDescriptionLike(String description);

}
