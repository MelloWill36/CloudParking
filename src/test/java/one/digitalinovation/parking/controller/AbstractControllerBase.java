package one.digitalinovation.parking.controller;

import org.testcontainers.containers.MySQLContainer;

public abstract class AbstractControllerBase {

	static final MySQLContainer MYSQL_CONTAINER;
	
	static {
		MYSQL_CONTAINER = new MySQLContainer("mysql");
		MYSQL_CONTAINER.start();
		System.setProperty("spring.datasouce.url", MYSQL_CONTAINER.getJdbcUrl());
		System.setProperty("spring.datasouce.username", MYSQL_CONTAINER.getUsername());
		System.setProperty("spring.datasouce.password", MYSQL_CONTAINER.getPassword());
	}
}
