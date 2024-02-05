package com.nus.iss.tasktracker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class TaskTrackerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskTrackerApiApplication.class, args);
		log.info("Task Tracker Application started successfully...");
	}

}
