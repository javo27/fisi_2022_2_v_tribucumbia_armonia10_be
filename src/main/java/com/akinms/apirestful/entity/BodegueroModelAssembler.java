package com.akinms.apirestful.entity;

import com.akinms.apirestful.controller.BodegaRestController;
import com.akinms.apirestful.controller.BodegueroRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BodegueroModelAssembler implements RepresentationModelAssembler<Bodeguero, EntityModel<Bodeguero>> {
    @Override
    public EntityModel<Bodeguero> toModel(Bodeguero entity) {
        return EntityModel.of(entity, //
                linkTo(methodOn(BodegueroRestController.class).show(entity.getIdbodeguero())).withSelfRel(),
                linkTo(methodOn(BodegueroRestController.class).listAll()).withRel("bodegueros"));
    }
}