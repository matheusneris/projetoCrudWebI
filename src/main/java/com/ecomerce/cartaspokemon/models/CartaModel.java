package com.ecomerce.cartaspokemon.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "cartas")
@Getter
@Setter
public class CartaModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @Column(nullable = false)
    private String nomePokemon;

    @Column
    private String raridadeCarta;

    @Column(nullable = false)
    private BigDecimal precoVenda;

}