package com.example.demo.repository;

import com.example.demo.model.TaskEntity;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends CouchbaseRepository<TaskEntity, String> {
    // This class is responsible for handling database operations related to tasks.
    // It interacts with the Task entity and the database using the JpaRepository interface.
    // The @Repository annotation marks this class as a repository component,
    // which allows Spring to automatically create an instance of this class
    // and manage its lifecycle.
    List<TaskEntity> findByUserId(String userId);
    Optional<TaskEntity> findByTaskIdAndUserId(String taskId, String userId);

}
