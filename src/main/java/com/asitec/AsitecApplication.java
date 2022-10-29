package com.asitec;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AsitecApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsitecApplication.class, args);
	}
	@ConfigurationProperties(prefix="spring.datasource")
	@Bean
	@Qualifier("data_mysql")
	public DataSource getDataSource() {
		return DataSourceBuilder
				.create()
				.build();
	}
}
