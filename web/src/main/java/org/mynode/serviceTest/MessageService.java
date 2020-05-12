package org.mynode.serviceTest;

import com.amazonaws.services.sqs.AmazonSQS;

import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Value("${aws.sqs.name}")
    private String queueName;
    @Autowired
    private AmazonSQS sqs;
//    private CreateQueueResult queueResult = sqs.createQueue(queueName);

    public void sendMessage(String messageBody, int delaySeconds){
        SendMessageRequest sendMsgRequest = new SendMessageRequest()
                .withQueueUrl(getQueueUrl(queueName))
                .withMessageBody(messageBody)
                .withDelaySeconds(delaySeconds);
        sqs.sendMessage(sendMsgRequest);
    }

    public String getQueueUrl(String queueName){
        GetQueueUrlResult getQueueUrlResult = sqs.getQueueUrl(queueName);
        return getQueueUrlResult.getQueueUrl();
    }
//    public void consumeMessage(){
//        String queueUrl = sqs.getQueueUrl(queueName).getQueueUrl();
//        // recevie messages
//        List<Message> messages = sqs.receiveMessage(queueUrl).getMessages();
//
//        // delete messages from the queue
//        for (Message m : messages) {
//            sqs.deleteMessage(queueUrl, m.getReceiptHandle());
//        }
//    }

}
