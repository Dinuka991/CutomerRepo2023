package com.example.customer.service;

import com.example.customer.entity.Customer;
import com.example.customer.exception.CustomerInActiveException;
import com.example.customer.exception.CustomerNotFoundException;
import com.example.customer.repository.CustomerRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Customer  saveCustomer(Customer cs){
        return customerRepository.save(cs);
    }

    @Async
    public List<Customer> getAll(){
        return  customerRepository.findAll();
    }

    public Customer findByName(String name){
        Optional<Customer> customer =  customerRepository.findByName(name);
        if(customer.isEmpty()){
            throw new CustomerNotFoundException("Customer Not Found with Name :" + name);
        }
        if(!customer.get().isActive()){
            throw new CustomerInActiveException("Customer not active yet :"+ name);
        }
        return customer.get();
    }

    public Customer  updateCustomerStatus(Long id,Customer customer){

        Optional<Customer> existingCustomer =  customerRepository.findById(id);
        if(existingCustomer.isEmpty()){
            throw new CustomerNotFoundException("Customer Not Found with id :" + id);
        }

        existingCustomer.get().setActive(customer.isActive());
        return  customerRepository.save(existingCustomer.get());
    }
}
