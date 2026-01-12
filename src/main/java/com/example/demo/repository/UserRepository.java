package com.example.demo.repository;

import com.example.demo.model.UserEntity;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

import java.util.Optional;

public interface UserRepository extends CouchbaseRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
}
