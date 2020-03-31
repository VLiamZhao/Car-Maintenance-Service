package org.mynode.repository;

import org.mynode.model.Customer;

import java.util.List;

public interface CustomerDao {
    Customer getCustomerEagerBy(long id);
    List<Customer> getCustomers();
    Customer getCustomerById(long id);
    Customer save(Customer customer);
    boolean deleteById(long id);
//    boolean updateCustomer(Customer customer);
}
