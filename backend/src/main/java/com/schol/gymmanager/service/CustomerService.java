package com.schol.gymmanager.service;

import com.schol.gymmanager.exception.EmailExistsException;
import com.schol.gymmanager.exception.EntityNotFoundException;
import com.schol.gymmanager.model.DTOs.CustomerDto;
import com.schol.gymmanager.model.enums.Gender;
import com.schol.gymmanager.model.user.BaseUser;
import com.schol.gymmanager.model.user.Customer;
import com.schol.gymmanager.repository.BaseUserRepository;
import com.schol.gymmanager.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AuthService authService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, AuthService authService) {
        this.customerRepository = customerRepository;
        this.authService = authService;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer create(CustomerDto customerDto) throws EmailExistsException {
        Customer customerToSave = new Customer();
        customerToSave.setGender(Gender.valueOf(customerDto.getGender()));
        customerToSave.setLastName(customerDto.getLastName());
        customerToSave.setFirstName(customerDto.getFirstName());
        authService.getLoggedInUser().ifPresent(customerToSave::setBaseUser);
        return customerRepository.save(customerToSave);
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer", id));
    }

    public Customer update(Customer newUser,Long id) {
        return customerRepository.findById(id)
                .map(customerRepository::save)
                .orElseGet(() -> {
                    newUser.setId(id);
                    return customerRepository.save(newUser);
                });
    }

    public Optional<Customer> getLoggedInCustomer() {
        return authService.getLoggedInUser()
                .flatMap(customerRepository::findByBaseUser);
    }

    public Optional<BaseUser> getLoggedInBaseUser() {
        return authService.getLoggedInUser();
    }

    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer findByBaseUser(BaseUser baseUser) {
        return customerRepository.findByBaseUser(baseUser)
                .orElseThrow(() -> new EntityNotFoundException("Customer with BaseUser", baseUser.getId()));
    }
}
