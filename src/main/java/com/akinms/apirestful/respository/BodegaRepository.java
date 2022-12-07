package com.akinms.apirestful.respository;

import com.akinms.apirestful.entity.Bodega;
import com.akinms.apirestful.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodegaRepository extends JpaRepository<Bodega, Long> {
}
