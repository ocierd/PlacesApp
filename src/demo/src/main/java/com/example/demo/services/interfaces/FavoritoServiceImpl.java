package com.example.demo.services.interfaces;

import org.springframework.stereotype.Service;
import com.example.demo.domain.Favorito;
import com.example.demo.repository.FavoritoRepository;

@Service
public class FavoritoServiceImpl implements FavoritoService {
    private final FavoritoRepository favoritoRepository;

    public FavoritoServiceImpl(FavoritoRepository favoritoRepository)
    {
        this.favoritoRepository=favoritoRepository;
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
public void eliminarFavorito (Long favoritoId){
    favoritoRepository.deleteById(favoritoId);

}
       

}
