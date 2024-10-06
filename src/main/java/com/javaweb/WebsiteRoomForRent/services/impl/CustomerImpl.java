package com.javaweb.WebsiteRoomForRent.services.impl;

import com.javaweb.WebsiteRoomForRent.dtos.CustomerDTO;
import com.javaweb.WebsiteRoomForRent.entities.CustomerEntity;
import com.javaweb.WebsiteRoomForRent.repository.CustomerRepository;
import com.javaweb.WebsiteRoomForRent.responses.CustomerSearchResponse;
import com.javaweb.WebsiteRoomForRent.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CustomerSearchResponse> findAllCustomer(){
        List<CustomerEntity> customerEntityList = customerRepository.findAll();
        List<CustomerSearchResponse> customerSearchResponseList = new ArrayList<>();
        for (CustomerEntity customerEntity : customerEntityList) {
            customerSearchResponseList.add(modelMapper.map(customerEntity, CustomerSearchResponse.class));
        }
        return customerSearchResponseList;
    }
}
