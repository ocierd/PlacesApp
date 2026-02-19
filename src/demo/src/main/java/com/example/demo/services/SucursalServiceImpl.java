package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Sucursal;
import com.example.demo.domain.dto.SucursalDto;
import com.example.demo.repository.SucursalRepository;
import com.example.demo.services.interfaces.SucursalService;

/**
 * SucursalServiceImpl es una clase que implementa la interfaz SucursalService y
 * proporciona la lógica de negocio para manejar las sucursales en la
 * aplicación. Utiliza el repositorio SucursalRepository para acceder a los
 * datos de las sucursales en la base de datos.
 */
@Service
public class SucursalServiceImpl implements SucursalService {

    private final SucursalRepository sucursalRepository;

    public SucursalServiceImpl(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }

    /**
     * Crea una nueva sucursal en la base de datos. Recibe un objeto Sucursal con
     * los datos de la sucursal a crear y devuelve la sucursal creada.
     * 
     * @param sucursal El objeto Sucursal que se desea crear
     * @return La sucursal creada
     */
    @Override
    public Sucursal crearSucursal(Sucursal sucursal) {
        Sucursal creada = sucursalRepository.save(sucursal);
        Long sucursalId = creada.getSucursalId();
        if (sucursalId != null) {
            return sucursalRepository.findById(sucursalId)
                    .orElse(null);
        }
        return creada;
    }

    @Override
    public Sucursal crearSucursal(SucursalDto sucursal) {
        Long sucursalId = sucursalRepository.crearSucursal(
                sucursal.getEmpresaId(),
                sucursal.getNombre(),
                sucursal.getUbicacionId(),
                sucursal.getLatitud(),
                sucursal.getLongitud(),
                sucursal.getEnlaceMaps());
        if (sucursalId != null) {
            return sucursalRepository.findById(sucursalId)
                    .orElse(null);
        }
        return null;
    }

    /**
     * Obtiene una lista de todas las sucursales disponibles.
     * 
     * @return Lista de todas las sucursales
     */
    @Override
    public List<Sucursal> getAllSucursales() {
        List<Sucursal> sucursales = sucursalRepository.findAll();
        return sucursales;
    }

}
