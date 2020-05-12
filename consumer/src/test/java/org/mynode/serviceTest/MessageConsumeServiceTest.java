package org.mynode.serviceTest;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mynode.init.AWSSQSApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AWSSQSApplication.class)
public class MessageConsumeServiceTest {
    @Value("${aws.sqs.name}")
    private String queueName;
    @Autowired
    MessageConsumeService messageConsumeService;
    @Autowired
    AmazonSQS sqs;
//            = AmazonSQSClientBuilder.defaultClient();
    @Test
    public void SQSPollTest(){
        when(sqs.getQueueUrl(anyString()).getQueueUrl()).thenReturn(anyString());
        when(sqs.receiveMessage(anyString())).thenReturn(mock(ReceiveMessageResult.class));
        messageConsumeService.SQSPoll();
        verify(sqs, times(1)).receiveMessage(anyString());
        verify(sqs, times(1)).deleteMessage(anyString(), anyString());
    }
}

