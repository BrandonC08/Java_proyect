package com.demo.proyecto_intro_progra;

public class Backlog {

    //Atributos
    private int id;
    private String nombre;
    private int esfuerzo;
    private String estado;

    //Constructores
    public Backlog() {
        this.estado = "Pendiente";
    }

    public Backlog(int id, String nombre, int esfuerzo) {
        this.id = id;
        this.nombre = nombre;
        this.esfuerzo = esfuerzo;
        this.estado = "Pendiente";
    }

    //Metodos
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setEsfuerzo(int esfuerzo) {
        this.esfuerzo = esfuerzo;
    }

    public int getEsfuerzo() {
        return esfuerzo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
