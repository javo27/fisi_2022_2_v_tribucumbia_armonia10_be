package com.akinms.apirestful.business;

import com.akinms.apirestful.entity.Cliente;
import com.akinms.apirestful.exception.BusinessException;
import com.akinms.apirestful.exception.NotFoundException;

import java.util.List;

public interface IClienteBusiness {
    List<Cliente> listAll() throws BusinessException;
    Cliente show(Long id) throws BusinessException, NotFoundException;
    Cliente save(Cliente cliente) throws BusinessException;
    Cliente update(Long id, Cliente cliente) throws BusinessException, NotFoundException;
    void remove(Long id) throws BusinessException, NotFoundException;
}
