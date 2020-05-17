package org.mynode.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mynode.init.ApplicationBootstrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class MessageServiceTest {
    @Autowired
    private MessageService messageService;
    @Autowired
    private AmazonSQS amazonSQS;


    @Test
    public void sendMessageTest(){
        when(amazonSQS.getQueueUrl(anyString())).thenReturn(mock(GetQueueUrlResult.class));
        messageService.sendMessage("hello",1);
        verify(amazonSQS,times(1)).sendMessage(any(SendMessageRequest.class));
    }
}
