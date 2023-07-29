package com.sudeera.backend.service.custom;

import com.sudeera.backend.dto.OrderDTO;
import com.sudeera.backend.service.SuperService;

public interface OrderService extends SuperService {
    boolean saveOrder(OrderDTO orderDTO);
}
