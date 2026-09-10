package com.mycompany.main;


import com.mycompany.main.Persistencia.LectorDeDatos;
import com.mycompany.main.Persistencia.EscritorDeDatos;
import com.mycompany.proyecto.menu.MenuConsola;
import com.mycompany.proyecto.modelo.Inmobiliaria;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
// EJEMPLO
        Inmobiliaria inmobiliaria = new Inmobiliaria("Inmobiliaria Chile","Av. Principal","contaco@inmobilaria.cl");
//
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
                break;
            case 2:
                System.out.println("Abriendo Ventana.....");
                
                break;
            default:
                System.out.println("Opcion Invalida,");

        }
        System.out.println("Guardando los datos....");
        EscritorDeDatos.guardarProyectos("proyectos.txt", inmobiliaria);
        EscritorDeDatos.guardarDepartamento("departamentos.txt", inmobiliaria);
        scanner.close();
   }
}
