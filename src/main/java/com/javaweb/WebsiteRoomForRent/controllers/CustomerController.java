package com.javaweb.WebsiteRoomForRent.controllers;

import com.javaweb.WebsiteRoomForRent.repository.CustomerRepository;
import com.javaweb.WebsiteRoomForRent.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> getCustomer() {
        return ResponseEntity.ok(customerService.findAllCustomer());
    }
}
