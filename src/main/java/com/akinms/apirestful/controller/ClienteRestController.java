package com.akinms.apirestful.controller;

import com.akinms.apirestful.business.IClienteBusiness;
import com.akinms.apirestful.entity.Cliente;
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
@RequestMapping("/api/v1/clientes")
public class ClienteRestController {
    @Autowired
    private IClienteBusiness clienteBusiness;

    /*@GetMapping("/")
    public ResponseEntity<CollectionModel<EntityModel<Cliente>>> listAll() {
    //public ResponseEntity<List<Categoria>> listAll(){
        try{
            List<EntityModel<Cliente>> clientes = clienteBusiness.listAll().stream()
                    .map(assemblerCliente::toModel)
                    .collect(Collectors.toList());
            CollectionModel<EntityModel<Cliente>> collection = CollectionModel.of(clientes,
                    linkTo(methodOn(ClienteRestController.class).listAll()).withSelfRel());
            return new ResponseEntity<>(collection, HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Cliente>> show(@PathVariable Long id){
    //public ResponseEntity<Categoria> showCategory(@PathVariable Long id){
        try{
            Cliente cliente = clienteBusiness.show(id);
            return new ResponseEntity<>(assemblerCliente.toModel(cliente),
                    HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Cliente cliente){
        try{
            EntityModel<Cliente> entityModel = assemblerCliente.toModel(clienteBusiness.save(cliente));
            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
        }catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Cliente>> update(@PathVariable Long id, @RequestBody Cliente cliente){
        try {
            EntityModel<Cliente> entityModel = assemblerCliente.toModel(clienteBusiness.update(id,cliente));
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
            clienteBusiness.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/
}
