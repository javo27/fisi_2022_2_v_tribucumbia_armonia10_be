package com.akinms.apirestful.entity;

import com.akinms.apirestful.controller.PedidoRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class PedidoModelAssembler implements RepresentationModelAssembler<Pedido, EntityModel<Pedido>> {
    @Override
    public EntityModel<Pedido> toModel(Pedido entity) {
        return EntityModel.of(entity, //
                linkTo(methodOn(PedidoRestController.class).show(entity.getIdpedido())).withSelfRel(),
                linkTo(methodOn(PedidoRestController.class).listAll()).withRel("pedidos"));
    }
}
