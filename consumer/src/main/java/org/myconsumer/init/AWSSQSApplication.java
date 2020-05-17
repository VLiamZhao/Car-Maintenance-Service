package org.myconsumer.init;

import org.myconsumer.util.SyncMessageReceiver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.jms.JMSException;

@SpringBootApplication(scanBasePackages = {"org.myconsumer"})
public class AWSSQSApplication {

    public static void main(String[] args) throws InterruptedException, JMSException {
        SpringApplication.run(AWSSQSApplication.class, args);
//        ConfigurableApplicationContext app = SpringApplication.run(AWSSQSApplication.class, args);
//        MessageConsumeService messageConsumeService = app.getBean(MessageConsumeService.class);
//        messageConsumeService.consumeMessage();
        SyncMessageReceiver syncMessageReceiver = new SyncMessageReceiver();
        syncMessageReceiver.runSync();
    }

}