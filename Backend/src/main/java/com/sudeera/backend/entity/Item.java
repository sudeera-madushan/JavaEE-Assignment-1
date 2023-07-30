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
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "item")
public class Item extends SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "qty")
    private double qtyOnHand;
    @Column(name = "price")
    private double unitPrice;
    @OneToMany(mappedBy = "item", targetEntity = OrderDetails.class)
    private List<OrderDetails> orderDetailsList=new ArrayList<>();

    public Item(int id) {
        this.id = id;
    }
}
