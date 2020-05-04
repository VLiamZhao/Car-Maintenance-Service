package org.mynode.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.mynode.model.Customer;
import org.mynode.model.Role;
import org.mynode.model.view.JsView;
import org.mynode.repository.RoleDao;
import org.mynode.service.CustomerService;
import org.mynode.service.JWTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = {"/auth"}, method = RequestMethod.POST)
public class AuthController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CustomerService customerService;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private JWTService jwtService;

    @RequestMapping(value="", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> userLogin(@RequestBody Customer c){
        try{
            Customer cus;
            if (c.getName() != null){
                cus = customerService.getCustomerByCredentials(c.getName(), c.getPassword());
            } else {
                cus = customerService.getCustomerByCredentials(c.getEmail(), c.getPassword());
            }
            assert(cus != null);
            String token = jwtService.generateToken(cus);
            Map<String, String> m = new HashMap<>();
            m.put("token",token);
            return ResponseEntity.ok().body(m);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    // "token":"xxxxxxx"

    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    public ResponseEntity<String> userSignUp(@RequestBody Customer customer){
        try{
            List<Role> roles=new ArrayList<>();
            Role r=roleDao.getRoleByName(Role.baseRole);
            roles.add(r);
            customer.setRoleList(roles);
            Customer c=customerService.save(customer);
            if(c==null) return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).build();
//            return ResponseEntity.status(HttpServletResponse.SC_OK).body(c);
            return ResponseEntity.status(HttpServletResponse.SC_OK).body("Registration is successfully finished!");
        } catch (Exception e){
            e.printStackTrace();
        }
//        return ResponseEntity.ok(customer);
        return null;
    }
}
