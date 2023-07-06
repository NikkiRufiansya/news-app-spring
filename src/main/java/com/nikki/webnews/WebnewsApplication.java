package com.nikki.webnews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class WebnewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebnewsApplication.class, args);
	}



}
