package com.akinms.apirestful.controller;

import com.akinms.apirestful.business.IPedidoBusiness;
import com.akinms.apirestful.entity.Pedido;
import com.akinms.apirestful.exception.BusinessException;
import com.akinms.apirestful.exception.NotFoundException;
import com.akinms.apirestful.responseentity.RespuestaPedido;
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
@RequestMapping("/api/atencioncliente/v1/pedidos")
public class PedidoRestController {

    @Autowired
    private IPedidoBusiness pedidoBusiness;

    @GetMapping("/consultar/cliente/{id}")
    public ResponseEntity<RespuestaPedido> listPedidosCliente(@PathVariable Long id){
        RespuestaPedido respuestaPedido = new RespuestaPedido();
        try{
            List<Pedido> pedidosCliente = pedidoBusiness.getPedidosCliente(id);
            if(pedidosCliente.size()>0)
                respuestaPedido.setMensaje("Cantidad de pedidos: "+pedidosCliente.size());
            else
                respuestaPedido.setMensaje("No Tiene pedidos registrados");
            respuestaPedido.setPedidos(pedidosCliente);
            return new ResponseEntity<>(respuestaPedido,HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
    @GetMapping("/")
    public ResponseEntity<CollectionModel<EntityModel<Pedido>>> listAll() {
    //public ResponseEntity<List<Categoria>> listAll(){
        try{
            List<EntityModel<Pedido>> pedidos = pedidoBusiness.listAll().stream()
                    .map(assemblerPedido::toModel)
                    .collect(Collectors.toList());
            CollectionModel<EntityModel<Pedido>> collection = CollectionModel.of(pedidos,
                    linkTo(methodOn(PedidoRestController.class).listAll()).withSelfRel());
            return new ResponseEntity<>(collection, HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Pedido>> show(@PathVariable Long id){
    //public ResponseEntity<Categoria> showCategory(@PathVariable Long id){
        try{
            Pedido pedido = pedidoBusiness.show(id);
            return new ResponseEntity<>(assemblerPedido.toModel(pedido),
                    HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Pedido pedido){
        try{
            EntityModel<Pedido> entityModel = assemblerPedido.toModel(pedidoBusiness.save(pedido));
            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
        }catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Pedido>> update(@PathVariable Long id, @RequestBody Pedido pedido){
        try {
            EntityModel<Pedido> entityModel = assemblerPedido.toModel(pedidoBusiness.update(id,pedido));
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
            pedidoBusiness.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BusinessException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/
}