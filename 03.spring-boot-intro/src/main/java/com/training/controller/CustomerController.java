package com.training.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.training.dto.CustomerDetails;
import com.training.dto.RegisterStatus;
import com.training.entity.Customer;
import com.training.exception.CustomerServiceException.CustomerServiceException;
import com.training.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/customer/register")
    public RegisterStatus register(@RequestBody Customer customer){
try{
    int id=customerService.register(customer);
    RegisterStatus status=new RegisterStatus();
    status.setStatus(true);
    status.setStatusMessage("Customer Registered");
    status.setRegisteredCustomerId(id);
return status;
}
catch(CustomerServiceException ce){
    RegisterStatus status=new RegisterStatus();
    status.setStatus(false);
    status.setStatusMessage(ce.getMessage());
    return status;

}
    }
    @PostMapping(path="/customer/registerV2",consumes = "multipart/form-data")
    public RegisterStatus registerV2(CustomerDetails customerDetails){
        try{

            //copy data from DTO to entity
            Customer customer=new Customer();
            BeanUtils.copyProperties(customerDetails,customer);

            //store the  file to be uploaded

            try{
                String fileName=customerDetails.getProfilePic().getOriginalFilename();
                String generatedFile=fileName;//code to generate unique name for the file

                //this generated file name should be passed to data base thru entity class(customer entity)
                customer.setProfilePic(generatedFile);

                InputStream is=customerDetails.getProfilePic().getInputStream();
                FileOutputStream os=new FileOutputStream("C:/uploads/"+generatedFile);
                FileCopyUtils.copy(is,os);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            int id=customerService.register(customer);
            RegisterStatus status=new RegisterStatus();
            status.setStatus(true);
            status.setStatusMessage("Customer Registered");
            status.setRegisteredCustomerId(id);
            return status;
        }
        catch(CustomerServiceException ce){
            RegisterStatus status=new RegisterStatus();
            status.setStatus(false);
            status.setStatusMessage(ce.getMessage());
            return status;

        }
    }
    @PostMapping(path="/customer/registerV3",consumes = "multipart/form-data")
    public RegisterStatus registerV3(@RequestPart("customer") String customerData,
                                     @RequestPart("profilePic") MultipartFile file) {
        try {

            //copy data from DTO to entity
            Customer customer = new ObjectMapper().readValue(customerData,Customer.class);


            //store the  file to be uploaded

            try {
                String fileName = file.getOriginalFilename();
                String generatedFile = fileName;//code to generate unique name for the file

                //this generated file name should be passed to data base thru entity class(customer entity)
                customer.setProfilePic(generatedFile);

                InputStream is = file.getInputStream();
                FileOutputStream os = new FileOutputStream("C:/uploads/" + generatedFile);
                FileCopyUtils.copy(is, os);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            int id = customerService.register(customer);
            RegisterStatus status = new RegisterStatus();
            status.setStatus(true);
            status.setStatusMessage("Customer Registered");
            status.setRegisteredCustomerId(id);
            return status;
        } catch (CustomerServiceException ce) {
            RegisterStatus status = new RegisterStatus();
            status.setStatus(false);
            status.setStatusMessage(ce.getMessage());
            return status;

        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
