package com.akinms.apirestful.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "producto")
public class Producto implements Serializable {
    private String nombre;
    private String descripcion;
    private Double precio;
    private Double descuento;
    private int stock;
    private String img;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    //@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idcategoria", nullable = false)
    public Categoria categoria = new Categoria();

    /*@OneToMany
    @JoinColumn(name = "idproducto")
    private Set<DetallePedido> detallePedido;*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idproducto;

    public Producto(String nombre, String descripcion, Double precio, Double descuento, int stock, String img, int idCategoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.descuento = descuento;
        this.stock = stock;
        this.img = img;
    }
    public Producto(){

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
    public Long getIdProducto() {
        return idproducto;
    }

    public void setIdproducto(Long id) {
        this.idproducto = id;
    }

    @JsonBackReference(value = "productoscate")
    //@JsonManagedReference(value = "productoscate")
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    //@JsonBackReference
   /* @JsonManagedReference
    public Set<DetallePedido> getDetallePedido(){
        return this.detallePedido;
    }
    public void setDetallePedido(Set<DetallePedido> detallePedido){
        this.detallePedido = detallePedido;
    }*/
    @Override
    public String toString(){
        return String.format("" +
                "producto [idproducto=%d, nombre=%s, descripcion=%s, precio=%f, descuento=%f, stock=%d]",
                this.idproducto,this.nombre,this.categoria,this.precio,this.descuento,this.stock
        );
    }

}
