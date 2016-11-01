package com.jovicazoric;

import com.jovicazoric.config.FtpConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(FtpConfig.class)
public class FtpIntegrationApplication {
    public static void main(String[] args) {
        SpringApplication.run(FtpIntegrationApplication.class, args);
    }
}


