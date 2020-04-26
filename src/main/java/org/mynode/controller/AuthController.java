package org.mynode.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
import java.util.List;

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
    @JsonView(JsView.Anonymous.class)
    public ResponseEntity<String> userLogin(@RequestBody Customer c){
        try{
            Customer cus;
            if (c.getName() != null){
                cus = customerService.getCustomerByCredentials(c.getName(), c.getPassword());
            } else {
                cus = customerService.getCustomerByCredentials(c.getEmail(), c.getPassword());
            }
            assert(cus != null);
            String token = jwtService.generateToken(cus);
            String jsonStr = "{\"token\":"+ token + "}" ;
            JsonObject jsonObject = new JsonParser().parse(jsonStr).getAsJsonObject();
            return ResponseEntity.ok().body(jsonObject.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    // "token":"xxxxxxx"

    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    @JsonView(JsView.Anonymous.class)
    public ResponseEntity<?> userSignUp(@RequestBody Customer customer){
        try{
            List<Role> roles=new ArrayList<>();
            Role r=roleDao.getRoleById(2L);
            roles.add(r);
            customer.setRoleList(roles);
            Customer c=customerService.save(customer);
            if(c==null) return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).build();
            return ResponseEntity.status(HttpServletResponse.SC_OK).body(c);
        } catch (Exception e){
            e.printStackTrace();
        }
//        return ResponseEntity.ok(customer);
        return null;
    }
}
