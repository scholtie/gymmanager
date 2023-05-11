package com.schol.gymmanager;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.schol.gymmanager.model.user.Customer;
import com.schol.gymmanager.model.DTOs.CustomerDto;
import com.schol.gymmanager.repository.CustomerRepository;
import com.schol.gymmanager.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TestUtils testUtils;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void testCreate() {
        //GIVEN
        CustomerDto dto = testUtils.getTestCustomerDto();
        Customer expected = testUtils.getTestCustomer();
        //WHEN
        Customer actual = customerService.create(dto);
        when(passwordEncoder.encode(dto.getPassword())).thenReturn(dto.getPassword());
        //TRUE
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getUserName(), actual.getUserName());
    }




}
