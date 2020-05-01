package org.mynode.service;

import com.amazonaws.services.sqs.AmazonSQS;

import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Value("${aws.sqs.name}")
    private String queueName;
    @Autowired
    private AmazonSQS sqs;
    public void sendMessage(String messageBody, int delaySeconds){
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(getQueueUrl(queueName))
                .withMessageBody(messageBody)
                .withDelaySeconds(delaySeconds);
        sqs.sendMessage(send_msg_request);
    }

    public String getQueueUrl(String queueNmae){
        GetQueueUrlResult getQueueUrlResult = sqs.getQueueUrl(queueNmae);
        return getQueueUrlResult.getQueueUrl();
    }
}
