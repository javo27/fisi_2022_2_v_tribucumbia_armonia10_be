package com.akinms.apirestful.controller;

import com.akinms.apirestful.business.IBodegaBusiness;
import com.akinms.apirestful.entity.*;
import com.akinms.apirestful.exception.BusinessException;
import com.akinms.apirestful.exception.NotFoundException;
import com.akinms.apirestful.responseentity.BodegaUbicacion;
import com.akinms.apirestful.responseentity.RespuestaBodegas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/atencioncliente/v1/bodegas")
public class BodegaRestController {
    @Autowired
    private IBodegaBusiness bodegaBusiness;


    /*@GetMapping("/")
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
    }*/
    @GetMapping("/")
    public ResponseEntity<RespuestaBodegas> listAll() {
        RespuestaBodegas rp = new RespuestaBodegas();
        try{
            List<Bodega> bodegas = bodegaBusiness.listAllBodegas();
            List<BodegaUbicacion> bodegasRespuesta = new ArrayList<>();
            for(Bodega bo: bodegas){
                BodegaUbicacion bu = new BodegaUbicacion();
                bu.setIdbodega(bo.getIdbodega());
                bu.setNombre(bo.getNombre());
                bu.setDireccion(bo.getUbicacion().getNombre());
                bu.setLatitud(bo.getUbicacion().getLatitud());
                bu.setLongitud(bo.getUbicacion().getLongitud());
                bodegasRespuesta.add(bu);
            }
            if(bodegasRespuesta.size()>0)
                rp.setMensaje("Total de bodegas: "+bodegasRespuesta.size());
            else
                rp.setMensaje("Sin bodegas disponibles");
            rp.setBodegas(bodegasRespuesta);
            return new ResponseEntity<>(rp, HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaBodegas> show(@PathVariable Long id){
        //public ResponseEntity<Categoria> showCategory(@PathVariable Long id){
        RespuestaBodegas rp = new RespuestaBodegas();
        try{
            Bodega bodegas = bodegaBusiness.show(id);
            BodegaUbicacion bodegaRespuesta = new BodegaUbicacion(
                    bodegas.getIdbodega(),
                    bodegas.getNombre(),
                    bodegas.getUbicacion().getNombre(),
                    bodegas.getUbicacion().getLatitud(),
                    bodegas.getUbicacion().getLongitud());
            rp.setMensaje("Bodega "+bodegas.getNombre());
            rp.setBodegas(bodegaRespuesta);
            return new ResponseEntity<>(rp, HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }/*
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
    }*/
}
