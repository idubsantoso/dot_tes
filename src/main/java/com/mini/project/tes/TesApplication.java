package com.mini.project.tes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableConfigurationProperties({com.mini.project.tes.config.file.FileStorageProperties.class})
@EnableJpaRepositories(basePackages = "com.mini.project.tes.persistence.repository")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableCaching
public class TesApplication extends SpringBootServletInitializer {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(TesApplication.class, args);


	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(TesApplication.class);
	}
}
