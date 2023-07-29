package com.sudeera.backend.service.custom.impl;

import com.sudeera.backend.CustomerDTO;
import com.sudeera.backend.FactoryConfiguration;
import com.sudeera.backend.dao.custom.CustomerDAO;
import com.sudeera.backend.dao.util.DaoFactory;
import com.sudeera.backend.dao.util.DaoTypes;
import com.sudeera.backend.entity.Customer;
import com.sudeera.backend.service.custom.CustomerService;
import com.sudeera.backend.service.util.Convertor;
import org.hibernate.Session;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAO customerDAO;
    private final Convertor convertor;
    private final Session session;

    public CustomerServiceImpl() {
        session = FactoryConfiguration.getInstance().getSession();
        convertor=new Convertor();
        customerDAO= DaoFactory.getInstance().getDAO(session, DaoTypes.CUSTOMER);
    }
    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) {
        Customer customer = customerDAO.save(convertor.toCustomer(customerDTO));
        if (customer==null){
            return false;
        }
        return true;
    }
    @Override
    public boolean deleteCustomer(int id) {
        Customer customer = customerDAO.delete();
        if (customer==null){
            return false;
        }
        return true;
    }
    @Override
    public CustomerDTO getCustomer(int id) {
        Customer customer = customerDAO.findByPk(id);
        if (customer==null){
            return null;
        }
        return convertor.fromCustomer(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerDAO.getAll().stream().map(customer -> convertor.fromCustomer(customer)).collect(Collectors.toList());
    }
}
