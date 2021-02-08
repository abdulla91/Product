package com.company.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan("com.company")
public class AppConfig {
	
	@Bean
	public DataSource dataSource() {
		
		DriverManagerDataSource driverManager = new DriverManagerDataSource();
		driverManager.setDriverClassName("com.mysql.cj.jdbc.Driver");
		driverManager.setUrl("jdbc:mysql://localhost:3306/test_spring_jdbc");
		driverManager.setUsername("root");
		driverManager.setPassword("123456789");
		return driverManager;
		
	}
}
