package com.mycompany.main;

import java.util.Scanner;
import <default package>.Inmobiliaria;
import <default package>.Proyecto;
import <default package>.NivelDemanda;

public class MenuProyecto {
    private Inmobiliaria inmobiliaria;
    private Scanner scanner;

    public MenuProyecto(Inmobiliaria inmobiliaria){
        this.inmobiliaria = inmobiliaria;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu(){
        int opcion;
        do{
            
            System.out.println("1 - Agregar proyecto");
            System.out.println("2 - Mostrar proyecto");
            System.out.println("3 - Buscar proyecto");
            System.out.println("4 - Modificar proyecto");
            System.out.println("5 - Eliminar proyecto");
            System.out.println("0 - Salir");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion){
                case 1:
                    agregarProyecto();
                    break;
                case 2:
                    mostrarProyecto();
                    break;
                case 3:
                    buscarProyecto();
                    break;
                case 4:
                    modificarProyecto();
                    break;
                case 5:
                    eliminarProyecto();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("opcion invalida");
            }
        }while(opcion != 0);
    }

    private void agregarProyecto(){
        String codigo = scanner.nextLine();
        String nombre = scanner.nextLine();
        String ubicacion = scanner.nextLine();
        NivelDemanda demanda = seleccionarDemanda();
        
        Proyecto proyecto = new Proyecto(codigo, nombre, ubicacion, demanda);
        inmobiliaria.agregarProyecto(proyecto);
    }

    private void mostrarProyecto(){
        String codigo = scanner.nextLine();
        inmobiliaria.mostrarProyecto(codigo);
    }

    private void buscarProyecto(){
        String codigo = scanner.nextLine();
        
        Proyecto proyecto = inmobiliaria.buscarProyecto(codigo);

        if(proyecto != null){
            System.out.println("se encontro");
            System.out.println(proyecto);
        }else{
            System.out.println("no se encontro");
        }

    }

    private void modificarProyecto(){
        String codigo = scanner.nextLine();
        Proyecto proyecto = inmobiliaria.buscarProyecto(codigo);

        if(proyecto == null){
            
            return;
        }

        String nombre = scanner.nextLine();
        String ubicacion = scanner.nextLine();
        NivelDemanda demanda = seleccionarDemanda();

        inmobiliaria.modificarProyecto(codigo, nombre, ubicacion, demanda);


    }

    private void eliminarProyecto(){
        String codigo = scanner.nextLine();
        Proyecto proyecto = inmobiliaria.buscarProyecto(codigo);

        if(proyecto != null){
            inmobiliaria.eliminarProyecto(codigo);
        }else{
            System.out.println("no se encontro");
        }
    }

    private NivelDemanda seleccionarDemanda(){
        System.out.println("1) Baja");
        System.out.println("2) Media");
        System.out.println("3) Alta");
        
        int opcion = Integer.parseInt(scanner.nextLine());
        
        switch (opcion){
            case 1: 
                return NivelDemanda.BAJA;
            case 2:
                return NivelDemanda.MEDIA;
            case 3:
                return NivelDemanda.ALTA;
            default:
                return NivelDemanda.BAJA;
        }
    }
}
