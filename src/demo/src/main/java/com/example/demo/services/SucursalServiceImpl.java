package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Horario;
import com.example.demo.domain.Sucursal;
import com.example.demo.domain.Ubicacion;
import com.example.demo.domain.dto.SucursalDto;
import com.example.demo.domain.dto.UbicacionDto;
import com.example.demo.domain.exceptions.NoEncontradoException;
import com.example.demo.domain.exceptions.ValidacionException;
import com.example.demo.domain.projections.SucursalSummary;
import com.example.demo.domain.dto.SucursalCriteriaDto;
import com.example.demo.repository.SucursalRepository;
import com.example.demo.services.interfaces.SucursalService;
import com.example.demo.services.utils.TimeUtils;

/**
 * SucursalServiceImpl es una clase que implementa la interfaz SucursalService y
 * proporciona la lógica de negocio para manejar las sucursales en la
 * aplicación. Utiliza el repositorio SucursalRepository para acceder a los
 * datos de las sucursales en la base de datos.
 */
@Service
public class SucursalServiceImpl implements SucursalService {

    private static final Logger logger = LoggerFactory.getLogger(SucursalServiceImpl.class);

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
    @Transactional(rollbackFor = Exception.class)
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
     * Agrega un horario a una sucursal existente. Recibe el ID de la sucursal a la
     * que se desea agregar el horario y un objeto Horario con los datos del horario
     * a agregar. Verifica que el horario no se traslape con horarios existentes en
     * la sucursal y, si no hay traslapes, agrega el horario a la sucursal y
     * devuelve la sucursal actualizada. Si se encuentran traslapes, lanza una
     * excepción de validación con los detalles de los traslapes encontrados.
     * @param intSucursalId El ID de la sucursal a la que se desea agregar el horario
     * @param horario El objeto Horario con los datos del horario a agregar
     * @return La sucursal actualizada con el nuevo horario agregado
     * @throws ValidacionException Si se encuentran traslapes de horarios
     * @throws NoEncontradoException Si la sucursal no se encuentra en la base de datos
     */
    @Override
    public Sucursal agregarHorario(Long intSucursalId, Horario horario)
            throws ValidacionException, NoEncontradoException {
        List<Horario> horariosExistentes = new ArrayList<>();
        List<String> traslapes = new ArrayList<>();

        Sucursal sucursal = sucursalRepository.findBySucursalId(intSucursalId);
        if (sucursal == null) {
            throw new NoEncontradoException();
        }
        horariosExistentes = sucursal.getHorarios();

        for (Horario existente : horariosExistentes) {

            boolean esMismoDia = existente.getDiaId() == horario.getDiaId();
            if (!esMismoDia) {
                continue;
            }

            boolean seTraslapaHoraApertura = TimeUtils.isBetween(horario.getHoraApertura(), existente.getHoraApertura(),
                    existente.getHoraCierre());
            boolean seTraslapaHoraCierre = TimeUtils.isBetween(horario.getHoraCierre(), existente.getHoraApertura(),
                    existente.getHoraCierre());

            if (seTraslapaHoraApertura || seTraslapaHoraCierre) {
                String horarioStr = String.format("%s - %s", horario.getHoraApertura(), horario.getHoraCierre());
                String existenteHorario = String.format("%s - %s", existente.getHoraApertura(),
                        existente.getHoraCierre());
                String mensaje = String.format(
                        "El horario que se desea agregar se traslapa con un horario existente. Horario a agregar: %s - Horario existente: %s",
                        horarioStr, existenteHorario);
                traslapes.add(mensaje);
            }
        }

        if (!traslapes.isEmpty()) {
            throw new ValidacionException("Se encontraron traslapes de horarios", traslapes);
        }

        sucursalRepository.crearHorarioSucursal(
                intSucursalId,
                horario.getDiaId(),
                horario.getHoraApertura(),
                horario.getHoraCierre());

        if (intSucursalId != null) {
            return sucursalRepository.findById(intSucursalId)
                    .orElse(null);
        }
        return null;
    }

    // @Override
    // public Sucursal crearSucursalConHorarios(Sucursal sucursal, ) {

    // }

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

    @Override
    @Transactional(readOnly = true)
    public List<SucursalSummary> getByCriteria(SucursalCriteriaDto sucursalCriteriaDto) {
        String nombre = sucursalCriteriaDto.getCriterioBusqueda();
        UbicacionDto ubi = sucursalCriteriaDto.getUbicacion();
        Double latitud = ubi == null ? null : ubi.getLatitud() == 0.00 ? null : ubi.getLatitud();
        Double longitud = ubi == null ? null : ubi.getLongitud() == 0.00 ? null : ubi.getLongitud();
        Double kms = ubi == null ? null
                : sucursalCriteriaDto.getDistanciaKms() == 0.00 ? null : sucursalCriteriaDto.getDistanciaKms();

        List<SucursalSummary> summaries = sucursalRepository.findByCriteria(nombre, latitud, longitud, kms);

        // // JPA projection para obtener solo el ID y el nombre de las sucursales que
        // coinciden con el criterio de búsqueda
        // List<SucursalSummary> summaries =
        // sucursalRepository.findSucursalesByCriteria(nombre);

        for (SucursalSummary s : summaries) {
            logger.info("ID: {} - Nombre: {}", s.getSucursalId(), s.getNombre());
        }

        return summaries;
    }

    /**
     * Obtiene la entidad por medio del identificador. Regresa NULL en caso de no
     * existir
     */
    @Override
    public Sucursal getById(Long sucursalId) throws NoEncontradoException {

        // List<Sucursal> sucursals = sucursalRepository.findByEmpresaId(100);

        Optional<Sucursal> sucursalOpt = sucursalRepository.findById(sucursalId);
        Sucursal sucursal = sucursalOpt.isPresent() ? sucursalOpt.get() : null;

        if (sucursal == null) {
            throw new NoEncontradoException();
        }

        return sucursal;
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
    @Transactional(rollbackFor = Exception.class)
    public void actualizarUbicacionDeSucursales(List<Sucursal> sucursals, Long ubicacionId) {
        try {
            for (Sucursal sucursal : sucursals) {
                Ubicacion ub = new Ubicacion();
                ub.setUbicacionId(ubicacionId);
                sucursal.setUbicacion(ub);
                sucursalRepository.save(sucursal);
            }
        } catch (Exception e) {
            logger.error("Error al actualizar la ubicación de las sucursales: ", e);
            throw e;
        }

    }

    /**
     * Elimina una sucursal de la base de datos por su ID. Recibe el ID de la
     * sucursal a eliminar y elimina la sucursal correspondiente de la base de
     * datos.
     * 
     * @param sucursalId El ID de la sucursal a eliminar
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void eliminarSucursal(Long sucursalId) {
        try {
            sucursalRepository.deleteById(sucursalId);
        } catch (Exception e) {
            logger.error("Error al eliminar la sucursal con ID {}: {}", sucursalId, e.getMessage(), e);
            throw e;
        }
    }

}
