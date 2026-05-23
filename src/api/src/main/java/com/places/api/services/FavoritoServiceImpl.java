package com.places.api.services;

import org.springframework.stereotype.Service;

import com.places.api.domain.Favorito;
import com.places.api.repository.FavoritoRepository;
import com.places.api.services.interfaces.FavoritoService;

@Service
public class FavoritoServiceImpl implements FavoritoService {
    private final FavoritoRepository favoritoRepository;

    public FavoritoServiceImpl(FavoritoRepository favoritoRepository) {
        this.favoritoRepository = favoritoRepository;
    }

    @Override
    public Favorito crearFavorito(Favorito favorito) {

        Favorito creada = favoritoRepository.save(favorito);
        Long favoritoId = creada.getFavoritoId();

        if (favoritoId != null) {
            return favoritoRepository.findById(favoritoId)
                    .orElse(null);
        }
        return creada;

    }

    @Override
    public void eliminarFavorito(Long favoritoId) {
        favoritoRepository.deleteById(favoritoId);

    }

}
