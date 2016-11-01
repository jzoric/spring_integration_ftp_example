package com.jovicazoric.config;

import com.jovicazoric.entity.MyPayload;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.file.FileHeaders;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.integration.support.MessageBuilder;

@Configuration
@IntegrationComponentScan
@EnableConfigurationProperties(FtpProperties.class)
public class FtpConfig {

    private static String REMOTE_DIR = "tmp";
    private final FtpProperties ftpProperties;

    public FtpConfig(FtpProperties ftpProperties) {
        this.ftpProperties = ftpProperties;
    }

    @Bean
    DefaultFtpSessionFactory ftpSessionFactory(){
        DefaultFtpSessionFactory ftpSessionFactory = new DefaultFtpSessionFactory();
        ftpSessionFactory.setHost(ftpProperties.getHost());
        ftpSessionFactory.setUsername(ftpProperties.getUsername());
        ftpSessionFactory.setPassword(ftpProperties.getPassword());
        return ftpSessionFactory;
    }

    @Bean
    public IntegrationFlow ftpOutboundFlow(DefaultFtpSessionFactory ftpSessionFactory) {
        return IntegrationFlows.from("ftpChannel")
                .transform(MyPayload.class,(MyPayload file) -> MessageBuilder.withPayload(file.getPayload())
                        .setHeader(FileHeaders.FILENAME, file.getFilename())
                        .build())
                .handleWithAdapter(adapters -> adapters.ftp(ftpSessionFactory).remoteDirectory(REMOTE_DIR))
                .get();
    }
}
