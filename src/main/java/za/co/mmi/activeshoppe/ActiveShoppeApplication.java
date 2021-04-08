package za.co.mmi.activeshoppe;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import za.co.mmi.activeshoppe.config.StartUpData;

@AllArgsConstructor
@SpringBootApplication
public class ActiveShoppeApplication implements CommandLineRunner {
    private StartUpData startUpData;

    public static void main(String[] args) {
        SpringApplication.run(ActiveShoppeApplication.class, args);
    }

    @Override
    public void run(String... args) {
        startUpData.loadData();
    }
}
