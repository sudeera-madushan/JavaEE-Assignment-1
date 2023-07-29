package com.sudeera.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ItemDTO {
    private int id;
    private String name;
    private double qty;
    private double price;
    private List<OrderDetailsDTO> orderDetails=new ArrayList<>();
}
