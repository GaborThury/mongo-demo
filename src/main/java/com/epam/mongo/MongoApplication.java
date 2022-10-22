package com.epam.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MongoApplication implements CommandLineRunner {

	private final FlowManager flowManager;

	@Autowired
	public MongoApplication(FlowManager flowManager) {
		this.flowManager = flowManager;
	}


	public static void main(String[] args) {
		SpringApplication.run(MongoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		flowManager.manage();
	}
}
