package org.mynode.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.stereotype.Service;

@Service
public class File2Service {
    public void uploadFile(){
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion("us-east-1")
                .build();
        s3Client.putObject("canzhao", "sample.txt", "Uploaded String Object");
    }
}
