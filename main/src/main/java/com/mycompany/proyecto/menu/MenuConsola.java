package com.mycompany.proyecto.menu;

import com.mycompany.proyecto.modelo.Inmobiliaria;
import java.util.Scanner;


public class MenuConsola {
    private Inmobiliaria inmobiliaria;
    private Scanner scanner;

    public MenuConsola(Inmobiliaria inmobiliaria){
        this.inmobiliaria = inmobiliaria;
        this.scanner = new Scanner(System.in);
    }
    
    public void mostrarMenu(){
        int opcion;
        do {
            System.out.println("Menu de gestion de inmobilaria");
            System.out.println("1 - Gestion de proyecto");
            System.out.println("2 - Gestion de departamento");
            System.out.println("3 - Funcionalidad especial");
            System.out.println("0 - Salir");
            System.out.println("Selecciones una opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion){
                case 1:
                    System.out.println("Menu Proyecto");
                    menuProyecto();
                    break;
                case 2:
                    System.out.println("Menu Departamento");
                    menuDepartamento();
                    break;
                case 3:
                    System.out.println("Funcion especial");
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción Inválida");
            }
        } while (opcion != 0);
    }

    private void menuProyecto(){
        MenuProyecto menuProyecto = new MenuProyecto(inmobiliaria);
        menuProyecto.mostrarMenu();
    }
    
    private void menuDepartamento(){
        MenuDepartamento menuDepartamento = new MenuDepartamento(inmobiliaria);
        menuDepartamento.mostrarMenu();
    }
}
