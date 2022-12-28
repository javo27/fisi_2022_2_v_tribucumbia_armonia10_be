package com.akinms.apirestful.respository;

import com.akinms.apirestful.entity.Bodeguero;
import com.akinms.apirestful.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BodegueroRepository extends JpaRepository<Bodeguero, Long> {
    @Query(value = "{call buscarBodeguero(:correo_b,:passw)};", nativeQuery = true)
    Bodeguero buscarBodeguero(@Param("correo_b") String correo_b, @Param("passw") String passw);
}
