package com.jb.bankapp;

import com.jb.bankapp.Art.ArtUtil;
import com.jb.bankapp.Repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankAppApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(BankAppApplication.class, args);
        System.out.println(ArtUtil.LOCALHOST);


    }

}
