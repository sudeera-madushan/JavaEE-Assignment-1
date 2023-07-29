package com.sudeera.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderDetailsDTO {
    private int id;
    private OrderDTO orderDTO;
    private ItemDTO itemDTO;
    private double qty;
    private double unitPrice;
}
