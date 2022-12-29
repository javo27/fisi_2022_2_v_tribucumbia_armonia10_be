package com.akinms.apirestful.controller;

import com.akinms.apirestful.business.ICategoriaBusiness;
import com.akinms.apirestful.entity.Categoria;
import com.akinms.apirestful.exception.BusinessException;
import com.akinms.apirestful.exception.NotFoundException;
import com.akinms.apirestful.responseentity.Categorias;
import com.akinms.apirestful.responseentity.RespuestaBodegas;
import com.akinms.apirestful.responseentity.RespuestaCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/atencioncliente/v1/categorias")
public class CategoriaRestController {
    @Autowired
    private ICategoriaBusiness categoriaBusiness;

    @GetMapping("/listar/bodega/{id}")
    public ResponseEntity<RespuestaCategoria> listCategoriesBodega(@PathVariable Long id){
        RespuestaCategoria respuesta = new RespuestaCategoria();
        try{
            List<Categoria> categoriasBodega = categoriaBusiness.listarCategoriasBodega(id);
            List<Categorias> respuestaCategorias = new ArrayList<>();
            for(Categoria c:categoriasBodega){
                Categorias ca = new Categorias();
                ca.setIdcategoria(c.getIdcategoria());
                ca.setNombre(c.getNombre());
                ca.setImg_url(c.getImg_url());
                respuestaCategorias.add(ca);
            }
            if(respuestaCategorias.size()>0)
                respuesta.setMensaje("Categorias encontradas: "+respuestaCategorias.size());
            else
                respuesta.setMensaje("No se encontraron categorias");
            respuesta.setCategorias(respuestaCategorias);
            return new ResponseEntity<>(respuesta,HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(respuesta,HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e){
            return new ResponseEntity<>(respuesta,HttpStatus.NOT_FOUND);
        }
    }
    /*
    @GetMapping("/")
    public ResponseEntity<CollectionModel<EntityModel<Categoria>>> listAll() {
    //public ResponseEntity<List<Categoria>> listAll(){
        try{
            List<EntityModel<Categoria>> categorias = categoriaBusiness.listAllCategories().stream()
                    .map(assemblerCategoria::toModel)
                    .collect(Collectors.toList());
            CollectionModel<EntityModel<Categoria>> collection = CollectionModel.of(categorias,
                    linkTo(methodOn(CategoriaRestController.class).listAll()).withSelfRel());
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
    }*/
}
