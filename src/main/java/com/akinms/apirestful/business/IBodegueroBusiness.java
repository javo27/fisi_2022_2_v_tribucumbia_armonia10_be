package com.akinms.apirestful.business;

import com.akinms.apirestful.entity.Bodega;
import com.akinms.apirestful.entity.Bodeguero;
import com.akinms.apirestful.entity.Cliente;
import com.akinms.apirestful.exception.BusinessException;
import com.akinms.apirestful.exception.NotFoundException;

import java.util.List;

public interface IBodegueroBusiness {
    /*List<Bodeguero> listAll() throws BusinessException;

    Bodeguero save(Bodeguero bodeguero) throws BusinessException;
    Bodeguero update(Long id, Bodeguero bodeguero) throws BusinessException, NotFoundException;
    void remove(Long id) throws BusinessException, NotFoundException;*/
    Bodeguero show(Long id) throws BusinessException, NotFoundException;
    Bodeguero buscarBodeguero(String correo, String pass) throws BusinessException, NotFoundException;
}
