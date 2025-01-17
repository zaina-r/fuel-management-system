package org.example.fuel_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class FuelManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(FuelManagementSystemApplication.class, args);


        
    }

}
