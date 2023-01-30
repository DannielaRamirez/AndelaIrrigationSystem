package com.agrofarm.irrigationsystem;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableRetry
public class IrrigationSystemApplication {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    public static void main(String[] args) {
        SpringApplication.run(IrrigationSystemApplication.class, args);
    }

}
