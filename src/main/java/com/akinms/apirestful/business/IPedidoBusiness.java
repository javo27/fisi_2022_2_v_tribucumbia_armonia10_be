package com.akinms.apirestful.business;

import com.akinms.apirestful.entity.Pedido;
import com.akinms.apirestful.exception.BusinessException;
import com.akinms.apirestful.exception.NotFoundException;
import com.akinms.apirestful.responseentity.Ventas;

import java.util.Date;
import java.util.List;

public interface IPedidoBusiness {
    /*List<Pedido> listAll() throws BusinessException;
    Pedido show(Long id) throws BusinessException, NotFoundException;*/
    Pedido save(Pedido pedido) throws BusinessException, NotFoundException;
    /*Pedido update(Long id, Pedido pedido) throws BusinessException, NotFoundException;
    void remove(Long id) throws BusinessException, NotFoundException;*/
    List<Pedido> getPedidosCliente(Long id) throws BusinessException, NotFoundException;
    Pedido getDetallePedidoCliente(Long id_cliente, Long id_pedido) throws BusinessException, NotFoundException;
    List<Pedido> getPedidosBodega(Long id) throws BusinessException, NotFoundException;
    List<Pedido> getVentasSemanes(String fecha_inicio, String fecha_fin, Long idBodega) throws BusinessException, NotFoundException;
}
