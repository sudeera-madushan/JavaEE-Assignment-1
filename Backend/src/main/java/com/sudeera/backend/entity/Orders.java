package com.sudeera.backend.entity;

import com.sudeera.backend.dao.util.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orders")
public class Orders extends SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;
    @OneToMany(mappedBy = "orders" , targetEntity = OrderDetails.class)
    private List<OrderDetails> orderDetailsList = new ArrayList<>();
    private double total;
    private double discount;

    public Orders(int id) {
        this.id = id;
    }
}
