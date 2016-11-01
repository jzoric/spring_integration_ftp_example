package com.jovicazoric.controller;

import com.jovicazoric.config.FtpGateway;
import com.jovicazoric.entity.MyPayload;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;

@Controller
class IndexController {
    private final FtpGateway ftpGateway;

    public IndexController(FtpGateway ftpGateway) {
        this.ftpGateway = ftpGateway;
    }

    @GetMapping("/")
    public @ResponseBody
    String uploadToFtp() throws FileNotFoundException {

        JSONObject data = new JSONObject();
        data.put("username", "jovica.zoric");
        data.put("password", "secret");

        ftpGateway.send(new MyPayload("jovica.json", data.toString().getBytes()));
        return "OK";

    }
}
