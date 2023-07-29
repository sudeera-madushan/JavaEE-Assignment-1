package com.sudeera.backend.service;


import com.sudeera.backend.service.custom.impl.CustomerServiceImpl;
import com.sudeera.backend.service.custom.impl.ItemServiceImpl;
import com.sudeera.backend.service.custom.impl.OrderServiceImpl;

public class ServiceFactory {

    private static ServiceFactory serviceFactory;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance(){
        return serviceFactory==null?(serviceFactory=new ServiceFactory()):serviceFactory;
    }

    public <T extends SuperService> T getService(ServiceTypes serviceTypes){
        switch (serviceTypes){
            case CUSTOMER:
                return (T)new CustomerServiceImpl();
            case ITEM:
                return (T)new ItemServiceImpl();
            case ORDER:
                return (T)new OrderServiceImpl();
            default:
                return null;
        }
    }

}
