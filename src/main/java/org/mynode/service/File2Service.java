package org.mynode.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class File2Service {
    String defaultRegion = "us-east-1";
    String bucketName = "canzhao";
    @Autowired
    AmazonS3 s3Client;
    public void uploadFile(File f){
        PutObjectRequest request = new PutObjectRequest(bucketName, f.getName(), f);
        s3Client.putObject(request);
    }
}
