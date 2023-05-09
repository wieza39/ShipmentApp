package com.app.shipment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    //@Pattern(regexp = "^[A-Z]\\d[A-Z]\\d{6}$")
    @Column(name = "SKU", unique = true)
    private String sku;

    @PositiveOrZero
    @Column(name = "quantity")
    private int quantity;

    @PositiveOrZero
    @Column(name = "weight")
    private float weight;

    @PositiveOrZero
    @Column(name = "price")
    private float price;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "warehouse")
    private Warehouse warehouse;
}
