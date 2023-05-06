package com.demo.proyecto_intro_progra;

import java.util.LinkedList;
import javax.swing.JOptionPane;

public class ControladorDesarrollador {
    public LinkedList<Desarrollador> desarrolladores = new LinkedList<>();
    // Método para validación de Strings
    public static boolean esString(String str) {
        return str.matches("[a-zA-Z]+");
    }

    public void ingresarDatosDesarolladores(Desarrollador desarrollador, int n) {
        //Obtencion de datos en variables para poder añadir a los objetos
        String lectura;
        String nombre = "";
        String siglas = "";
        double costo = 1;
        double validarCosto = 0;

        //Validación de datos para que no se coloquen enteros, Strings, etc... dependiendo del caso
        //Validación de Strings (Nombre del desarrollador)     
        do {
            lectura = JOptionPane.showInputDialog("Digite el nombre del desarrollador #" + n + ":");
            if (!esString(lectura)) {
                JOptionPane.showMessageDialog(null, "¡Opción inválida!\n\nDebe escribir el nombre sin espacios...");
            } else {
                nombre = lectura;
                break;
            }
        } while (!esString(lectura));

        //Validación de datos (Siglas del desarrollador)
        do {
            lectura = JOptionPane.showInputDialog("Digite las siglas del desarrollador #" + n + ":");
            if (!esString(lectura)) {
                JOptionPane.showMessageDialog(null, "¡Opción inválida!\n\nDebe escribir las siglas sin espacios y sin carácteres especiales...");
            } else {
                siglas = lectura;
                break;
            }
        } while (!esString(lectura));

        //Validación de datos(Costo x día del desarrollador) y que solo se coloquen valores numericos
        do {
            lectura = JOptionPane.showInputDialog("Digite cuánto es el costo por día del desarrollador #" + n + ":");
            try{
                validarCosto = Double.parseDouble(lectura);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "¡Error! El valor ingresado no es un número");
            }
            if (validarCosto <= 0) {
                JOptionPane.showMessageDialog(null, "¡Opción inválida!\n\nDebe escribir un número mayor a 0...");
            } else {
                costo = Integer.parseInt(lectura);
                break;
            }
        } while (validarCosto <= 0);

        //Se asigna el id de manera aleatoria automáticamente (debajo está el código anterior comentado)
        int id = (int) (Math.random() * 1000) + 1;

        //Asignacion de valores a los atributos del objeto
        desarrollador.setNombre(nombre);
        desarrollador.setSiglas(siglas);
        desarrollador.setCosto(costo);
        desarrollador.setId(id);
        desarrolladores.add(desarrollador);

        JOptionPane.showMessageDialog(null, "Se ha agregado el desarrollador #" + n + ".");
    }

    public void regDesarrolladores() {
        // Array para guardar los objetos desarrolladores
        String lectura;
        int cantidad;

        // Validación de datos para seleccionar entre 1 y 5 desarrolladores
        String[] listaOpciones = {"1", "2", "3", "4", "5"};
        lectura = (String) JOptionPane.showInputDialog(null, "Elija cuántos desarrolladores desea registrar:",
                "Desarrolladores",
                JOptionPane.DEFAULT_OPTION,
                null,
                listaOpciones, listaOpciones[0]);

        cantidad = Integer.parseInt(lectura);

        //Ciclo for para crear las instancias requeridas por el usuario y llenar los atributos de las mismas
        for (int i = 0; i < cantidad; i++) {
            Desarrollador desarrollador = new Desarrollador();
            ingresarDatosDesarolladores(desarrollador, i + 1);
        }
    }
    
    public String solicitarSigla(){
        String desarrollador = "Sigla Nombre\n\n";
        boolean siglaDesarrollo = false;
        String lectura = "";
        for (int i = 0; i < desarrolladores.size(); i++) {
            Desarrollador vista = new Desarrollador();
            vista = desarrolladores.get(i);
            desarrollador += vista.getSiglas() + ": " + vista.getNombre() + "\n"; 
            String sigla = vista.getSiglas();
            }
        //Validacion para que se coloquen solos las siglas de desarrollador
        do {
            lectura = JOptionPane.showInputDialog(null, desarrollador + "\nDigite la sigla del desarrollador que desea asignar");
            if (!esString(lectura)){   
                JOptionPane.showMessageDialog(null, "¡Opción inválida!\n\nDebe escribir las siglas sin espacios y sin carácteres especiales...");
            }
            for (int i = 0; i < desarrolladores.size(); i++) {
                Desarrollador vista = new Desarrollador();
                vista = desarrolladores.get(i);
                if (lectura.equals(vista.getSiglas())){
                    siglaDesarrollo = true;
                    break;
                    } else {
                    JOptionPane.showMessageDialog(null, "¡Opción inválida!\n\nDebe escribir las siglas de un desarrollador real...");
                    }   
            }
        } while(siglaDesarrollo == false);
        
        return lectura;
    }
    public double obtenerCostoPorSiglas(String siglas){
        Desarrollador desarrollador = new Desarrollador();
        double costo = 0;
        
        for (int i = 0; i < desarrolladores.size(); i++) {
            desarrollador = desarrolladores.get(i);
            if(desarrollador.getSiglas().equals(siglas)){
                costo = desarrollador.getCosto();
            }
        }
        
        return costo;
    }
    
    public String mostrarDesarrolladores(){
        String mensaje = "";
        Desarrollador lista = new Desarrollador();
        
        if(desarrolladores.isEmpty()){
            mensaje = "No se ha agregado ningún desarrollador.";
        }else{
            for (int i = 0; i < desarrolladores.size(); i++) {
                lista = desarrolladores.get(i);
                mensaje += (i+1) + ". " + lista.getNombre() + ", " + lista.getSiglas() + ", " + lista.getCosto() + ", " + lista.getId() + ".\n";
            }
        }
        
        return mensaje;
    }
}
