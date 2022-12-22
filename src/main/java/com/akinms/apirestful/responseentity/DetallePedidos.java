package com.akinms.apirestful.responseentity;

import com.akinms.apirestful.entity.Producto;

public class DetallePedidos {
    private int cantidad;
    private Pedidos pedido;
    private Producto producto;

    public DetallePedidos(){}

    public DetallePedidos(int cantidad, Pedidos pedido, Producto producto) {
        this.cantidad = cantidad;
        this.pedido = pedido;
        this.producto = producto;
    }

    public Pedidos getPedido() {
        return pedido;
    }
    public void setPedido(Pedidos pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
