package tn.esprit.hydatisqueryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class HydatisQueryAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(HydatisQueryAppApplication.class, args);
    }

}
