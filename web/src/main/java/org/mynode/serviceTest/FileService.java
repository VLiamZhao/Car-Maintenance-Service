package org.mynode.serviceTest;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {
    ///TODO REMOVE THE sTRING... - done
    @Value("${aws.s3.bucket}")
    private String bucketName;

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    AmazonS3 s3Client;

    public String uploadFile(File f){
        if (f != null) {
            PutObjectRequest request = new PutObjectRequest(bucketName, f.getName(), f);
            s3Client.putObject(request);
        }else{
            logger.error("Cannot upload null file!");
        }
        return null;
    }

    public String getUrl(String s3Key){
        return s3Client.getUrl(bucketName, s3Key).toString();
    }

    public String uploadFile(MultipartFile file) throws IOException{
        try{
            String uuid = UUID.randomUUID().toString();
            String originalFilename = file.getOriginalFilename();
            String newFilename = Files.getNameWithoutExtension(originalFilename) + uuid + "." + Files.getFileExtension(originalFilename);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());
            s3Client.putObject(bucketName, newFilename, file.getInputStream(), objectMetadata);
            logger.info(String.format("The file name=%s was uploaded to bucket %s", file.getName(), bucketName));
            return newFilename;
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }
}
