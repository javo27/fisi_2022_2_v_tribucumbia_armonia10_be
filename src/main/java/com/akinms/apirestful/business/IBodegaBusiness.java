package com.akinms.apirestful.business;

import com.akinms.apirestful.entity.Bodega;
import com.akinms.apirestful.entity.Cliente;
import com.akinms.apirestful.exception.BusinessException;
import com.akinms.apirestful.exception.NotFoundException;
import com.akinms.apirestful.responseentity.BodegaUbicacion;

import java.util.List;

public interface IBodegaBusiness {
    /*List<Bodega> listAll() throws BusinessException;

    Bodega save(Bodega bodega) throws BusinessException;
    Bodega update(Long id, Bodega bodega) throws BusinessException, NotFoundException;
    void remove(Long id) throws BusinessException, NotFoundException;
    */
    Bodega show(Long id) throws BusinessException, NotFoundException;
    List<Bodega> listAllBodegas() throws BusinessException;
    List<Bodega> listAllBodegasPremium() throws BusinessException;

}
