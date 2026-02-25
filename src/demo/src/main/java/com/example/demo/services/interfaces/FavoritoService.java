package com.example.demo.services.interfaces;

import com.example.demo.domain.Favorito;

public interface FavoritoService {
    Favorito crearFavorito(Favorito favorito);

    void eliminarFavorito(Long favoritoId);
}
