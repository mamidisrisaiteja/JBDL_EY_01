package com.training.service;

import com.training.entity.Address;
import com.training.entity.Customer;
import com.training.exception.CustomerServiceException.CustomerServiceException;
import com.training.repository.AddressRepository;
import com.training.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomerService {
    @Autowired
    private  CustomerRepository customerRepository;
    @Autowired
    private  AddressRepository addressRepositoryy;
//@Autowired
//    public CustomerService(CustomerRepository customerRepository, AddressRepository addressRepositoryy) {
//        this.customerRepository = customerRepository;
//
//        this.addressRepositoryy = addressRepositoryy;
//    }

    public int register(Customer customer){

        //first check if customer has already registered

      Optional<Customer> optionalCustomer=  customerRepository.findByEmail(customer.getEmail());

   if(optionalCustomer.isPresent())
       throw new CustomerServiceException("Customer already registered");
   else {

       Address address=customer.getAddress();
       address.setCustomer(customer);
       customerRepository.save(customer);

       return customer.getId();
       //send a welcome mail
   }
    }
}
