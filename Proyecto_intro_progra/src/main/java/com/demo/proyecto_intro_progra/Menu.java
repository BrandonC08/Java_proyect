package com.demo.proyecto_intro_progra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Menu {
    //Variables para el Módulo de Reportes
    public static int contadorDesarrolladores = 0;
    public static int contadorRequerimientos = 0;
    public static int sumaEsfuerzos = 0;
    public static double costoTotal = 0;
    
    //Instancias de los distintos objetos para llamar a los metodos de cada uno
    public static ControladorBacklog Back1 = new ControladorBacklog();
    public static ControladorDesarrollador D1 = new ControladorDesarrollador();
    public static ControladorIteracion I1 = new ControladorIteracion();
    public void menu() {
        JButton a = getButton("1. Registrar a los desarrolladores");
        JButton b = getButton("2. Registrar los requerimientos en Backlog");
        JButton c = getButton("3. Registrar los registros de iteraciones");
        JButton d = getButton("4. Registrar la asignación de trabajo");
        JButton e = getButton("5. Registrar el cierre de una iteración");
        JButton f = getButton("6. Abrir el módulo de reportes");

        Object[] opciones = {a, b, c, d, e, f};

        JOptionPane.showOptionDialog(null, opciones, "Elija una opción:",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null,
                new String[]{"Salir"},
                null);
    }

    public static JButton getButton(String text) {
        final JButton button = new JButton(text);
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                String opcion = actionEvent.getActionCommand().substring(0, 1);
                switch (opcion) {
                    case "1" ->
                        D1.regDesarrolladores();
                    case "2" ->
                        Back1.regRequerimientosBacklog();
                    case "3" ->
                        I1.regIteraciones();
                    case "4" ->
                        asignarTrabajo();   
                    case "5" ->
                        I1.cambiarEstadoIteracion();
                    case "6" ->
                        moduloDeReportes();
                }
            }
        };
        button.addActionListener(actionListener);
        return button;
    }
    //Metodo para hacer las asignaciones de trabajo
    public static void asignarTrabajo(){
        int idRequisito = Back1.solicitarId();
        String siglasDesarrollador = D1.solicitarSigla();
        int idIteracion = I1.solicitaeIdIteracion();
        String lectura = JOptionPane.showInputDialog(null, "\nDigite el día en el cual desea iniciar:");
        int dia = Integer.parseInt(lectura);
        
        int esfuerzo = Back1.obtenerEsfuerzoPorId(idRequisito);
        sumaEsfuerzos += esfuerzo;
        
        double costo = D1.obtenerCostoPorSiglas(siglasDesarrollador);
        costoTotal += costo;
        
        if(I1.validarCantidadDeDias(dia, idIteracion, esfuerzo)){
            if (I1.validarDesarrollador(siglasDesarrollador, esfuerzo, dia,idIteracion)){
                I1.asignarDesarrolador(idIteracion, siglasDesarrollador, idRequisito, dia, esfuerzo);
                contadorDesarrolladores++;
                Back1.cambiarEstadoRequisito(idRequisito);
                contadorRequerimientos++;
            }
            else{
                JOptionPane.showMessageDialog(null, "El desarrollador esta ocupado");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Lo siento el requirimiento necesita mas dias de los que dispone la iteracion");
        }
    }
    //TODO: Submenu para los reportes
    
    public static void moduloDeReportes(){
        
        String[] opciones = {"1", "2", "3", "4", "5"};
        int opcion = JOptionPane.showOptionDialog(null, "Eliga una opción:\n1. Reporte de los requerimientos.\n2. Reporte de los desarrolladores.\n3. Reporte de las iteraciones.\n 4. Reporte visual de una iteración.\n 5. Reporte del resumen de las iteraciones", "Módulo de reportes",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null,
                opciones,
                null);
        /*int opcion = (Integer)JOptionPane.showInputDialog(null, "Elija una opción:", 
                "Módulo de Reportes.", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);*/
        switch (opcion) {
            case 0: 
                JOptionPane.showMessageDialog(null, Back1.mostrarRequerimientos());                
                break;
            case 1:
                JOptionPane.showMessageDialog(null, D1.mostrarDesarrolladores());
                break;
            case 2:
                JOptionPane.showMessageDialog(null, I1.mostrarIteraciones());
                break;
            case 3:
                I1.mostrarIteracion();
                break;
            case 4:
                JOptionPane.showMessageDialog(null, "Cantidad de requerimientos asignados: " + contadorRequerimientos
                + "\nCantidad de desarrolladores asignados: " + contadorDesarrolladores + "\nSuma de esfuerzo de los requerimientos: "
                + sumaEsfuerzos + "\nCosto de la iteración + IVA (13%): " + (costoTotal + (costoTotal * 0.13)));
                break;
        }
    }
}