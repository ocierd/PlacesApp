package com.places.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.places.api.domain.SucursalTipoPago;

@Repository
public interface SucursalTipoPagoRepository extends JpaRepository <SucursalTipoPago, Long> {
  
}