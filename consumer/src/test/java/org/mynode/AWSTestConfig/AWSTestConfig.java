package org.mynode.AWSTestConfig;


import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;


import static org.mockito.Mockito.mock;

@Configuration
@Profile("unit")
public class AWSTestConfig {
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)  //this is default option
    public AmazonS3 getAmazonS3() {return mock(AmazonS3.class);}

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AmazonSQS getAmazonSQS(){
        return mock(AmazonSQS.class);
//        return AmazonSQSClientBuilder
//                .standard()
//                .withCredentials(new DefaultAWSCredentialsProviderChain())
//                .build();
    }
}