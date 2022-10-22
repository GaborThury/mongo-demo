package com.epam.mongo.repository;

import com.epam.mongo.domain.Command;
import com.epam.mongo.domain.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {

    @Query("{deadlineInEpoch: {$lt:?0}}")
    List<Task> getOverdueTasks(BigInteger deadlineInEpoch);

}
