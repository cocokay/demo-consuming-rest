package com.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories("com.spring")
@EntityScan("com.spring")
public class Application implements CommandLineRunner {
    //private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private VendorService vendorService;

    public static void main(String args[]) throws Exception {
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(String... args) throws Exception {
        vendorService.show();
    }
}
