package org.mynode.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mynode.init.ApplicationBootstrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class FileServiceTest {
    @Autowired
    FileService fileService;
    @Autowired
    AmazonS3 s3Client;

    @Test
    public void uploadFileTest(){
//        AmazonS3 s3Client = Mockito.mock(AmazonS3.class);
//        s3Client.putObject("XXX", "XXX", "string of object");
//        verify(s3Client, times(1)).putObject(anyString(), anyString(), anyString());
//        File testFile = new File("/home/can/a.txt");
        File testFile = Mockito.mock(File.class);
        fileService.uploadFile(testFile);
        verify(s3Client,times(1)).putObject(any(PutObjectRequest.class));
    }

//    @Test
//    public void getUrlTest() throws MalformedURLException {
//        when(s3Client.getUrl(anyString(),anyString())).thenReturn(new URL("http", "ascending.com"));
//        file2Service.getUrl("a.txt");
//        verify(s3Client,times(1)).putObject(any(PutObjectRequest.class));
//    }
    @Test // stub test
    public void uploadFileStubTest(){
        File testFile = Mockito.mock(File.class);
        when(testFile.getName()).thenReturn("filename.pdf");  // The getName method will always return "filename.pdf"
        fileService.uploadFile(testFile);
        verify(s3Client,times(1)).putObject(any(PutObjectRequest.class));
    }

    @Test
    public void getUrlTest() throws MalformedURLException {
        when(s3Client.getUrl(anyString(),anyString())).thenReturn(new URL("http","xxx", 123, "xxx"));
        fileService.getUrl("Zhang3");
        verify(s3Client, times(1)).getUrl(anyString(), anyString());
    }
}
