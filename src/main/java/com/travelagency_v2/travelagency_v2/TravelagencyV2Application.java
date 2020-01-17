package com.travelagency_v2.travelagency_v2;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TravelagencyV2Application {

    public static void main(String[] args) {
        SpringApplication.run(TravelagencyV2Application.class, args);
    }

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    private int asd;


}
