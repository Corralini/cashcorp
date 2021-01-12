package com.gamblia.model;

import java.util.Date;

public class Movimiento {

    private Integer id;
    private Integer idCuenta;
    private Double cantidad;
    private String descripcion;
    private Integer idOperacion;
    private Integer idCuentaDestino;
    private Date fechaCreacion;

    public Movimiento() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Integer getIdCuentaDestino() {
        return idCuentaDestino;
    }

    public void setIdCuentaDestino(Integer idCuentaDestino) {
        this.idCuentaDestino = idCuentaDestino;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
