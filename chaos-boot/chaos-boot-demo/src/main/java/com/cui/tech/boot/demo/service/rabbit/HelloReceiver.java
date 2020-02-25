package com.cui.tech.boot.demo.service.rabbit;

import com.cui.tech.boot.demo.api.entity.Version;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Service;


@Service
//@RabbitListener(queues = "hello")
public class HelloReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
    }

    @RabbitHandler
    public void process(Version versionManage) {
        System.out.println("Receiver object : " + versionManage);
    }
}
