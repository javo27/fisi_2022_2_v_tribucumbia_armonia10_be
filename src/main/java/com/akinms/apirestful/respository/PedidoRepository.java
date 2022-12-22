package com.akinms.apirestful.respository;

import com.akinms.apirestful.entity.Pedido;
import com.akinms.apirestful.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query(value = "{call pedidosCliente(:id_cliente)};", nativeQuery = true)
    List<Pedido> getPedidosCliente(@Param("id_cliente") Long id_cliente);
}