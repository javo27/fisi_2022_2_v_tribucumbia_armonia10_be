package com.akinms.apirestful.entity;

import com.akinms.apirestful.controller.BodegaRestController;
import com.akinms.apirestful.controller.ClienteRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BodegaModelAssembler implements RepresentationModelAssembler<Bodega, EntityModel<Bodega>> {
    @Override
    public EntityModel<Bodega> toModel(Bodega entity) {
        return EntityModel.of(entity, //
                linkTo(methodOn(BodegaRestController.class).show(entity.getIdbodega())).withSelfRel(),
                linkTo(methodOn(BodegaRestController.class).listAll()).withRel("bodegas"));
    }
}