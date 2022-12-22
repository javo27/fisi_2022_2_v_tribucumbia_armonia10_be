package com.akinms.apirestful.business;

import com.akinms.apirestful.entity.Categoria;
import com.akinms.apirestful.entity.Producto;
import com.akinms.apirestful.exception.BusinessException;
import com.akinms.apirestful.exception.NotFoundException;

import java.util.List;

public interface ICategoriaBusiness {
    /*List<Categoria> listAllCategories() throws BusinessException;
    Categoria showCategory(Long id) throws BusinessException, NotFoundException;
    Categoria saveCategory(Categoria categoria) throws BusinessException;
    Categoria updateCategory(Long id, Categoria categoria) throws BusinessException, NotFoundException;
    void removeCategory(Long id) throws BusinessException, NotFoundException; */
    List<Categoria> listarCategoriasBodega(Long id) throws BusinessException, NotFoundException;
}
