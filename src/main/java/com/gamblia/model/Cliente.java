package com.gamblia.model;

import java.util.Date;

public class Cliente extends Usuario {

    private Date fechaCreacion;

    public Cliente() {
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
