package com.sudeera.backend.service.custom.impl;

import com.sudeera.backend.FactoryConfiguration;
import com.sudeera.backend.dao.custom.OrdersDAO;
import com.sudeera.backend.dao.util.DaoFactory;
import com.sudeera.backend.dao.util.DaoTypes;
import com.sudeera.backend.dto.OrderDTO;
import com.sudeera.backend.entity.Orders;
import com.sudeera.backend.service.custom.OrderService;
import com.sudeera.backend.service.util.Convertor;
import org.hibernate.Session;

public class OrderServiceImpl implements OrderService {

    private final Session session;
    private final Convertor convertor;
    private OrdersDAO ordersDAO;

    public OrderServiceImpl() {
        session = FactoryConfiguration.getInstance().getSession();
        convertor=new Convertor();
        ordersDAO= DaoFactory.getInstance().getDAO(session, DaoTypes.ORDER);
    }

    @Override
    public boolean saveOrder(OrderDTO orderDTO) {
        Orders orders = ordersDAO.save(convertor.toOrder(orderDTO));
        return true;
    }
}
