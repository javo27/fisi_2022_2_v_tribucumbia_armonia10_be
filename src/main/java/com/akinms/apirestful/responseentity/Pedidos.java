package com.akinms.apirestful.responseentity;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class Pedidos {
    private Date fecha;
    private double monto_total;
    private String estado;
    private String tipo_entrega;
    private String metodo_pago;

    public BodegaUbicacion bodega;

    private Long idpedido;

    private Set<DetallePedidos> detallesPedido;

    public Pedidos() {
    }

    public Pedidos(Date fecha, double monto_total, String estado, String tipo_entrega, String metodo_pago, Long idpedido) {
        this.fecha = fecha;
        this.monto_total = monto_total;
        this.estado = estado;
        this.tipo_entrega = tipo_entrega;
        this.metodo_pago = metodo_pago;
        this.idpedido = idpedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.fecha = sdf.parse(fecha);
    }

    public double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(double monto_total) {
        this.monto_total = monto_total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo_entrega() {
        return tipo_entrega;
    }

    public void setTipo_entrega(String tipo_entrega) {
        this.tipo_entrega = tipo_entrega;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public BodegaUbicacion getBodega() {
        return bodega;
    }

    public void setBodega(BodegaUbicacion bodega) {
        this.bodega = bodega;
    }

    public Long getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Long idpedido) {
        this.idpedido = idpedido;
    }

    public Set<DetallePedidos> getDetallesPedido() {
        return detallesPedido;
    }
    public void setDetallesPedido(Set<DetallePedidos> detallesPedido) {
        this.detallesPedido = detallesPedido;
        for(DetallePedidos dt: detallesPedido){
            dt.setPedido(this);
        }
    }
}
