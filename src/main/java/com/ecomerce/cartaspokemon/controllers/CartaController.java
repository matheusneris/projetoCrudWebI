package com.ecomerce.cartaspokemon.controllers;

import com.ecomerce.cartaspokemon.models.CartaModel;
import com.ecomerce.cartaspokemon.services.CartaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cartas")
public class CartaController {

    @Autowired
    CartaService cartaService;

    @GetMapping("/cartas")
    public String getAllUsers(Model model){
        List<CartaModel> cartas = cartaService.buscarTodasCartas();
        model.addAttribute("cartas", cartas);
        return "cartas";
    }

    @PostMapping("/cartas")
    public String addCarta(@RequestParam("nomePokemon") String nomePokemon,
                           @RequestParam("raridadeCarta") String raridadeCarta,
                           @RequestParam("precoVenda") BigDecimal precoVenda){

        CartaModel cartaModel = new CartaModel();
        cartaModel.setNomePokemon(nomePokemon);
        cartaModel.setRaridadeCarta(raridadeCarta);
        cartaModel.setPrecoVenda(precoVenda);
        cartaService.salvarCarta(cartaModel);

        return "redirect:cartas";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id")UUID uuid){
        if(cartaService.buscarPorId(uuid).isEmpty()){
            return "redirect:cartas";
        }
        cartaService.deletarPorId(uuid);
        return "redirect:cartas";
    }

    @GetMapping("/buscar")
    public String searchCarta(@RequestParam("nomePokemon") String nomePokemon){
        cartaService.buscarPokemonPorNome(nomePokemon);

        return "redirect:cartas";
    }

    @GetMapping("/add-carta")
    public String createCarta(){
        return "add-cartas";
    }
}
