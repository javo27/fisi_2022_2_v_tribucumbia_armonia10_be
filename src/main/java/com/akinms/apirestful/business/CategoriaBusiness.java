package com.akinms.apirestful.business;

import com.akinms.apirestful.entity.Categoria;
import com.akinms.apirestful.entity.Producto;
import com.akinms.apirestful.exception.BusinessException;
import com.akinms.apirestful.exception.NotFoundException;
import com.akinms.apirestful.respository.CategoriaRepository;
import com.akinms.apirestful.respository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaBusiness implements ICategoriaBusiness{
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Override
    public List<Categoria> listAllCategories() throws BusinessException {
        try{
            return categoriaRepository.findAll();
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public Categoria showCategory(Long id) throws BusinessException, NotFoundException {
        Optional<Categoria> op;
        try{
            op = categoriaRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro la categoria con el id "+id);
        }
        return op.get();
    }

    @Override
    public Categoria saveCategory(Categoria categoria) throws BusinessException {
        try{
            return categoriaRepository.save(categoria);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public Categoria updateCategory(Long id, Categoria categoria) throws BusinessException, NotFoundException {
        Optional<Categoria> op;
        try{
            op = categoriaRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro el producto con el id "+id);
        }
        else{
            try {
                op.map(categoriaUpdate -> {
                    categoriaUpdate.setNombre(categoria.getNombre());
                    categoriaUpdate.setImg_url(categoria.getImg_url());
                    categoriaUpdate.setIdcategoria(id);
                    return categoriaRepository.save(categoriaUpdate);
                });
            }
            catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
        categoria.setIdcategoria(id);
        return categoriaRepository.save(categoria);
    }

    @Override
    public void removeCategory(Long id) throws BusinessException, NotFoundException {
        Optional<Categoria> op;
        try{
            op = categoriaRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro el producto con el id "+id);
        }
        else{
            try {
                categoriaRepository.deleteById(id);
            }
            catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
    }
}
