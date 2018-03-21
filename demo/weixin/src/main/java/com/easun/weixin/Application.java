package com.easun.weixin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.easun.weixin.servlet.CoreServlet;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean  
    public ServletRegistrationBean testServletRegistration() {  
        ServletRegistrationBean registration = new ServletRegistrationBean(new CoreServlet());  
        registration.addUrlMappings("/");  
        return registration;
    }
	
}
