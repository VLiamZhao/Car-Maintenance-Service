package org.mynode.service;

import io.jsonwebtoken.Claims;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mynode.init.ApplicationBootstrap;
import org.mynode.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class JWTServiceTest {
    @Autowired
    JWTService jwtService;

    //passed
    @Test
    public void generateTokenTest(){
        Customer customer = new Customer();
        customer.setId(100L);
        customer.setName("CZ");
        String token = jwtService.generateToken(customer);
//        Assert.assertNotNull(token);
        // TODO use regular expression
        String[] arr = token.split("\\.");
        Assert.assertEquals(arr.length, 3);
    }
    //passed
    @Test
    public void decodeTokenTest(){
        Customer customer = new Customer();
        customer.setId(100L);
        customer.setName("CZ");
        String token = jwtService.generateToken(customer);
        Claims c = jwtService.decodeJwtToken(token);
        String username = c.getSubject();
        Assert.assertEquals(customer.getName(), username);
    }
}
