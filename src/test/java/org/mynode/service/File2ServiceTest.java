package org.mynode.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mynode.init.ApplicationBootstrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class File2ServiceTest {
    @Autowired
    File2Service file2Service;

    @Test
    public void uploadFileTest(){
        File f = new File("/home/can/b.txt");
        file2Service.uploadFile(f);
    }
}
