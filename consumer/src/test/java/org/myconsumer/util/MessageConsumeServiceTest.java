//package org.myconsumer.service;
//
//import com.amazonaws.services.sqs.AmazonSQS;
//import com.amazonaws.services.sqs.model.ReceiveMessageResult;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.myconsumer.init.AWSSQSApplication;
//import org.myconsumer.serviceTest.MessageConsumeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.mock;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = AWSSQSApplication.class)
//public class MessageConsumeServiceTest {
//    @Autowired
//    MessageConsumeService messageConsumeService;
//    @Autowired
//    AmazonSQS sqs;
////            = AmazonSQSClientBuilder.defaultClient();
//    @Test
//    public void SQSPollTest(){
////        when(sqs.getQueueUrl(anyString()).getQueueUrl()).thenReturn(anyString());
////        when(sqs.receiveMessage(anyString())).thenReturn(mock(ReceiveMessageResult.class));
//        messageConsumeService.consumeMessage();
//        verify(sqs, times(1)).receiveMessage(anyString());
//        verify(sqs, times(1)).deleteMessage(anyString(), anyString());
//    }
//}
//
