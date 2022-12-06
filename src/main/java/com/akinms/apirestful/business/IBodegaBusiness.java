package com.akinms.apirestful.business;

import com.akinms.apirestful.entity.Bodega;
import com.akinms.apirestful.entity.Cliente;
import com.akinms.apirestful.exception.BusinessException;
import com.akinms.apirestful.exception.NotFoundException;

import java.util.List;

public interface IBodegaBusiness {
    List<Bodega> listAll() throws BusinessException;
    Bodega show(Long id) throws BusinessException, NotFoundException;
    Bodega save(Bodega bodega) throws BusinessException;
    Bodega update(Long id, Bodega bodega) throws BusinessException, NotFoundException;
    void remove(Long id) throws BusinessException, NotFoundException;
}
