package com.gamblia.model;

import java.util.Date;

public class Cuenta {

    private Integer id;
    private String idPais;
    private Integer dcIban;
    private Integer entidad;
    private Integer oficina;
    private Integer dc;
    private Integer cuenta;
    private Double saldo;
    private Date fechaCreacion;

    public Cuenta() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdPais() {
        return idPais;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    public Integer getDcIban() {
        return dcIban;
    }

    public void setDcIban(Integer dcIban) {
        this.dcIban = dcIban;
    }

    public Integer getEntidad() {
        return entidad;
    }

    public void setEntidad(Integer entidad) {
        this.entidad = entidad;
    }

    public Integer getOficina() {
        return oficina;
    }

    public void setOficina(Integer oficina) {
        this.oficina = oficina;
    }

    public Integer getDc() {
        return dc;
    }

    public void setDc(Integer dc) {
        this.dc = dc;
    }

    public Integer getCuenta() {
        return cuenta;
    }

    public void setCuenta(Integer cuenta) {
        this.cuenta = cuenta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
