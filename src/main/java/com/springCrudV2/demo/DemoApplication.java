package com.springCrudV2.demo;

import com.springCrudV2.demo.service.RuleAllService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);
		RuleAllService ruleAllService = run.getBean("ruleAllService", RuleAllService.class);
		ruleAllService.run();
	}

}
