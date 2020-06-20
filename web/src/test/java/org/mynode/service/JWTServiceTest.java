package org.mynode.service;

import io.jsonwebtoken.Claims;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mynode.init.ApplicationBootstrap;
import org.mynode.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class JWTServiceTest {
  @Autowired JWTService jwtService;
  private Logger logger = LoggerFactory.getLogger(getClass());
  // passed
  //    @Test
  //    public void generateTokenTest(){
  //        Customer customer = new Customer();
  //        customer.setId(100L);
  //        customer.setName("CZ");
  //        String token = jwtService.generateToken(customer);
  //    //        Assert.assertNotNull(token);
  //    // TODO use regular expression - done
  //    // String[] arr = token.split("\\.");
  //
  //        String patternString = "^[A-Za-z0-9_]+\\.[A-Za-z0-9_]+\\.?[A-Za-z0-9_]*$";
  //        Pattern pattern = Pattern.compile(patternString);
  //        Matcher matcher = pattern.matcher(token);
  //        Assert.assertTrue(token, matcher.matches());
  //    }
  // passed
  @Test
  public void decodeTokenTest() {
    Customer customer = new Customer();
    customer.setId(100L);
    customer.setName("CZ");
    String token = jwtService.generateToken(customer);
    Claims c = jwtService.decodeJwtToken(token);
    String username = c.getSubject();
    Assert.assertEquals(customer.getName(), username);
  }
}
