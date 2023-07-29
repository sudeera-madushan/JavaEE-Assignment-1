package com.sudeera.backend.dto;

import com.sudeera.backend.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrderDTO {
    private int id;
    private Date date;
    private CustomerDTO customer;
    private List<OrderDetailsDTO> orderDetailsDTOS=new ArrayList<>();
    private double total;
    private double discount;
}
