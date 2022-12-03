package com.akinms.apirestful.controller;

import com.akinms.apirestful.business.ICategoriaBusiness;
import com.akinms.apirestful.business.IProductoBusiness;
import com.akinms.apirestful.entity.Categoria;
import com.akinms.apirestful.entity.CategoriaModelAssembler;
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
@RequestMapping("/api/v1/categorias")
public class CategoriaRestController {
    @Autowired
    private ICategoriaBusiness categoriaBusiness;
    private CategoriaModelAssembler assemblerCategoria;

    CategoriaRestController(ICategoriaBusiness categoriaBusiness, CategoriaModelAssembler assemblerCategoria){
        this.categoriaBusiness = categoriaBusiness;
        this.assemblerCategoria = assemblerCategoria;
    }

    @GetMapping("/")
    public ResponseEntity<CollectionModel<EntityModel<Categoria>>> listAll() {
    //public ResponseEntity<List<Categoria>> listAll(){
        try{
            List<EntityModel<Categoria>> categorias = categoriaBusiness.listAllCategories().stream()
                    .map(assemblerCategoria::toModel)
                    .collect(Collectors.toList());
            CollectionModel<EntityModel<Categoria>> collection = CollectionModel.of(categorias,
                    linkTo(methodOn(ProductoRestController.class).listAll()).withSelfRel());
            return new ResponseEntity<>(collection, HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Categoria>> showCategory(@PathVariable Long id){
    //public ResponseEntity<Categoria> showCategory(@PathVariable Long id){
        try{
            Categoria categoria = categoriaBusiness.showCategory(id);
            return new ResponseEntity<>(assemblerCategoria.toModel(categoria),
                    HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public ResponseEntity<?> saveCategory(@RequestBody Categoria categoria){
        try{
            EntityModel<Categoria> entityModel = assemblerCategoria.toModel(categoriaBusiness.saveCategory(categoria));
            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
        }catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Categoria>> updateCategory(@PathVariable Long id, @RequestBody Categoria categoria){
        try {
            EntityModel<Categoria> entityModel = assemblerCategoria.toModel(categoriaBusiness.updateCategory(id,categoria));
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
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        try{
            categoriaBusiness.removeCategory(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
