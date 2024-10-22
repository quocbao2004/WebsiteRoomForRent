package com.javaweb.WebsiteRoomForRent.services.impl;

import com.javaweb.WebsiteRoomForRent.dtos.CustomerDTO;
import com.javaweb.WebsiteRoomForRent.entities.CustomerEntity;
import com.javaweb.WebsiteRoomForRent.entities.UserEntity;
import com.javaweb.WebsiteRoomForRent.repository.CustomerRepository;
import com.javaweb.WebsiteRoomForRent.repository.UserRepository;
import com.javaweb.WebsiteRoomForRent.responses.CustomerSearchResponse;
import com.javaweb.WebsiteRoomForRent.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<CustomerSearchResponse> findAllCustomer(){
        List<CustomerEntity> customerEntityList = customerRepository.findAll();
        List<CustomerSearchResponse> customerSearchResponseList = new ArrayList<>();
        for (CustomerEntity customerEntity : customerEntityList) {
            customerSearchResponseList.add(modelMapper.map(customerEntity, CustomerSearchResponse.class));
        }
        return customerSearchResponseList;
    }

    @Override
    public void addNewCustomer(CustomerDTO customerDTO){
        if(customerDTO.getId() == null) {
            CustomerEntity customerEntity = modelMapper.map(customerDTO, CustomerEntity.class);
            UserEntity userEntity = userRepository.findByPhone("0865479500").get();
            customerEntity.setUserid(userEntity);
            customerRepository.save(customerEntity);
        } else {

        }
    }
}