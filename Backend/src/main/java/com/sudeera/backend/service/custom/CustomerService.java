package com.sudeera.backend.service.custom;

import com.sudeera.backend.CustomerDTO;
import com.sudeera.backend.service.SuperService;

import java.util.List;

public interface CustomerService extends SuperService {
    public boolean saveCustomer(CustomerDTO customerDTO);

    CustomerDTO getCustomer(int id);

    List<CustomerDTO> getAllCustomers();
}
