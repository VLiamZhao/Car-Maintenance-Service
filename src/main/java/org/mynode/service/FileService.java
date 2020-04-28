package org.mynode.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileService {
    String defaultRegion = "us-east-1";
    String bucketName = "canzhao";
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    AmazonS3 s3Client;

    public void uploadFile(File f){
        if (f != null) {
            PutObjectRequest request = new PutObjectRequest(bucketName, f.getName(), f);
            s3Client.putObject(request);
        }else{
            logger.error("Cannot upload null file!");
        }
    }

    public String getUrl(String s3Key){
        return s3Client.getUrl(bucketName, s3Key).toString();
    }
}
