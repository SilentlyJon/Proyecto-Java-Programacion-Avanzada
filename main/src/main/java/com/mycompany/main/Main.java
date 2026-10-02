package com.mycompany.main;


import com.mycompany.main.Persistencia.LectorDeDatos;
import com.mycompany.main.Persistencia.EscritorDeDatos;
import com.mycompany.proyecto.menu.MenuConsola;
import com.mycompany.proyecto.modelo.Inmobiliaria;
import com.mycompany.proyecto.ventana.MenuVentana;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Inmobiliaria inmobiliaria = new Inmobiliaria("Inmobiliaria Chile","Av. Principal","contaco@inmobiliaria.cl");

        System.out.println("Cargando los datos......");
        LectorDeDatos.cargarProyectos("proyectos.txt", inmobiliaria);
        LectorDeDatos.cargarDepartamento("departamentos.txt", inmobiliaria);
        
        System.out.println("Selecciona la forma de visualizacion: ");
        System.out.println("1) Consola");
        System.out.println("2) Ventana");

        int opcionMenu = scanner.nextInt();
        
        switch (opcionMenu){
            case 1:
                MenuConsola menu = new MenuConsola(inmobiliaria);
                menu.mostrarMenu();
                
                System.out.println("Guardando los datos....");
                EscritorDeDatos.guardarProyectos("proyectos.txt", inmobiliaria);
                EscritorDeDatos.guardarDepartamento("departamentos.txt", inmobiliaria);
                break;
            case 2:
                System.out.println("Abriendo Ventana.....");
                javax.swing.SwingUtilities.invokeLater(() -> {
                    MenuVentana ventana = new MenuVentana(inmobiliaria);
                ventana.setVisible(true);
            });
                break;
            default:
                System.out.println("Opcion Invalida,");

        }
        
        scanner.close();
   }
}
