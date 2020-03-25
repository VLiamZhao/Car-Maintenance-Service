package org.mynode.controller;

import org.mynode.model.Customer;
import org.mynode.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping(value = {"/customer"})
public class CustomerController {
    @Autowired
    CustomerService customerService;

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
     * POST {prefix}/customer/body
     * @param customer
     * @return
     */
    @RequestMapping(value = "/body", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
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

    
}
