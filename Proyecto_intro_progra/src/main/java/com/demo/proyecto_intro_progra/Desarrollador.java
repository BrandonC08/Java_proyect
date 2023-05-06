package com.demo.proyecto_intro_progra;

public class Desarrollador {

    //Atributos
    private String nombre;
    private String siglas;
    private double costo;
    private double id;

    //Constuctores
    public Desarrollador() {
    }

    public Desarrollador(String nombre, String siglas, double costo, double id) {
        this.nombre = nombre;
        this.siglas = siglas;
        this.costo = costo;
        this.id = id;
    }

    //Metodos
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }

    public String getSiglas() {
        return siglas;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getCosto() {
        return costo;
    }

    public void setId(double id) {
        this.id = id;
    }

    public double getId() {
        return id;
    }
}
