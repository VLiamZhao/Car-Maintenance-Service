package org.mynode.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mynode.init.ApplicationBootstrap;
import org.mynode.model.Customer;
import org.mynode.model.Role;
import org.mynode.serviceTest.CustomerService;
import org.mynode.serviceTest.JWTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class AuthControllerTest {
    private Logger logger = LoggerFactory.getLogger(getClass());
    String email = "joe@gmail.com";
    String pw = "202cb962ac59075b964b07152d234b70";

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
        List<Role> l = c1.getRoleList();
        for(Role r : l){

        }
        Assert.assertNotNull(c1);
    }


}
