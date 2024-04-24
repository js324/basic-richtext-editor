package com.challenge.textarea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TextareaApplication {
	private static Logger logger = LoggerFactory.getLogger(TextareaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TextareaApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/content").allowedOrigins("http://localhost:5173").allowedMethods("*");
			}
		};
	}



//	private void initialize() throws Exception {
//		createDBIfNotExists();
//		createContainerIfNotExists();
//
//	}
//	private void createDBIfNotExists() throws Exception {
//        String databaseName = "textarea";
//        CosmosDatabaseResponse databaseResponse = client.createDatabaseIfNotExists(databaseName).block();
//		database = client.getDatabase(databaseResponse.getProperties().getId());
//		logger.info("Checking database " + database.getId() + " completed!\n");
//
//	}
//	private void createContainerIfNotExists() throws Exception {
//        String containerName = "content";
//        logger.info("Create container " + containerName + " if not exists.");
//
//		//  Create container if not exists
//		try {
//			CosmosContainerProperties containerProperties = new CosmosContainerProperties(containerName, "/id");
//			ThroughputProperties throughputProperties = ThroughputProperties.createManualThroughput(400);
//			CosmosContainerResponse cosmosContainerResponse = database.createContainerIfNotExists(containerProperties, throughputProperties).block();
//
//			container = database.getContainer(cosmosContainerResponse.getProperties().getId());
//			containerProperties = cosmosContainerResponse.getProperties();
//			CosmosContainerResponse propertiesReplace = container.replace(containerProperties, new CosmosContainerRequestOptions()).block();
//			logger.info("setupContainer(): Container " + container.getId() + " in " + database.getId() +
//					"has been updated with it's new properties.");
//		}
//		catch (Exception e) {
//			logger.error("setupContainer(): Unable to update properties for container " + container.getId() +
//					" in database " + database.getId() +
//					". e: " + e.getLocalizedMessage());
//		};
//	}


}

