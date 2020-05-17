package org.myconsumer.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyQueueListener implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void onMessage(Message message) {
        try{
            logger.info("Receiving message from queue...");
            logger.info("Receiving " + ((TextMessage) message).getText());
        } catch (JMSException e){
            e.printStackTrace();
        }
    }
}
