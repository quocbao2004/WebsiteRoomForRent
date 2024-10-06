package com.javaweb.WebsiteRoomForRent.services;

import com.javaweb.WebsiteRoomForRent.responses.CustomerSearchResponse;

import java.util.List;

public interface CustomerService {
    List<CustomerSearchResponse> findAllCustomer();
}
