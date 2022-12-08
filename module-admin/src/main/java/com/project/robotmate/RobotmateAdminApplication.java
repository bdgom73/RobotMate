package com.project.robotmate;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.persistence.EntityManager;

@EnableAsync
@SpringBootApplication
@EntityScan(basePackages = {"com.project.robotmate.domain"})
@EnableJpaAuditing
public class RobotmateAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(RobotmateAdminApplication.class, args);
	}

	@Bean
	public JPAQueryFactory queryFactory(EntityManager em) {
		return new JPAQueryFactory(em);
	}


}
