package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.SucursalTipoPago;

@Repository
public interface SucursalTipoPagoRepository extends JpaRepository <SucursalTipoPago, Long> {
  
}