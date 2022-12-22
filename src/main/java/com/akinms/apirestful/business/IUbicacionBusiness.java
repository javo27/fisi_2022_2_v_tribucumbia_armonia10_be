package com.akinms.apirestful.business;

import com.akinms.apirestful.entity.Bodega;
import com.akinms.apirestful.entity.Ubicacion;
import com.akinms.apirestful.exception.BusinessException;
import com.akinms.apirestful.exception.NotFoundException;

import java.util.List;

public interface IUbicacionBusiness {
    List<Ubicacion> listAll() throws BusinessException;
    Ubicacion show(Long id) throws BusinessException, NotFoundException;
    Ubicacion save(Ubicacion ubicacion) throws BusinessException;
    Ubicacion update(Long id, Ubicacion ubicacion) throws BusinessException, NotFoundException;
    void remove(Long id) throws BusinessException, NotFoundException;
}
