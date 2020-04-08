package org.mynode.controller;

import org.mynode.model.Customer;
import org.mynode.service.CustomerService;
import org.mynode.service.JWTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = {"/customer"})
public class CustomerController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    CustomerService customerService;
    @Autowired
    JWTService jwtService;
    /**
     * GET {prefix}/customer
     * @param
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    /**
     * POST {prefix}/customer
     * @param
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Customer createCustomer(@RequestBody Customer customer){
        Customer cu = customerService.save(customer);
        return cu;
    }

    /**
     * GET {prefix}/customer/{id}
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Customer getCustomerById(@PathVariable long id){
        Customer customer = customerService.getCustomerById(id);
        return customer;
    }

    /**
     * GET {prefix}/customer/eager?id=inputId
     * @param inputId
     * @return
     */
    @RequestMapping(value = "/eager", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Customer getCustomerEagerBy(@RequestParam(name = "id") long inputId){
        Customer customer = customerService.getCustomerEagerBy(inputId);
        return customer;
    }

     /**
     * DELETE /customer/{id}
     *
     * @param id
     * @return
     */
     @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
     public boolean deleteCustomerById(@PathVariable(name = "id") long id) {
         return customerService.deleteCustomerById(id);
     }

    /**
     * PUT {prefix}/customer/{id}?name={name}
     * @param
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Customer updateCustomer(@PathVariable("id") long id, @RequestParam("name") String name) {
        Customer c = customerService.getCustomerById(id);
        c.setName(name);
        return c;
    }
    /**
     * Post {prefix}/customer/auth?name={name}&password={password}
     * @param
     * @return
     */
    @RequestMapping(value = "/auth", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String userLogin(@RequestParam("username") String emailOrUsername, @RequestParam("password") String password) {
        try {
            Customer c = customerService.getUserByCredentials(emailOrUsername, password);
            return jwtService.generateToken(c);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

}
