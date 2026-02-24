package kz.asumc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "kz.asumc")
public class AsumcApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsumcApplication.class, args);
    }
}