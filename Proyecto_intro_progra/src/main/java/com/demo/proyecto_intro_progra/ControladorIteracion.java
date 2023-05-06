package com.demo.proyecto_intro_progra;

import java.util.HashMap;
import javax.swing.JOptionPane;
import java.util.LinkedList;

public class ControladorIteracion {

    public LinkedList<Iteracion> iteraciones = new LinkedList<>();

    public void rIteracion(int n) {
        //Obtencion de datos en variables para poder añadir a los objetos
        String lectura = "";
        //int id_iteracion = 0;
        int cantidadSemanas = 0;
        int validarId = 0;
        //Validacion para no colocar string ni un numero que no esté en el rango
        do {
            lectura = JOptionPane.showInputDialog("Digite el número de semanas por favor :  #" + n + ":");
            try{
                validarId = Integer.parseInt(lectura);
                if (validarId < 2 || validarId > 4) {
                    JOptionPane.showMessageDialog(null, "¡Opción inválida!\n\nDebe escribir un número de semanas entre 2 y 4...");
                } else {
                    cantidadSemanas = validarId;
                }
            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "¡Error! El valor ingresado no es un número entero");
            }
        } while (validarId < 2 || validarId > 4);

        //Asignacion de valores a los atributos del objeto
        Iteracion iteracion = new Iteracion(n, cantidadSemanas);
        iteraciones.add(iteracion);
        JOptionPane.showMessageDialog(null, "Se ha agregado la iteración su estado es Pendiente #" + n + ".");
    }

    public void regIteraciones() {
        // Array para guardar los objetos desarrolladores
        String lectura;
        int cantidad;

        // Validación de datos para seleccionar entre 1 y 5 desarrolladores
        String[] listaOpciones = {"1", "2", "3"};
        lectura = (String) JOptionPane.showInputDialog(null, "Elija cuántas iteraciones desea registrar",
                "Iteraciones",
                JOptionPane.DEFAULT_OPTION,
                null,
                listaOpciones, listaOpciones[0]);

        cantidad = Integer.parseInt(lectura);

        //Ciclo for para crear las instancias requeridas por el usuario y llenar los atributos de las mismas
        for (int i = 0; i < cantidad; i++) {
            rIteracion(i + 1);
        }
    }

    public int solicitaeIdIteracion() {
        String lectura = "";
        String iteracion = "Id Semanas\n\n";
        int idVista = 0;
        int idLectura = 0;
        for (int i = 0; i < iteraciones.size(); i++) {
            Iteracion vista = new Iteracion();
            vista = iteraciones.get(i);
            if (vista.getEstado().equals("En proceso")) {
                iteracion += vista.getId_iteracion() + ": " + vista.getCantidadSemanas() + "\n";
            }
        }
        // Validacion para que se coloque un id de la iteracion real
        do {
            lectura = JOptionPane.showInputDialog(null, iteracion + "\nDigite el id de la iteracion a la que desea asignar el trabajo");
            try{
                idLectura = Integer.parseInt(lectura);
                for (int i = 0; i < iteraciones.size(); i++){
                    Iteracion vista = new Iteracion();
                    vista = iteraciones.get(i);
                    idVista = vista.getId_iteracion();
                    if (idLectura == 0){
                        JOptionPane.showMessageDialog(null, "Debe escribir un id de la iteración mayor a 0...");
                    }
                    if (idLectura == idVista){
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Debe escribir un id de la iteración válido...");
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: El valor ingresado no es un número");
            }
           

        } while(idLectura == 0);
        
        int id = Integer.parseInt(lectura);
        return id;
    }

    public int obtenerSemanasIteracion(int idIteracion) {
        int cantidadSemanas = 0;
        for (int i = 0; i < iteraciones.size(); i++) {
            Iteracion iteracion = new Iteracion();
            iteracion = iteraciones.get(i);
            if (idIteracion == iteracion.getId_iteracion()) {
                cantidadSemanas = iteracion.getCantidadSemanas();
            }
        }
        return cantidadSemanas;

    }

    public boolean validarCantidadDeDias(int dia, int idIteracion, int esfuerzo) {
        boolean valido = false;
        int cantidad = obtenerSemanasIteracion(idIteracion);
        if ((cantidad * 5) >= (dia + esfuerzo - 1)) {
            valido = true;
        }
        return valido;
    }

    //Falta validacion de datos
    public Iteracion obtenerIteracionId(int id) {
        Iteracion iteracion = new Iteracion();
        for (int i = 0; i < iteraciones.size(); i++) {
            int idTemp = iteraciones.get(i).getId_iteracion();
            if (id == idTemp) {
                iteracion = iteraciones.get(i);
            }
        }
        return iteracion;
    }

    public boolean validarDesarrollador(String siglas, int esfuerzo, int dia, int id) {
        boolean valido = true;
        Iteracion iteracion = obtenerIteracionId(id);
        int posicionDia = dia - 1;
        int posicionFinal = posicionDia + esfuerzo;
        LinkedList<HashMap> dias = iteracion.getDias();
        for (int i = posicionDia; i < posicionFinal; i++) {
            HashMap<String, Integer> diaHash = dias.get(i);
            for (String j : diaHash.keySet()) {
                if (j.equals(siglas)) {
                    valido = false;
                }
            }
        }
        return valido;
    }

    public void asignarDesarrolador(int idIteracion, String siglas, int idRequisito, int dia, int esfuerzo) {
        Iteracion iteracion = obtenerIteracionId(idIteracion);
        LinkedList<HashMap> dias = iteracion.getDias();
        int posicionDia = dia - 1;
        int posicionFinal = posicionDia + esfuerzo;
        for (int i = posicionDia; i < posicionFinal; i++) {
            HashMap<String, Integer> diaHash = dias.get(i);
            diaHash.put(siglas, idRequisito);
            dias.set(i, diaHash);
        }

        iteracion.setDias(dias);

        iteraciones.set(idIteracion - 1, iteracion);
        JOptionPane.showMessageDialog(null, "Se asignó exitosamente.");
    }

    public void mostrarIteracion() {
        String lectura = JOptionPane.showInputDialog("Digite el Id de la iteración que desea ver:");
        int idIteracion = Integer.parseInt(lectura);
        Iteracion iteracion = obtenerIteracionId(idIteracion);
        LinkedList<HashMap> dias = iteracion.getDias();
        String mostrar = "";
        for (int i = 0; i < dias.size(); i++) {
            mostrar += "Día " + (i + 1) + "\n";
            HashMap<String, Integer> dia = dias.get(i);
            for (String j : dia.keySet()) {
                if (!"*".equals(j)) {
                    mostrar += "RQ: " + dia.get(j) + "- Des:" + j + "\n";
                }
            }
        }
        JOptionPane.showMessageDialog(null, mostrar);
    }
    //TODO: Metodos necesarios para el reporte del resumen de iteraciones

    // CIERRE DE ITERACION (Se crea el método)
    public void cambiarEstadoIteracion() {
        String lectura = JOptionPane.showInputDialog("Digite el Id de la iteracion que desea cerrar");
        int idIteracion = Integer.parseInt(lectura);
        Iteracion iteracion = new Iteracion();
        for (int i = 0; i < iteraciones.size(); i++) {
            iteracion = iteraciones.get(i);
            if (idIteracion == iteracion.getId_iteracion()) {
                iteracion.setEstado("Finalizado");
                iteraciones.set(i, iteracion);
            }
        }
    }
    
    public String mostrarIteraciones(){
        String mensaje = "";
        Iteracion lista = new Iteracion();
        
        if(iteraciones.size() == 0){
            mensaje = "No se ha agregado ninguna iteración.";
        }else{
            for (int i = 0; i < iteraciones.size(); i++) {
                lista = iteraciones.get(i);
                mensaje += (i+1) + ". " + lista.getId_iteracion() + ", " + lista.getCantidadSemanas() + ", " + lista.getEstado() + ".\n";
            }
        }
        
        return mensaje;
    }
}
