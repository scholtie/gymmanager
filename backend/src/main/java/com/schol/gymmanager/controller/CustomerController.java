package com.schol.gymmanager.controller;

import com.schol.gymmanager.exception.EmailExistsException;
import com.schol.gymmanager.model.user.BaseUser;
import com.schol.gymmanager.model.user.Customer;
import com.schol.gymmanager.model.DTOs.CustomerDto;
import com.schol.gymmanager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public List<Customer> findAll() {
        List<Customer> customers = customerService.findAll();
//        for (Customer customer : customers) {
//            //addLinks(customer);
//        }
        return customers;
    }

    @PostMapping("/")
    public Customer create(@RequestBody CustomerDto customerDTO) throws EmailExistsException {
        Customer customer = customerService.create(customerDTO);
        //addLinks(customer);
        return customer;
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        //addLinks(customer);
        return customer;
    }

    @PutMapping("/{id}")
    public Customer update(@RequestBody Customer newUser, @PathVariable Long id) {
        Customer customer = customerService.update(newUser, id);
        //addLinks(customer);
        return customer;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }

    @GetMapping("/loggedInUser")
    public Optional<BaseUser> getLoggedInBaseUser(){
        return customerService.getLoggedInBaseUser();
    }

//    private void addLinks(Customer customer) {
//        customer.add(linkTo(methodOn(CustomerController.class).findById(customer.getId())).withSelfRel());
//    }
}
