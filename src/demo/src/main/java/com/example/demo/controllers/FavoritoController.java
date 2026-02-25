package com.example.demo.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Favorito;
import com.example.demo.services.interfaces.FavoritoService;

@RestController
@RequestMapping("/Favoritos")
public class FavoritoController {
    private final FavoritoService favoritoService;

    public FavoritoController(FavoritoService favoritoService){
        this.favoritoService=favoritoService;
    }

    @PostMapping
    public Favorito crearFavorito(@RequestBody Favorito favorito){
        return favoritoService.crearFavorito(favorito);
    }

    @DeleteMapping("/{id}")
    public void eliminarFavorito(@PathVariable Long id){
         favoritoService.eliminarFavorito(id);
    }
}
