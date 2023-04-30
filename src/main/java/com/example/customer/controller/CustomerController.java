package com.example.customer.controller;

import com.example.customer.entity.Customer;
import com.example.customer.entity.CustomerRegRequestDto;
import com.example.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController  {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping("/customer")
    public  ResponseEntity<Customer>  save(@RequestBody CustomerRegRequestDto customerRegRequestDto){

       Customer customer = Customer.builder().name(customerRegRequestDto.getName())
               .nic(customerRegRequestDto.getNic()).build();

        return  new ResponseEntity<>(customerService.saveCustomer(customer),HttpStatus.CREATED);
    }

    @GetMapping("/customers")
    public  ResponseEntity<List<Customer>> getAll(){
        return  new ResponseEntity<>(customerService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/customer/{name}")
    public  ResponseEntity<Customer> getCustomerNyName(String name){
        Customer customer  = customerService.findByName(name);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @PutMapping("/customer/{nic}")
    public  ResponseEntity<Customer> update(@PathVariable Long id,@RequestBody Customer customer){
        return  new ResponseEntity<>(customerService.updateCustomerStatus(id,customer),HttpStatus.CREATED);
    }

    }


