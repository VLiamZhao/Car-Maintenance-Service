package org.mynode.serviceTest;
import com.amazonaws.services.dynamodbv2.xspec.M;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumeService {
    @Value("${aws.sqs.name}")
    private String queueName;
    @Autowired
    AmazonSQS sqs;

    public void SQSPoll(){
        String queueUrl = sqs.getQueueUrl(queueName).getQueueUrl();
        // recevie messages
        ReceiveMessageResult messages = sqs.receiveMessage(queueUrl);

        // delete messages from the queue
       messages.getMessages().forEach(m -> {
          sqs.deleteMessage(queueUrl,m.getReceiptHandle());
       });
    }
//    public static void main(String[] args){
//        MessageConsumeService messageConsumeService = new MessageConsumeService();
//        messageConsumeService.SQSPoll();
//    }
}
