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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;

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
        List<CustomerEntity> customerEntityList = customerRepository.findAllByIsActive(1);
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

            UserEntity userEntity = userRepository.findById(2L).get();
            customerEntity.setUserid(userEntity);
            customerEntity.setStatus("CHUA_XU_LY");
            customerEntity.setIsActive(1);
            customerRepository.save(customerEntity);
        }
    }

    @Override
    public void DeleteCustomer(Long id){
        CustomerEntity customer = customerRepository.findById(id).get();
        customer.setIsActive(0);
    }

    @Override
    public long countCustomers() {
        return customerRepository.count();
    }
}
