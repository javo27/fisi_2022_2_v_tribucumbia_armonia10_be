package com.akinms.apirestful.controller;

import com.akinms.apirestful.business.IBodegueroBusiness;
import com.akinms.apirestful.entity.Bodeguero;
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
@RequestMapping("/api/v1/bodegueros")
public class BodegueroRestController {
    @Autowired
    private IBodegueroBusiness bodegueroBusiness;

/*
    @GetMapping("/")
    public ResponseEntity<CollectionModel<EntityModel<Bodeguero>>> listAll() {
        //public ResponseEntity<List<Categoria>> listAll(){
        try{
            List<EntityModel<Bodeguero>> bodegueros = bodegueroBusiness.listAll().stream()
                    .map(assemblerBodeguero::toModel)
                    .collect(Collectors.toList());
            CollectionModel<EntityModel<Bodeguero>> collection = CollectionModel.of(bodegueros,
                    linkTo(methodOn(ClienteRestController.class).listAll()).withSelfRel());
            return new ResponseEntity<>(collection, HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Bodeguero>> show(@PathVariable Long id){
        //public ResponseEntity<Categoria> showCategory(@PathVariable Long id){
        try{
            Bodeguero bodeguero = bodegueroBusiness.show(id);
            return new ResponseEntity<>(assemblerBodeguero.toModel(bodeguero),
                    HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Bodeguero bodeguero){
        try{
            EntityModel<Bodeguero> entityModel = assemblerBodeguero.toModel(bodegueroBusiness.save(bodeguero));
            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
        }catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Bodeguero>> update(@PathVariable Long id, @RequestBody Bodeguero bodeguero){
        try {
            EntityModel<Bodeguero> entityModel = assemblerBodeguero.toModel(bodegueroBusiness.update(id,bodeguero));
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
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            bodegueroBusiness.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/
}
