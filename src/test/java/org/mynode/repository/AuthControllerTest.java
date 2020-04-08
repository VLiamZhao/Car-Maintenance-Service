package org.mynode.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mynode.init.ApplicationBootstrap;
import org.mynode.model.Customer;
import org.mynode.service.CustomerService;
import org.mynode.service.JWTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class AuthControllerTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
    String email = "js@gmail.com";
    String pw = "25f9e794323b453885f5181f1b624d0b";

    @Autowired
    CustomerService customerService;
    @Autowired
    JWTService jwtService;

//    @Before
//    public void init(){
//        c = new Customer("ab");
//        c.setEmail(email);
//        c.setPassword(pw);
//        c = customerService.save(c);
//        Assert.assertNotNull(c);
//    }
//
//    @After
//    public void tearDown(){
//        Assert.assertEquals(customerService.deleteCustomerById(c.getId()), true);
//    }

    @Test
    public void getCustomerByCredentials() throws Exception {
        Customer c1 = customerService.getCustomerByCredentials(email, pw);
        Assert.assertNotNull(c1);
    }


}
