package com.akinms.apirestful.business;

import com.akinms.apirestful.entity.Bodega;
import com.akinms.apirestful.entity.Categoria;
import com.akinms.apirestful.entity.Producto;
import com.akinms.apirestful.exception.BusinessException;
import com.akinms.apirestful.exception.NotFoundException;
import com.akinms.apirestful.respository.BodegaRepository;
import com.akinms.apirestful.respository.CategoriaRepository;
import com.akinms.apirestful.respository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoBusiness implements IProductoBusiness{
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private BodegaRepository bodegaRepository;
    @Override
    public List<Producto> listAllProducts() throws BusinessException {
        try{
            return productoRepository.findAll();
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    @Override
    public List<Producto> listAllProductsByName(String nombre) throws BusinessException{
        try{
            return productoRepository.findByNombreContainingIgnoreCase(nombre);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public Producto showProduct(Long id) throws BusinessException, NotFoundException {
        Optional<Producto> op;
        try{
            op = productoRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro el producto con el id "+id);
        }
        return op.get();
    }

    @Override
    public Producto saveProduct(Producto producto) throws BusinessException{
        try{
            Optional<Categoria> cat;
            Optional<Bodega> bod;
            if(producto.categoria!=null){
                cat = categoriaRepository.findById(producto.getCategoria().getIdcategoria());
                if(!cat.isPresent()){
                    throw new NotFoundException("La categoria a la que se quiere registrar un producto no existe");
                }
                producto.setCategoria(cat.get());
                //return productoRepository.save(producto);
            }
            if(producto.getBodega()!=null){
                bod = bodegaRepository.findById(producto.getBodega().getIdbodega());
                if(!bod.isPresent()){
                    throw new NotFoundException("La bodega a la que se quiere registrar un producto no existe");
                }
                producto.setBodega(bod.get());
            }
            return productoRepository.save(producto);

        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public Producto updateProducto(Long id, Producto producto) throws BusinessException, NotFoundException{
        Optional<Producto> op;
        try{
            op = productoRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro el producto con el id "+id);
        }
        else{
            try {
                Optional<Categoria> cat;
                if(producto.categoria!=null) {
                    cat = categoriaRepository.findById(producto.getCategoria().getIdcategoria());
                    producto.setCategoria(cat.get());
                }
                    //return productoRepository.save(producto);
                op.map(productoUpdate -> {
                    productoUpdate.setDescripcion(producto.getDescripcion());
                    productoUpdate.setImg(producto.getImg());
                    productoUpdate.setNombre(producto.getNombre());
                    productoUpdate.setPrecio(producto.getPrecio());
                    productoUpdate.setDescuento(producto.getDescuento());
                    productoUpdate.setStock(producto.getStock());
                    if(producto.categoria!=null){
                        productoUpdate.setCategoria(producto.getCategoria());
                    }
                    return productoRepository.save(productoUpdate);
                });
            }
            catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
        producto.setIdproducto(id);
        return productoRepository.save(producto);
    }

    @Override
    public void removeProduct(Long id) throws BusinessException, NotFoundException{
        Optional<Producto> op;
        try{
            op = productoRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro el producto con el id "+id);
        }
        else{
            try {
                productoRepository.deleteById(id);
            }
            catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
    }
}
