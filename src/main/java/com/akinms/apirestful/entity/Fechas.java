package com.akinms.apirestful.entity;

import java.util.Date;

public class Fechas {
    private Date fecha_inicio;
    private Date fecha_fin;
    public Fechas(){}

    public Fechas(Date fecha_inicio, Date fecha_fin) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
}
