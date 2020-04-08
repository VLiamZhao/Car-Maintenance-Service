package org.mynode.service;

import org.mynode.model.Customer;
import org.mynode.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;

    public Customer getCustomerEagerBy(long id){
        return customerDao.getCustomerEagerBy(id);
    }

    public Customer getCustomerByName(String name){ return customerDao.getCustomerByName(name);}

    public List<Customer> getCustomers(){
        return customerDao.getCustomers();
    }

    public Customer getCustomerById(long id){
        return customerDao.getCustomerById(id);
    }

    public Customer save(Customer customer){
        return customerDao.save(customer);
    }

    public boolean deleteCustomerById(long id){
        return customerDao.deleteById(id);
    }

//    public boolean updateCustomer(Customer customer){return customerDao.updateCustomer(customer); }
    public Customer getCustomerByCredentials(String email, String password) throws Exception {
        return customerDao.getCustomerByCredentials(email, password);
    }
}
