package com.akinms.apirestful.controller;

import com.akinms.apirestful.business.IProductoBusiness;
import com.akinms.apirestful.entity.Producto;
import com.akinms.apirestful.entity.ProductoModelAssembler;
import com.akinms.apirestful.exception.BusinessException;
import com.akinms.apirestful.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoRestController {
    @Autowired
    private IProductoBusiness productoBusiness;
    private final ProductoModelAssembler assemblerProducto;

    ProductoRestController(IProductoBusiness productoBusiness,ProductoModelAssembler assemblerProducto){
        this.productoBusiness = productoBusiness;
        this.assemblerProducto = assemblerProducto;
    }

    @GetMapping("/")
    public ResponseEntity<CollectionModel<EntityModel<Producto>>> listAll() {
    //public ResponseEntity<List<Producto>> listAll() {
        try{
            List<EntityModel<Producto>> productos = productoBusiness.listAllProducts().stream()
                    .map(assemblerProducto::toModel)
                    .collect(Collectors.toList());
            CollectionModel<EntityModel<Producto>> collection = CollectionModel.of(productos,
                    linkTo(methodOn(ProductoRestController.class).listAll()).withSelfRel());
            return new ResponseEntity<>(collection, HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/search")
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
    }
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
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id){
        try{
            productoBusiness.removeProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
