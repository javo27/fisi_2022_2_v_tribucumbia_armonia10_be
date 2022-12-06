package com.akinms.apirestful.controller;

import com.akinms.apirestful.business.IBodegaBusiness;
import com.akinms.apirestful.business.IClienteBusiness;
import com.akinms.apirestful.entity.Bodega;
import com.akinms.apirestful.entity.BodegaModelAssembler;
import com.akinms.apirestful.entity.Cliente;
import com.akinms.apirestful.entity.ClienteModelAssembler;
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
@RequestMapping("/api/v1/bodegas")
public class BodegaRestController {
    @Autowired
    private IBodegaBusiness bodegaBusiness;
    private BodegaModelAssembler assemblerBodega;

    BodegaRestController(IBodegaBusiness bodegaBusiness, BodegaModelAssembler assemblerBodega){
        this.bodegaBusiness = bodegaBusiness;
        this.assemblerBodega = assemblerBodega;
    }

    @GetMapping("/")
    public ResponseEntity<CollectionModel<EntityModel<Bodega>>> listAll() {
        //public ResponseEntity<List<Categoria>> listAll(){
        try{
            List<EntityModel<Bodega>> bodegas = bodegaBusiness.listAll().stream()
                    .map(assemblerBodega::toModel)
                    .collect(Collectors.toList());
            CollectionModel<EntityModel<Bodega>> collection = CollectionModel.of(bodegas,
                    linkTo(methodOn(ClienteRestController.class).listAll()).withSelfRel());
            return new ResponseEntity<>(collection, HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Bodega>> show(@PathVariable Long id){
        //public ResponseEntity<Categoria> showCategory(@PathVariable Long id){
        try{
            Bodega bodega = bodegaBusiness.show(id);
            return new ResponseEntity<>(assemblerBodega.toModel(bodega),
                    HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Bodega bodega){
        try{
            EntityModel<Bodega> entityModel = assemblerBodega.toModel(bodegaBusiness.save(bodega));
            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
        }catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Bodega>> update(@PathVariable Long id, @RequestBody Bodega bodega){
        try {
            EntityModel<Bodega> entityModel = assemblerBodega.toModel(bodegaBusiness.update(id,bodega));
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
            bodegaBusiness.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

