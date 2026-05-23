package com.places.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.places.api.domain.TipoPago;

@Repository
public interface TipoPagoRepository extends JpaRepository<TipoPago, Short> {

}