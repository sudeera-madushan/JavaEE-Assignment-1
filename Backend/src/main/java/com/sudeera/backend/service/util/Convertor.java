package com.sudeera.backend.service.util;



import com.sudeera.backend.CustomerDTO;
import com.sudeera.backend.dto.ItemDTO;
import com.sudeera.backend.dto.OrderDTO;
import com.sudeera.backend.dto.OrderDetailsDTO;
import com.sudeera.backend.entity.Customer;
import com.sudeera.backend.entity.Item;
import com.sudeera.backend.entity.OrderDetails;
import com.sudeera.backend.entity.Orders;

import javax.persistence.criteria.Order;
import java.util.stream.Collectors;

public class Convertor {

    public CustomerDTO fromCustomer(Customer customer){
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress(), customer.getMobile(), customer.getSalary(),null);
    }

    public Customer toCustomer(CustomerDTO customer){
        return new Customer(customer.getId(), customer.getName(), customer.getAddress(), customer.getMobile(), customer.getSalary(),null);
    }


    public ItemDTO fromItem(Item item) {
        return new ItemDTO(item.getId(),item.getName(),item.getQtyOnHand(),item.getUnitPrice(),null);
    }
    public Item toItem(ItemDTO item) {
        return new Item(item.getId(),item.getName(),item.getQty(),item.getPrice(),null);
    }
    public Orders toOrder(OrderDTO orderDTO) {
        return new Orders(orderDTO.getId(),
                orderDTO.getDate(),
                toCustomer(orderDTO.getCustomer()),
                orderDTO.getOrderDetailsDTOS().stream().map(orderDetailsDTO ->
                        toOrdersDetails(orderDetailsDTO)).collect(Collectors.toList()),
                orderDTO.getTotal(), orderDTO.getDiscount());
    }

    private OrderDetails toOrdersDetails(OrderDetailsDTO orderDetailsDTO) {
        return new OrderDetails(orderDetailsDTO.getId(),
                new Orders(orderDetailsDTO.getOrderDTO().getId()),
                toItem(orderDetailsDTO.getItemDTO()),
                orderDetailsDTO.getQty(),
                orderDetailsDTO.getUnitPrice());
    }
}
