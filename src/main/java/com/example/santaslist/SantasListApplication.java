package com.example.santaslist;

import com.example.santaslist.model.Wish;
import com.example.santaslist.repository.WishRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.management.openmbean.CompositeData;

@SpringBootApplication
public class SantasListApplication {

    public static void main(String[] args) {
        SpringApplication.run(SantasListApplication.class, args);

    }

}
