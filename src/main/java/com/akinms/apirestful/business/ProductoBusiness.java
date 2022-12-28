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
    /*@Override
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
*/
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

    @Override
    public List<Producto> listProductsBodega(Long id) throws BusinessException, NotFoundException {
        Optional<Bodega> op;
        try{
            op = bodegaRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro la bodega con el id "+id);
        }
        else{
            try{
                return productoRepository.getProductosBodega(id);
            } catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
    }

    @Override
    public List<Producto> listaProductsNombre(String nombre) throws BusinessException{
        try{
            return productoRepository.getProductosNombre(nombre);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public List<Producto> listProductsNombreBodega(String nombre, Long id) throws BusinessException, NotFoundException {
        Optional<Bodega> op;
        try{
            op = bodegaRepository.findById(id);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro la bodega con el id "+id);
        }
        else{
            try{
                return productoRepository.getProductosNombreBodega(nombre,id);
            } catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
    }

    @Override
    public List<Producto> listProductsCategory(Long id1, Long id2) throws BusinessException, NotFoundException {
        Optional<Bodega> op;
        Optional<Categoria> cat;
        try{
            op = bodegaRepository.findById(id2);
        } catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro la bodega con el id "+id2);
        }
        else{
            try{
                cat = categoriaRepository.findById(id1);
            } catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
            if(!cat.isPresent()){
                throw new NotFoundException("No se encontro la categoria con el id "+id2);
            } else{
                try{
                    return productoRepository.getProductosCateoria(id1,id2);
                } catch (Exception e){
                    throw new BusinessException(e.getMessage());
                }
            }

        }
    }

    @Override
    public Producto actualizarProducto(Long id, String nombre, int stock, double precio) throws BusinessException, NotFoundException {
        Optional<Producto> op;
        try{
            op = productoRepository.findById(id);
        } catch (Exception e){
            throw new NotFoundException("No se encontro el producto con el id "+id);
        }
        if(!op.isPresent()){
            throw new NotFoundException("No se encontro el producto con el id "+id);
        } else{
            try{
                return productoRepository.updateProducto(id,nombre,stock,precio);
            } catch(Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
    }
}
