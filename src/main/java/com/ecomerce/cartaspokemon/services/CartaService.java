package com.ecomerce.cartaspokemon.services;

import com.ecomerce.cartaspokemon.models.CartaModel;
import com.ecomerce.cartaspokemon.repositories.CartaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartaService {

    @Autowired
    CartaRepository cartaRepository;

    public CartaModel salvarCarta(CartaModel cartaModel) {
        if(cartaModel.getRaridadeCarta() == null){
            cartaModel.setRaridadeCarta("COMUM");
            cartaModel.setNomePokemon(cartaModel.getNomePokemon().toUpperCase());
        }else {
            cartaModel.setNomePokemon(cartaModel.getNomePokemon().toUpperCase());
            cartaModel.setRaridadeCarta(cartaModel.getRaridadeCarta().toUpperCase());
        }
        return cartaRepository.save(cartaModel);
    }

    public List<CartaModel> buscarPokemonPorNome(String nomePokemon){
        return cartaRepository.findAllByNomePokemon(nomePokemon);
    }

    public boolean existsByNomePokemonAndRaridadeCarta(String nomePokemon, String raridadeCarta){
        return cartaRepository.existsByNomePokemonAndRaridadeCarta(nomePokemon, raridadeCarta);
    }

    public Optional<CartaModel> buscarPorId(UUID uuid){
        return cartaRepository.findById(uuid);
    }

    public List<CartaModel> buscarTodasCartas(){
        return cartaRepository.findAll();
    }

    public void deletarPorId(UUID uuid){
        cartaRepository.deleteById(uuid);
    }
}

