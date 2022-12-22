package com.akinms.apirestful.respository;

import com.akinms.apirestful.entity.Bodega;
import com.akinms.apirestful.entity.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {
}
