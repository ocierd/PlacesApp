package com.example.demo.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Correo;
import com.example.demo.domain.exceptions.NoEncontradoException;
import com.example.demo.domain.exceptions.ValidacionException;
import com.example.demo.repository.CorreoRepository;
import com.example.demo.services.interfaces.CorreoService;

@Service
public class CorreoServiceImpl implements CorreoService {
  private static final Logger logger = LoggerFactory.getLogger(CorreoServiceImpl.class);

  private final CorreoRepository correoRepository;

  public CorreoServiceImpl(CorreoRepository correoRepository) {
    this.correoRepository = correoRepository;
  }

  public Correo crearCorreo(Long usuarioId, Correo correo) throws NoEncontradoException, ValidacionException {
    try {
      String correoElectronico = correo.getCorreoElectronico();

      if (!correoElectronico.isEmpty()) {
        correoElectronico = correoElectronico.toLowerCase();

        validarDatosCorreo(correo);

        correo.setUsuarioId(usuarioId);
        correoRepository.save(correo);

        return correo;
      } else {
        throw new NoEncontradoException();
      }
    } catch (Exception e) {
      logger.error("Error al guardar confirmación el correo de verificación del token.", e);
      throw e;
    }
  }

  private void validarDatosCorreo(Correo correo) throws ValidacionException {
    Optional<Correo> correOptional = correoRepository
        .findByCorreoElectronicoAndActivoTrue(correo.getCorreoElectronico());
    if (correOptional.isPresent()) {
      throw new ValidacionException("Este correo ya está siendo usado");
    }
  }
}