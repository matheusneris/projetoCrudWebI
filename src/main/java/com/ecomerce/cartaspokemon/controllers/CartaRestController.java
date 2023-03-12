package com.ecomerce.cartaspokemon.controllers;

import com.ecomerce.cartaspokemon.dtos.CartaDto;
import com.ecomerce.cartaspokemon.models.CartaModel;
import com.ecomerce.cartaspokemon.services.CartaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/rest/cartas")
public class CartaRestController {

    @Autowired
    CartaService cartaService;

    @PostMapping("/inserir")
    public ResponseEntity<Object> salvarCarta(@RequestBody @Valid CartaDto cartaDto){
        if(cartaService.existsByNomePokemonAndRaridadeCarta(cartaDto.getNomePokemon(), cartaDto.getRaridadeCarta())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Carta já cadastrada");
        }
        if(cartaDto.getPrecoVenda() == null){
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body("É necessário enviar o valor de venda da carta.");
        }
        CartaModel cartaModel = new CartaModel();
        BeanUtils.copyProperties(cartaDto, cartaModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartaService.salvarCarta(cartaModel));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Object> buscarCartaPorId(@PathVariable(value = "id") UUID uuid){

        Optional<CartaModel> cartaModelOptional = cartaService.buscarPorId(uuid);
        if(cartaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(cartaModelOptional);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carta não encontrada.");
    }


    @GetMapping("/listartodas")
    public ResponseEntity<List<CartaModel>> buscarTodasCartas(){
        List<CartaModel> cartaModelList = cartaService.buscarTodasCartas();
        return ResponseEntity.status(HttpStatus.OK).body(cartaModelList);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object> deletarPorId(@PathVariable(value = "id")UUID uuid){

        Optional<CartaModel> cartaModelOptional = cartaService.buscarPorId(uuid);
        if(cartaModelOptional.isPresent()){
            cartaService.deletarPorId(uuid);
            return ResponseEntity.status(HttpStatus.OK).body(cartaModelOptional);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe uma carta com esse id.");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Object> editarCarta(@PathVariable(value = "id")UUID uuid, @RequestBody @Valid CartaDto cartaDto){

        Optional<CartaModel> cartaModelOptional = cartaService.buscarPorId(uuid);
        if(cartaModelOptional.isPresent()){
            CartaModel cartaModel = cartaModelOptional.get();
            cartaModel.setNomePokemon(cartaDto.getNomePokemon());
            cartaModel.setRaridadeCarta(cartaDto.getRaridadeCarta());
            cartaModel.setPrecoVenda(cartaDto.getPrecoVenda());

            cartaService.salvarCarta(cartaModel);
            return ResponseEntity.status(HttpStatus.OK).body(cartaModel);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existe uma carta com esse id.");
    }

}

