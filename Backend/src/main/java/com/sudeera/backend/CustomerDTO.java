package com.sudeera.backend;

import com.sudeera.backend.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class CustomerDTO {
    private int id;
    private String name;
    private String address;
    private String mobile;
    private Double salary;
    private List<Orders> ordersList=new ArrayList<>();
}
