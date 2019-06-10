package com.houses;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.houses.dao")
public class HouseMangerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HouseMangerApplication.class, args);
    }

}
