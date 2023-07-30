package com.sudeera.backend.entity;

import com.sudeera.backend.dao.util.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Table(name = "customer")
public class Customer extends SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "salary")
    private double salary;
    @OneToMany(mappedBy = "customer",targetEntity = Orders.class)
    private List<Orders> ordersList=new ArrayList<>();

    public Customer(int id) {
        this.id = id;
    }
}
