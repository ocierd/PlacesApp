package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Sucursal;
import com.example.demo.domain.Ubicacion;
import com.example.demo.domain.dto.SucursalDto;
import com.example.demo.repository.SucursalRepository;
import com.example.demo.services.interfaces.SucursalService;

import jakarta.transaction.Transactional;

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

    /**
     * Crea una nueva sucursal en la base de datos a partir de un objeto
     * SucursalDto. Recibe un objeto SucursalDto con los datos de la sucursal a
     * crear y devuelve la sucursal creada. Este método es útil para recibir datos
     * de la sucursal desde el cliente en formato DTO (Data Transfer Object) y luego
     * convertirlo a una entidad Sucursal para guardarlo en la base de datos.
     * 
     * @param sucursal El objeto SucursalDto que se desea crear
     * @return La sucursal creada
     */
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

    /**
     * Obtiene la entidad por medio del identificador. Regresa NULL en caso de no
     * existir
     */
    @Override
    public Sucursal getById(Long sucursalId) {

        List<Sucursal> sucursals = sucursalRepository.findByEmpresaId(100);

        Optional<Sucursal> sucursalOpt = sucursalRepository.findById(sucursalId);
        return sucursalOpt.isPresent() ? sucursalOpt.get() : null;

        // Sucursal sucursal = sucursalRepository.findBySucursalId(sucursalId);
        // return sucursal;

    }

    /**
     * Ejemplo de @Transactional para actualizar la ubicación de una lista de
     * sucursales.
     * Este método se encarga de actualizar la ubicación de cada sucursal en la
     * lista proporcionada con la ubicación correspondiente al ID de ubicación
     * proporcionado. Si ocurre algún error durante el proceso, se imprime un
     * mensaje de error y se lanza la excepción para que se realice un rollback de
     * la transacción.
     * 
     * @param sucursals   Lista de sucursales a actualizar
     * @param ubicacionId ID de la ubicación que se asignará a las sucursales
     */
    @Transactional(rollbackOn = Exception.class)
    public void actualizarUbicacionDeSucursales(List<Sucursal> sucursals, Long ubicacionId) {
        try {
            for (Sucursal sucursal : sucursals) {
                Ubicacion ub = new Ubicacion();
                ub.setUbicacionId(ubicacionId);
                sucursal.setUbicacion(ub);
                sucursalRepository.save(sucursal);
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar la ubicación de las sucursales: "
                    + e.getMessage());
            throw e;
        }

    }

}
