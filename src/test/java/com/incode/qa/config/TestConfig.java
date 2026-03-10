package com.incode.qa.config;

import com.incode.qa.ioc.HelloWorld;
import com.incode.qa.ui.DashboardPage;
import com.incode.qa.ui.FlowsPage;
import com.incode.qa.ui.IdentitiesPage;
import com.incode.qa.ui.SessionsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ApplicationProperties.class)
public class TestConfig {

    @Autowired
    private ApplicationProperties applicationProperties;

    @Bean
    public HelloWorld helloWorld() {
        return new HelloWorld(applicationProperties);
    }

    @Bean
    public WebDriver webDriver() {
        return new ChromeDriver();
    }

    @Bean
    public DashboardPage dashboardPage(WebDriver driver) {
        return new DashboardPage(
                applicationProperties.getBaseUrl(),
                "/log-in",
                driver,
                applicationProperties.getWaitTimeSeconds()
        );
    }

    @Bean
    public SessionsPage sessionsPage(WebDriver driver) {
        return new SessionsPage(
                applicationProperties.getBaseUrl(),
                "/session",
                driver,
                applicationProperties.getWaitTimeSeconds()
        );
    }

    @Bean
    public IdentitiesPage identitiesPage(WebDriver driver) {
        return new IdentitiesPage(
                applicationProperties.getBaseUrl(),
                "/identities",
                driver,
                applicationProperties.getWaitTimeSeconds()
        );
    }


    @Bean
    public FlowsPage flowsPage(WebDriver driver) {
        return new FlowsPage(
                applicationProperties.getBaseUrl(),
                "/flows",
                driver,
                applicationProperties.getWaitTimeSeconds()
        );
    }
}
