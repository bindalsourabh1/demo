package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}



/*
TO DO LIST
1. User can add multiple task.
2. Can add startdate and endDate.
3. As of now we will only add that task is completed or not will skip endDate functionality.
3. User should be able to perform CRUD operations on task.

User ->
username, userId, task(start, endDate, description),

Task->
Title
description
StartDate
endDate

// My application
Model Class
user specific data storage (SQl/noSQL)
Time left
Create, Read, Update, Delete

UI (last)

 */