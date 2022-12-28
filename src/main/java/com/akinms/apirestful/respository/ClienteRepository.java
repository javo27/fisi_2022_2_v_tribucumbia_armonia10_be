package com.akinms.apirestful.respository;

import com.akinms.apirestful.entity.Bodega;
import com.akinms.apirestful.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query(value = "{call buscarCliente(:correo_b,:passw)};", nativeQuery = true)
    Cliente buscarCliente(@Param("correo_b") String correo_b, @Param("passw") String passw);
}