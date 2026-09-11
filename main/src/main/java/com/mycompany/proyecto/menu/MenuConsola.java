package com.mycompany.proyecto.menu;

import com.mycompany.proyecto.modelo.Departamento;
import com.mycompany.proyecto.modelo.Inmobiliaria;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;


public class MenuConsola {
    private Inmobiliaria inmobiliaria;
    private Scanner scanner;
    private final NumberFormat formatoPrecio = NumberFormat.getNumberInstance(new Locale("es", "CL"));

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
                    funcionalidadEspecial();
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

    /**
     * SIA-9: Recomendador de departamentos segun presupuesto del cliente.
     * Filtra, en todos los proyectos, el subconjunto de departamentos
     * DISPONIBLES cuyo precio final no supere el presupuesto ingresado.
     */
    private void funcionalidadEspecial(){
        System.out.println("=== Recomendador de departamentos segun presupuesto ===");
        System.out.println("Ingrese el presupuesto maximo del cliente: ");
        double presupuesto = scanner.nextDouble();
        scanner.nextLine();

        if(presupuesto <= 0){
            System.out.println("El presupuesto debe ser mayor a cero.");
            return;
        }

        Map<String, List<Departamento>> resultado = inmobiliaria.buscarDisponiblesPorPresupuesto(presupuesto);

        if(resultado.isEmpty()){
            System.out.println("No hay departamentos disponibles dentro de ese presupuesto.");
            return;
        }

        System.out.println("Opciones disponibles para un presupuesto de $" + formatoPrecio.format(Math.round(presupuesto)) + ":");
        for(Map.Entry<String, List<Departamento>> entrada : resultado.entrySet()){
            System.out.println("Proyecto: " + entrada.getKey());
            for(Departamento d : entrada.getValue()){
                System.out.println("   " + d.mostrarInformacion()
                        + " | Precio final: $" + formatoPrecio.format(Math.round(d.calcularPrecioFinal())));
            }
        }
    }
}