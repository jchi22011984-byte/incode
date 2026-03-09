package com.incode.qa.steps;

import com.incode.qa.config.TestConfig;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@CucumberContextConfiguration
@SpringBootTest(classes = TestConfig.class)
@ActiveProfiles("test")
public class CucumberSpringContextConfiguration {
}