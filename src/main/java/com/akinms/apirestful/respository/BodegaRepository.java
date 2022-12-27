package com.akinms.apirestful.respository;

import com.akinms.apirestful.entity.Bodega;
import com.akinms.apirestful.entity.Cliente;
import com.akinms.apirestful.entity.Producto;
import com.akinms.apirestful.responseentity.BodegaUbicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BodegaRepository extends JpaRepository<Bodega, Long> {

    @Query(value = "{call listarBodegas()};", nativeQuery = true)
    List<Bodega> getBodegas();

    @Query(value = "{call listarBodegasPremium()};", nativeQuery = true)
    List<Bodega> getBodegasPremium();
}
