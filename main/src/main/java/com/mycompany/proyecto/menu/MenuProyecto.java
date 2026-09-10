package com.mycompany.proyecto.menu;

import com.mycompany.main.Persistencia.EscritorDeDatos;
import com.mycompany.proyecto.modelo.Proyecto;
import com.mycompany.proyecto.modelo.NivelDemanda;
import com.mycompany.proyecto.modelo.Inmobiliaria;
import com.mycompany.proyecto.excepciones.ProyectoDuplicadoException;
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

        try{
            inmobiliaria.agregarProyecto(proyecto);
            EscritorDeDatos.guardarProyectos("proyectos.txt", inmobiliaria);
            System.out.println("Se agrego el nuevo proyecto y se guardaron los cambios.");
        }catch(ProyectoDuplicadoException e){
            System.out.println("Error: " + e.getMessage());
        }
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
        
        EscritorDeDatos.guardarProyectos("proyectos.txt", inmobiliaria);
        System.out.println("Se modifico el proyecto y se guardaron los cambios.");

    }

    private void eliminarProyecto(){
        System.out.println("Ingrese el codigo del Proyecto: ");
        String codigo = scanner.nextLine();
        
        Proyecto proyecto = inmobiliaria.buscarProyecto(codigo);
   
        if(proyecto != null){
            System.out.println("Se encontro el proyecto");
            inmobiliaria.eliminarProyecto(codigo);
            
            EscritorDeDatos.guardarProyectos("proyectos.txt", inmobiliaria);
            System.out.println("Se elimino el proyecto y se guardaron los cambios.");
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