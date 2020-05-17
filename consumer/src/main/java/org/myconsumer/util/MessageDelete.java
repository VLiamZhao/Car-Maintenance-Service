//package org.myconsumer.util;
//
//import com.amazonaws.services.sqs.AmazonSQS;
//import com.amazonaws.services.sqs.model.ReceiveMessageResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MessageDelete {
//  private static String queueName = System.getProperty("aws.sqs.name");
//  @Autowired AmazonSQS sqs;
//
//  public void deleteMessages() {
//    String queueUrl = sqs.getQueueUrl(queueName).getQueueUrl();
//    // recevie messages
//    ReceiveMessageResult messages = sqs.receiveMessage(queueUrl);
//
//    // delete messages from the queue
//    messages
//        .getMessages()
//        .forEach(
//            m -> {
//              sqs.deleteMessage(queueUrl, m.getReceiptHandle());
//            });
//  }
//      public static void main(String[] args){
//          MessageDelete messageDelete = new MessageDelete();
//          messageDelete.deleteMessages();
//      }
//
//}
