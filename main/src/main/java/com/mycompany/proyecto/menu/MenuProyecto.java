package com.mycompany.proyecto.menu;

import com.mycompany.proyecto.modelo.Proyecto;
import com.mycompany.proyecto.modelo.NivelDemanda;
import com.mycompany.proyecto.modelo.Inmobiliaria;
import java.util.Scanner;

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
        System.out.println("Ingrese el codigo del Proyecto: ");
        String codigo = scanner.nextLine();
        
        System.out.println("Ingrese el nombre del Proyecto: ");
        String nombre = scanner.nextLine();
        
        System.out.println("Ingrese la ubicacion del Proyecto: ");
        String ubicacion = scanner.nextLine();
        
        System.out.println("Ingrese la demanda del Proyecto: ");
        NivelDemanda demanda = seleccionarDemanda();
        
        Proyecto proyecto = new Proyecto(codigo, nombre, ubicacion, demanda);
        inmobiliaria.agregarProyecto(proyecto);
        System.out.println("Se agrego el nuevo proyecto.");
    }

    private void mostrarProyecto(){
        System.out.println("Ingrese el codigo del Proyecto: ");
        String codigo = scanner.nextLine();
        
        inmobiliaria.mostrarProyecto(codigo);
    }

    private void buscarProyecto(){
        System.out.println("Ingrese el codigo del proyecto: ");
        String codigo = scanner.nextLine();
        
        Proyecto proyecto = inmobiliaria.buscarProyecto(codigo);

        if(proyecto != null){
            System.out.println("Se encontro el proyecto.");
            System.out.println(proyecto);
        }else{
            System.out.println("No se encontro el proyecto.");
        }

    }

    private void modificarProyecto(){
        System.out.println("Ingrese el codigo del Proyecto: ");
        String codigo = scanner.nextLine();
        Proyecto proyecto = inmobiliaria.buscarProyecto(codigo);

        if(proyecto == null){
          System.out.println("No se encontro el proyecto.");  
            return;
        }
        
        System.out.println("Ingrese el nombre del Proyecto: ");
        String nombre = scanner.nextLine();
        
        System.out.println("Ingrese la ubicacion del Proyecto: ");
        String ubicacion = scanner.nextLine();
        
        System.out.println("Ingrese la demanda del Proyecto: ");
        NivelDemanda demanda = seleccionarDemanda();

        inmobiliaria.modificarProyecto(codigo, nombre, ubicacion, demanda);
        System.out.println("Se modifico el proyecto.");

    }

    private void eliminarProyecto(){
        System.out.println("Ingrese el codigo del Proyecto: ");
        String codigo = scanner.nextLine();
        
        Proyecto proyecto = inmobiliaria.buscarProyecto(codigo);
   
        if(proyecto != null){
            System.out.println("Se encontro el proyecto");
            inmobiliaria.eliminarProyecto(codigo);
            System.out.println("Se elimino el proyecto.");
        }else{
            System.out.println("No se encontro el proyecto.");
        }
    }

    private NivelDemanda seleccionarDemanda(){
        System.out.println("Seleccione un tipo de Demanda");
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
