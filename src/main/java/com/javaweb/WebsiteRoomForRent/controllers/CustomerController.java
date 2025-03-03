package com.javaweb.WebsiteRoomForRent.controllers;

import com.javaweb.WebsiteRoomForRent.dtos.CustomerDTO;
import com.javaweb.WebsiteRoomForRent.repository.CustomerRepository;
import com.javaweb.WebsiteRoomForRent.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@CrossOrigin(origins = "*")
@Transactional
@RestController
@RequestMapping("${api.prefix}/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

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

    @DeleteMapping("/delete-customer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
        try {
            customerService.DeleteCustomer(id);
            return ResponseEntity.ok("Customer deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countCustomers() {
        try {
            long count = customerService.countCustomers();
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
