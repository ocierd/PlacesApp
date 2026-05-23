package com.places.api.services.interfaces;

import com.places.api.domain.Favorito;

public interface FavoritoService {
    Favorito crearFavorito(Favorito favorito);

    void eliminarFavorito(Long favoritoId);
}
