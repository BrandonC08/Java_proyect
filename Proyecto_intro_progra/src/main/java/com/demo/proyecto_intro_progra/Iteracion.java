package com.demo.proyecto_intro_progra;

import java.util.HashMap;
import java.util.LinkedList;

public class Iteracion {
    //TODO: Atributos necesario para el reporte de resumen de iteraciones

    //Atributos
    private int id_iteracion;
    private int cantidadSemanas;
    private String estado;
    //Lista de HashMap con los dias de la iteracion
    public LinkedList<HashMap> dias = new LinkedList<>();

    //Metodos 
    public Iteracion() {
        this.estado = "En proceso";
    }

    public Iteracion(int id_iteracion, int cantidadSemanas) {
        this.id_iteracion = id_iteracion;
        this.cantidadSemanas = cantidadSemanas;
        this.estado = "En proceso";
        //Ciclo para crear la cantidad de HashMap de acuerdo al numero de semanas
        for (int i = 0; i < (cantidadSemanas * 5); i++) {
            HashMap<String, Integer> dia = new HashMap<>();
            dia.put("*", 0);
            this.dias.add(dia);
        }
    }

    public int getId_iteracion() {
        return id_iteracion;
    }

    public void setId_iteracion(int id_iteracion) {
        this.id_iteracion = id_iteracion;
    }

    public int getCantidadSemanas() {
        return cantidadSemanas;
    }

    public void setCantidadSemanas(int cantidadSemanas) {
        this.cantidadSemanas = cantidadSemanas;
    }

    public LinkedList<HashMap> getDias() {
        return dias;
    }

    public void setDias(LinkedList<HashMap> dias) {
        this.dias = dias;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
