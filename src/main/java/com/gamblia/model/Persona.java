package com.gamblia.model;

import java.util.List;

public class Persona {

    private Integer id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String nif;
    private String email;
    private String tlf;
    private List<Cuenta> cuentas;
    private List<Direccion> direccions;

    public Persona() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public List<Direccion> getDireccions() {
        return direccions;
    }

    public void setDireccions(List<Direccion> direccions) {
        this.direccions = direccions;
    }
}
