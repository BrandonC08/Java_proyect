package com.demo.proyecto_intro_progra;

import java.util.LinkedList;
import javax.swing.JOptionPane;

public class ControladorBacklog {
    
    public LinkedList<Backlog> requisitos = new LinkedList<>();
    // Método para validación de Strings

    public static boolean esString(String str) {
        return str.matches("[a-zA-Z]+");
    }

    public void regRequerimientosBacklog() {
        // Array para guardar los objetos backlog
        String lectura;
        int cantidad = 0;
        //Validacion para que se coloquen solo valores enteros
        do {
            lectura = JOptionPane.showInputDialog("Digite cuántos requerimientos desea registrar:");
            try {
                cantidad = Integer.parseInt(lectura);
                break;
            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Error: El valor ingresado no es un número entero");
            } 
            if (cantidad > 50 || cantidad < 1) {
                JOptionPane.showMessageDialog(null, "Opción inválida\n\nDigite una opción entre 1 y 50...");
            }
        } while (cantidad > 50 || cantidad < 1);


        //Ciclo for para crear las instancias requeridas por el usuario y llenar los atributos de las mismas
        for (int i = 0; i < cantidad; i++) {
            Backlog backlog = new Backlog();
            ingresarDatosBacklog(backlog, i + 1);
        }
    }

    public void ingresarDatosBacklog(Backlog backlog, int n) {
        String lectura;
        int validarId = 0;
        int id = 0;
        String nombre = "";
        int validarEsfuerzo = 0;
        int esfuerzo = 0;
        //*****************************
        //Validacion para que coloquen valores de tipo entero, y si no lo hace se devuelve
        //*****************************
        do {
            lectura = JOptionPane.showInputDialog("Digite el Id del requerimiento #" + n + ":");
            try{
                validarId = Integer.parseInt(lectura);
                 if (validarId <= 0) {
                    JOptionPane.showMessageDialog(null, "¡Opción inválida!\n\nDebe escribir un número mayor a 0...");
                    break;
                } else {
                id = validarId;
            }
            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Error: El valor ingresado no es un número entero");
            }
           
        } while (validarId <= 0);
        //Validacion para solo ingresar string sin espacios
        do {
            lectura = JOptionPane.showInputDialog("Digite el nombre del requerimiento #" + n + ":");
            if (!esString(lectura)) {
                JOptionPane.showMessageDialog(null, "¡Opción inválida!\n\nDebe escribir las siglas sin espacios y sin carácteres especiales...");
            } else {
                nombre = (lectura);
            }
        } while (!esString(lectura));

        do {
            lectura = JOptionPane.showInputDialog("Digite el esfuerzo del requerimiento #" + n + ":");            
            try{
                validarEsfuerzo = Integer.parseInt(lectura);
                if (validarEsfuerzo <= 0) {
                    JOptionPane.showMessageDialog(null, "¡Opción inválida!\n\nDebe escribir un número mayor a 0...");
                } else {
                    esfuerzo = validarEsfuerzo;
            }
            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Error: El valor ingresado no es un número entero");
            }
        } while (validarEsfuerzo <= 0);


        //Asignacion de valores a los atributos del objeto
        backlog.setId(id);
        backlog.setNombre(nombre);
        backlog.setEsfuerzo(esfuerzo);
        requisitos.add(backlog);
        JOptionPane.showMessageDialog(null, "Se ha agregado el requerimiento #" + n + ".");
    }

    public int obtenerEsfuerzoPorId(int id) {

        int esfuerzo = 0;
        for (int i = 0; i < requisitos.size(); i++) {
            Backlog requisito = new Backlog();
            requisito = requisitos.get(i);
            if (id == requisito.getId()) {
                esfuerzo = requisito.getEsfuerzo();
            }
        }
        return esfuerzo;
    }
    
    public int solicitarId(){
        String requisito = "ID Nombre\n\n";
        for (int i = 0; i < requisitos.size(); i++) {
            Backlog vista = new Backlog();
            vista = requisitos.get(i);
            if (vista.getEstado().equals("Pendiente")){
             requisito += vista.getId() + ": " + vista.getNombre() + "\n";   
            }            
        }
        //Validacion para que se coloque numero entero y un id valido
        int id = 0;
        boolean booleano = false;
        do{
            String lectura = JOptionPane.showInputDialog(null, requisito + "\nDigite el Id del requerimiento que desea asignar");
                try{
                    id = Integer.parseInt(lectura);
                    if (id <= 0){
                        JOptionPane.showMessageDialog(null, "El valor ingresado no es un id válido");  
                    }
                    for (int i = 0; i < requisitos.size(); i++){
                        Backlog mostrar = new Backlog();
                        mostrar = requisitos.get(i);
                        if (id == mostrar.getId()){
                            booleano = true;
                            break;  
                        } else {
                            JOptionPane.showMessageDialog(null, "El id colocado no existe...");   
                            }
                    } 
                } catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Error: El valor ingresado no es un número entero");   
            }
        } while (booleano == false);
        return id;
    }
    public void cambiarEstadoRequisito(int idRequisito){
        Backlog requisito = new Backlog();
        for (int i = 0; i < requisitos.size(); i++){
            requisito = requisitos.get(i);
            if (idRequisito == requisito.getId()){
                requisito.setEstado("En proceso");
                requisitos.set(i, requisito);
            }
        }
    }
    public String mostrarRequerimientos(){
        String mensaje = "";
        Backlog lista = new Backlog();
        
        if(requisitos.isEmpty()){
            mensaje = "No se ha agregado ningún requerimiento.";
        }else{
            for (int i = 0; i < requisitos.size(); i++) {
                lista = requisitos.get(i);
                mensaje += (i+1) + ". " + lista.getId() + ", " + lista.getNombre() + ", " + lista.getEsfuerzo() + ", " + lista.getEstado() + ".\n";
            }
        }
        
        return mensaje;
    }
}
