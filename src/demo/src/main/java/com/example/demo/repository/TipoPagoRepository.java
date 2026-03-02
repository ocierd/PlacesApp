package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.TipoPago;

@Repository
public interface TipoPagoRepository extends JpaRepository<TipoPago, Short> {

}