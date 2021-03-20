package ua.kharkiv.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * An entry point to CatalogCompanies application.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableJpaRepositories("ua.kharkiv.catalog.repository")

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
