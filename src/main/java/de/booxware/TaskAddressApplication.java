package de.booxware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AutoConfigurationPackage
public class TaskAddressApplication {

    public static void main(String[] args) {

		SpringApplication.run(TaskAddressApplication.class, args);
    }
}
