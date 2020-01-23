package com.ccx.mngt;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.ccx.mngt","com.ccx.security"})
public class MarketMngtApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketMngtApplication.class,args);
    }
}
