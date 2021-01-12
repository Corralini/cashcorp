package com.gamblia.model;

public class Plazo {
    private Integer id;
    private String plazo;
    private Double varInteres;

    public Plazo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public Double getVarInteres() {
        return varInteres;
    }

    public void setVarInteres(Double varInteres) {
        this.varInteres = varInteres;
    }
}
