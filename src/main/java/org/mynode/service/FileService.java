package org.mynode.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

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

    public String uploadFile(String bucketName, MultipartFile file) throws IOException{
        try{
            String uuid = UUID.randomUUID().toString();
            String originalFilename = file.getOriginalFilename();
            String newFilename = Files.getNameWithoutExtension(originalFilename) + uuid + "." + Files.getFileExtension(originalFilename);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());
            s3Client.putObject(bucketName, file.getOriginalFilename(), file.getInputStream(), objectMetadata);
            logger.info(String.format("The file name=%s was uploaded to bucket %s", file.getName(), bucketName));
            return newFilename;
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }
}