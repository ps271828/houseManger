package com.houses;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.houses.dao")
@EnableTransactionManagement
public class HouseMangerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HouseMangerApplication.class, args);
    }

}
