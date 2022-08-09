package com.cognizant.balanceCRUD.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "balance")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "location_id")
    private int locationId;

    @Column(name = "quantity")
    private int quantity;
}
