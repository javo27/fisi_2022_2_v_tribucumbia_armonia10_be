package com.akinms.apirestful.entity;

import com.akinms.apirestful.controller.ProductoRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CategoriaModelAssembler implements RepresentationModelAssembler<Categoria, EntityModel<Categoria>> {
    @Override
    public EntityModel<Categoria> toModel(Categoria entity) {
        return EntityModel.of(entity, //
                linkTo(methodOn(ProductoRestController.class).showProduct(entity.getIdcategoria())).withSelfRel(),
                linkTo(methodOn(ProductoRestController.class).listAll()).withRel("categorias"));
    }
}
