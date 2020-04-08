package org.mynode.controller;

import org.mynode.model.Customer;
import org.mynode.service.CustomerService;
import org.mynode.service.JWTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/auth"}, method = RequestMethod.POST)
public class AuthController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CustomerService customerService;
    @Autowired
    private JWTService jwtService;
    @RequestMapping(value="", method = RequestMethod.POST)
    public String userLogin(@RequestParam("username") String emailOrUsername, @RequestParam("password") String password){
        try{
            Customer c = customerService.getCustomerByCredentials(emailOrUsername, password);
            return jwtService.generateToken(c);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
