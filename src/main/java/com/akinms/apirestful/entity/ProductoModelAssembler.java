package com.akinms.apirestful.entity;

import com.akinms.apirestful.controller.ProductoRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, EntityModel<Producto>> {
    @Override
    public EntityModel<Producto> toModel(Producto entity) {
        return EntityModel.of(entity, //
                linkTo(methodOn(ProductoRestController.class).showProduct(entity.getIdProducto())).withSelfRel(),
                linkTo(methodOn(ProductoRestController.class).listAll()).withRel("productos"));
    }
}
