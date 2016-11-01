package com.jovicazoric.config;

import com.jovicazoric.entity.MyPayload;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface FtpGateway {

    @Gateway(requestChannel = "ftpChannel")
    void send(MyPayload file);


}