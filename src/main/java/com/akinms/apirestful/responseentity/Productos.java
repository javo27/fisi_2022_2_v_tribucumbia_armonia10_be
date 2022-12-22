package com.akinms.apirestful.responseentity;

import com.akinms.apirestful.entity.Bodega;
import com.akinms.apirestful.entity.Categoria;

import javax.persistence.*;

public class Productos {
    private String nombre;
    private String descripcion;
    private Double precio;
    private Double descuento;
    private int stock;
    private String img;

    private Long idproducto;

    public Productos(){}

    public Productos(String nombre, String descripcion, Double precio, Double descuento, int stock, String img, Long idproducto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.descuento = descuento;
        this.stock = stock;
        this.img = img;
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Long idproducto) {
        this.idproducto = idproducto;
    }
}
