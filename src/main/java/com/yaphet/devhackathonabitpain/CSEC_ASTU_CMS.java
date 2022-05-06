package com.yaphet.devhackathonabitpain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages =
        {
                "com.yaphet.devhackathonabitpain.models",
                "com.yaphet.devhackathonabitpain.repositories",
                "com.yaphet.devhackathonabitpain.controllers",
                "com.yaphet.devhackathonabitpain.services",
                "com.yaphet.devhackathonabitpain.utilities"

        })
public class CSEC_ASTU_CMS {

    public static void main(String[] args) {
        SpringApplication.run(CSEC_ASTU_CMS.class, args);
    }
}
