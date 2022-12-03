package com.akinms.apirestful.business;

import com.akinms.apirestful.entity.Producto;
import com.akinms.apirestful.exception.BusinessException;
import com.akinms.apirestful.exception.NotFoundException;

import java.util.List;

public interface IProductoBusiness {
    List<Producto> listAllProducts() throws BusinessException;
    List<Producto> listAllProductsByName(String nombre) throws BusinessException;
    Producto showProduct(Long id) throws BusinessException, NotFoundException;
    Producto saveProduct(Producto producto) throws BusinessException;
    Producto updateProducto(Long id, Producto producto) throws BusinessException, NotFoundException;
    void removeProduct(Long id) throws BusinessException, NotFoundException;
}
