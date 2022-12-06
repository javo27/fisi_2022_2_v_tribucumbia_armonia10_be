package com.akinms.apirestful.respository;

import com.akinms.apirestful.entity.Bodeguero;
import com.akinms.apirestful.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodegueroRepository extends JpaRepository<Bodeguero, Long> {
}