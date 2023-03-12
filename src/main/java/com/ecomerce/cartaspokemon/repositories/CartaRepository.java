package com.ecomerce.cartaspokemon.repositories;

import com.ecomerce.cartaspokemon.models.CartaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CartaRepository extends JpaRepository<CartaModel, UUID> {

    public boolean existsByNomePokemonAndRaridadeCarta(String nomePokemon, String raridadeCarta);

    public List<CartaModel> findAllByNomePokemon(String nomePokemon);

}