package com.javaweb.WebsiteRoomForRent.controllers;

import com.javaweb.WebsiteRoomForRent.dtos.CustomerDTO;
import com.javaweb.WebsiteRoomForRent.repository.CustomerRepository;
import com.javaweb.WebsiteRoomForRent.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@Transactional
@RestController
@RequestMapping("${api.prefix}/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public ResponseEntity<?> getCustomer() {
        return ResponseEntity.ok(customerService.findAllCustomer());
    }

    @PostMapping("/add-customer")
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            customerService.addNewCustomer(customerDTO);
            return ResponseEntity.ok("Customer added successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
