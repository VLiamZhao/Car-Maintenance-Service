package org.mynode.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"org.mynode"})
@ServletComponentScan(basePackages = {"org.mynode.filter"})
public class ApplicationBootstrap extends SpringBootServletInitializer {

    public static void main(String[] args){
        SpringApplication.run(ApplicationBootstrap.class, args);
    }

}