package com.ecomerce.cartaspokemon.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
public class CartaDto {

    @NotBlank
    private String nomePokemon;
    private String raridadeCarta;
    private BigDecimal precoVenda;

}
