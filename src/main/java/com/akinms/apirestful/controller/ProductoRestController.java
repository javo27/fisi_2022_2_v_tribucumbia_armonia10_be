package com.akinms.apirestful.controller;

import com.akinms.apirestful.business.IProductoBusiness;
import com.akinms.apirestful.entity.Producto;
import com.akinms.apirestful.responseentity.RespuestaMensaje;
import com.akinms.apirestful.responseentity.RespuestaProductos;
import com.akinms.apirestful.exception.BusinessException;
import com.akinms.apirestful.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/atencioncliente/v1/productos")
public class ProductoRestController {
    @Autowired
    private IProductoBusiness productoBusiness;


    /*
    @GetMapping("/")
    public ResponseEntity<RespuestaProductos> listAll() {
    //public ResponseEntity<List<Producto>> listAll() {
        RespuestaProductos respuesta = new RespuestaProductos();
        try{
            List<?> productos = productoBusiness.listAllProducts();
            if (productos.size()>0)
                respuesta.setMensaje("Se encontraron "+productos.size()+" productos");
            else
                respuesta.setMensaje("No se encontraron coincidencias");
            respuesta.setProductos(productos);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/search2")
    public ResponseEntity<CollectionModel<EntityModel<Producto>>> listAllByName(@RequestParam String nombre) {
        try{
            List<EntityModel<Producto>> productos = productoBusiness.listAllProductsByName(nombre).stream()
                    .map(assemblerProducto::toModel)
                    .collect(Collectors.toList());
            CollectionModel<EntityModel<Producto>> collection = CollectionModel.of(productos,
                    linkTo(methodOn(ProductoRestController.class).listAllByName(nombre)).withSelfRel());
            return new ResponseEntity<>(collection, HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    @GetMapping("/detalles/{id}")
    public ResponseEntity<RespuestaProductos> showProduct(@PathVariable Long id){
        //public ResponseEntity<Producto> showProduct(@PathVariable Long id){
        RespuestaProductos respuesta = new RespuestaProductos();
        respuesta.setMensaje("Producto con id: "+id+" no se encuentra registrado");
        try{
            Producto producto = productoBusiness.showProduct(id);
            respuesta.setMensaje("Producto con id: "+id);
            respuesta.setProductos(producto);
            return new ResponseEntity<>(respuesta,
                    HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(respuesta,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/listarproductos/bodega/{id}")
    public ResponseEntity<RespuestaProductos> listarProductosBodega(@PathVariable Long id) {
        try{
            List<?> productos = productoBusiness.listProductsBodega(id);
            RespuestaProductos respuesta = new RespuestaProductos("Productos por Bodega",productos);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/search")
    public ResponseEntity<RespuestaProductos> listarProductosNombre(@RequestParam String nombre) {
        RespuestaProductos respuesta = new RespuestaProductos();
        try{
            List<?> productos = productoBusiness.listaProductsNombre(nombre);
            if (productos.size()>0)
                respuesta.setMensaje("Se encontraron "+productos.size()+" productos");
            else
                respuesta.setMensaje("No se encontraron coincidencias");
            respuesta.setProductos(productos);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/bodega/{id}/search")
    public ResponseEntity<RespuestaProductos> listarProductosNombreBodega(@PathVariable Long id, @RequestParam String nombre) {
        RespuestaProductos respuesta = new RespuestaProductos();
        try{
            List<?> productos = productoBusiness.listProductsNombreBodega(nombre,id);
            if (productos.size()>0)
                respuesta.setMensaje("Se encontraron "+productos.size()+" productos");
            else
                respuesta.setMensaje("No se encontraron coincidencias");
            respuesta.setProductos(productos);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/bodega/{id2}/categoria/{id1}")
    public ResponseEntity<RespuestaProductos> listarProductosCategoria(@PathVariable Long id1, @PathVariable Long id2) {
        RespuestaProductos respuesta = new RespuestaProductos();
        try{
            List<Producto> productos = productoBusiness.listProductsCategory(id1,id2);
            if (productos.size()>0)
                respuesta.setMensaje("Se encontraron "+productos.size()+" productos de la categoria " + productos.get(0).categoria.getNombre());
            else
                respuesta.setMensaje("La categoria no presenta productos registrados");
            respuesta.setProductos(productos);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("actualizar/{id}")
    public ResponseEntity<RespuestaProductos> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto){
        RespuestaProductos respuesta = new RespuestaProductos();
        try{
            Producto productoupdate = productoBusiness.actualizarProducto(id,producto.getNombre(),producto.getStock(),producto.getPrecio());

            respuesta.setMensaje("Producto Actualizado de manera exitosa");
            respuesta.setProductos(productoBusiness.showProduct(id));
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<RespuestaMensaje> deleteProduct(@PathVariable Long id){
        try{
            productoBusiness.removeProduct(id);
            return new ResponseEntity<>(new RespuestaMensaje("Producto Eliminado de manera Exitosa"),HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
/*
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Producto>> showProduct(@PathVariable Long id){
    //public ResponseEntity<Producto> showProduct(@PathVariable Long id){
        try{
            Producto producto = productoBusiness.showProduct(id);
            return new ResponseEntity<>(assemblerProducto.toModel(producto),
                    HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public ResponseEntity<?> saveProduct(@RequestBody Producto producto){
        try{
            EntityModel<Producto> entityModel = assemblerProducto.toModel(productoBusiness.saveProduct(producto));
            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
        }catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Producto>> updateProduct(@PathVariable Long id, @RequestBody Producto producto){
        try {
            EntityModel<Producto> entityModel = assemblerProducto.toModel(productoBusiness.updateProducto(id,producto));
            //return new ResponseEntity<>(entityModel,HttpStatus.OK);
            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    */
}
