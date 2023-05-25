package com.bkk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class BkkBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BkkBlogApplication.class,args);
    }
}
