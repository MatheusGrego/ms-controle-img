package com.msuploadimg;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@Log4j2
@SpringBootApplication
public class MsUploadImgApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsUploadImgApplication.class, args);
    }


    @Bean
     ApplicationRunner applicationRunner(Environment environment) {
        return args ->
                log.info("teste " + environment.getProperty("spring.servlet.multipart.max-file-size"));
    }

}
