package com.akinms.apirestful.entity;

import com.akinms.apirestful.controller.ClienteRestController;
import com.akinms.apirestful.controller.ProductoRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<Cliente, EntityModel<Cliente>> {
    @Override
    public EntityModel<Cliente> toModel(Cliente entity) {
        return EntityModel.of(entity, //
                linkTo(methodOn(ClienteRestController.class).show(entity.getIdcliente())).withSelfRel(),
                linkTo(methodOn(ClienteRestController.class).listAll()).withRel("clientes"));
    }
}