package com.mycompany.proyecto.menu;

import java.util.Scanner;
import com.mycompany.main.Persistencia.EscritorDeDatos;
import com.mycompany.proyecto.modelo.Inmobiliaria;
import com.mycompany.proyecto.modelo.Proyecto;
import com.mycompany.proyecto.modelo.Departamento;
import com.mycompany.proyecto.modelo.DepartamentoBasico;
import com.mycompany.proyecto.modelo.DepartamentoPremium;
import com.mycompany.proyecto.modelo.NivelDemanda;
import com.mycompany.proyecto.modelo.EstadoDepartamento;
import com.mycompany.proyecto.excepciones.DatosInvalidosException;

public class MenuDepartamento {
    private Inmobiliaria inmobiliaria;
    private Scanner scanner;

    public MenuDepartamento(Inmobiliaria inmobilaria) {
        this.inmobiliaria = inmobilaria;
        this.scanner = new Scanner(System.in);
    }
    
    public void mostrarMenu(){
        int opcion;
        do {
            System.out.println("1 - Agregar departamento");
            System.out.println("2 - Mostrar departamento");
            System.out.println("3 - Buscar departamento");
            System.out.println("4 - Modificar departamento");
            System.out.println("5 - Eliminar departamento");
            System.out.println("6 - Cambiar estado de un departamento");
            System.out.println("0 - Volver");
            System.out.println("Seleccione una opcion: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch(opcion){
                case 1:
                    agregarDepartamento();
                    break;
                case 2:
                    mostrarDepartamento();
                    break;
                case 3:
                    buscarDepartamento();
                    break;
                case 4:
                    modificarDepartamento();
                    break;
                case 5:
                    eliminarDepartamento();
                    break;
                case 6:
                    cambiarEstadoDepartamento();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        }while(opcion != 0);
    }
    
    private void agregarDepartamento(){
        System.out.println("Ingrese el codigo del Proyecto al que pertenece el deparamento: ");
        
        String codigoProyecto = scanner.nextLine();
        Proyecto proyecto = inmobiliaria.buscarProyecto(codigoProyecto);
        
        if(proyecto == null){
            System.out.println("No se encontro el proyecto.");
            return;
        }

        System.out.println("Ingrese el ID del departamento: ");
        String id = scanner.nextLine();
        
        System.out.println("Ingrese el numero del departamento: ");
        int numero = scanner.nextInt();
        
        System.out.println("Ingrese los metros cuadrados: ");
        double metrosCuadrados = scanner.nextDouble();
        
        System.out.println("Ingrese el precio base del departamento: ");
        double precioBase = scanner.nextDouble();
        
        scanner.nextLine();
        
        NivelDemanda demanda = seleccionarDemanda();
        
        EstadoDepartamento estado = seleccionarEstado();        
        
        System.out.println("Tipos de Departamento: ");
        System.out.println("1 - Basico");
        System.out.println("2 - Premium");
        System.out.println("Seleccione el tipo de departamento: ");
        
        int tipo = scanner.nextInt();
        scanner.nextLine();
        
        try{
            Departamento departamento;

            switch (tipo) {
                case 1:
                    departamento = new DepartamentoBasico(id, numero, metrosCuadrados, precioBase, demanda, estado);
                    break;
                case 2:
                    System.out.println("Ingrese si tiene Pisina: ");
                    boolean tienePisina = scanner.nextBoolean();

                    System.out.println("Ingrese si tiene garage: ");
                    boolean garage = scanner.nextBoolean();

                    System.out.println("Ingrese si tiene bidet: ");
                    boolean tieneBidet = scanner.nextBoolean();

                    departamento = new DepartamentoPremium(tienePisina, garage, tieneBidet, id, numero, metrosCuadrados, precioBase, demanda, estado);
                    break;
                default:
                    System.out.println("Tipo de departamento invalido.");
                    return;
            }
            proyecto.agregarDepartamento(departamento);

            EscritorDeDatos.guardarDepartamento("departamentos.txt", inmobiliaria);
            System.out.println("El departamento se agrego de forma exitosa y se guardaron los cambios.");
        }catch(DatosInvalidosException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void mostrarDepartamento() {
        System.out.println("Ingrese el codigo del proyecto: ");
        String codigoProyecto = scanner.nextLine();
        
        Proyecto proyecto = inmobiliaria.buscarProyecto(codigoProyecto);
        
        if(proyecto == null){
            System.out.println("No se encontro el proyecto.");
            return;
        }

        System.out.println("Como desea verlos: ");
        System.out.println("1 - Todos");
        System.out.println("2 - Filtrar por estado");
        System.out.println("3 - Filtrar por demanda");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch(opcion){
            case 1:
                proyecto.mostrarDepartamento();
                break;
            case 2:
                EstadoDepartamento estado = seleccionarEstado();
                proyecto.mostrarDepartamento(estado);
                break;
            case 3:
                NivelDemanda demanda = seleccionarDemanda();
                proyecto.mostrarDepartamento(demanda);
                break;
            default:
                System.out.println("Opcion invalida.");
        }
    }

    private void buscarDepartamento() {
        System.out.println("Ingrese el codigo del proyecto : ");
        String codigoProyecto = scanner.nextLine();
        Proyecto proyecto = inmobiliaria.buscarProyecto(codigoProyecto);
        
        if(proyecto == null){
            System.out.println("No se encontro el proyecto.");
            return;
        }
        
        System.out.println("Ingrese el ID del departamento: ");
        String id = scanner.nextLine();
        
        Departamento departamento = proyecto.buscarDepartamento(id);
        
        if(departamento != null){
            System.out.println(departamento.mostrarInformacion());
        }else{
            System.out.println("No se encontro el departamento.");
        }
    }

    private void modificarDepartamento() {
        System.out.println("Ingrese el codigo del proyecto : ");
        String codigoProyecto = scanner.nextLine();
        Proyecto proyecto = inmobiliaria.buscarProyecto(codigoProyecto);
        
        if(proyecto == null){
            System.out.println("No se encontro el proyecto.");
            return;
        }
        
        System.out.println("Ingrese el ID del departamento.");
        String id = scanner.nextLine();
        
        Departamento departamento = proyecto.buscarDepartamento(id);
       
        if(departamento == null){
            System.out.println("No se encontro el departamento. ");
            return;
        }
        
        System.out.println("Ingrese el numero del departamento: ");
        int numero = scanner.nextInt();
        
        System.out.println("Ingrese los metros cuadrados: ");
        double metrosCuadrados = scanner.nextDouble();
        
        System.out.println("Ingrese el precio base del departamento: ");
        double precioBase = scanner.nextDouble();
        
        scanner.nextLine();
        
        NivelDemanda demanda = seleccionarDemanda();
        
        EstadoDepartamento estado = seleccionarEstado();
        
        try{
            proyecto.modificarDepartamento(id, numero, metrosCuadrados, precioBase, demanda, estado);
        }catch(DatosInvalidosException e){
            System.out.println("Error: " + e.getMessage());
            return;
        }
        
        System.out.println("¿El Departamento es Primium?: ");
        System.out.println("1 - Si");
        System.out.println("2 - No");
        System.out.println("Seleccione el tipo de departamento: ");
        
        int tipo = scanner.nextInt();
        scanner.nextLine();
        
        switch (tipo) {
            case 1:
                System.out.println("Ingrese si tiene Pisina (true/false): ");
                boolean tienePiscina = scanner.nextBoolean();
        
                System.out.println("Ingrese si tiene garage (true/false): ");
                boolean garage = scanner.nextBoolean();
        
                System.out.println("Ingrese si tiene bidet (true/false): ");
                boolean tieneBidet = scanner.nextBoolean();
                DepartamentoPremium premium = (DepartamentoPremium) departamento;
                premium.modificarDatosPremium(tienePiscina, garage, tieneBidet);
                break;
            case 2:
                break;
            default:
                System.out.println("Respuesta invalida.");
                return;
        }
        
        EscritorDeDatos.guardarDepartamento("departamentos.txt", inmobiliaria);
        System.out.println("Se modificaron los datos del departamento de forma exitosa y se guardaron los cambios.");
    }

    private void cambiarEstadoDepartamento() {
        System.out.println("Ingrese codigo del proyecto: ");
        String codigoProyecto = scanner.nextLine();

        Proyecto proyecto = inmobiliaria.buscarProyecto(codigoProyecto);

        if(proyecto == null){
            System.out.println("No se encontro el proyecto.");
            return;
        }

        System.out.println("Ingrese el ID del departamento: ");
        String id = scanner.nextLine();

        Departamento departamento = proyecto.buscarDepartamento(id);

        if(departamento == null){
            System.out.println("No se encontro el departamento.");
            return;
        }

        EstadoDepartamento nuevoEstado = seleccionarEstado();

        // Sobrecarga de modificarDatos(): solo cambia el estado, sin tocar el resto de los datos.
        departamento.modificarDatos(nuevoEstado);

        EscritorDeDatos.guardarDepartamento("departamentos.txt", inmobiliaria);
        System.out.println("Se actualizo el estado del departamento y se guardaron los cambios.");
    }

    private void eliminarDepartamento() {
        System.out.println("Ingrese codigo del proyecto: ");
        String codigoProyecto = scanner.nextLine();
        
        Proyecto proyecto = inmobiliaria.buscarProyecto(codigoProyecto);
        
        if(proyecto == null){
            System.out.println("No se encontro el proyecto. ");
            
            return;
        }
        
        System.out.println("Ingrese el ID del departamento: ");
        String id = scanner.nextLine();
        
        Departamento departamento = proyecto.buscarDepartamento(id);
        
        if(departamento != null){
            proyecto.eliminarDepartamento(id);
            
            EscritorDeDatos.guardarDepartamento("departamentos.txt", inmobiliaria);
            System.out.println("Se elimino el departamento y guardaron los cambios.");
        }else{
            System.out.println("No se encontro el departamento.");
        }
    }

    private NivelDemanda seleccionarDemanda() {
        System.out.println("Niveles de demanda: ");
        System.out.println("1 - BAJA");
        System.out.println("2 - MEDIA");
        System.out.println("3 - ALTA");
        System.out.println("Seleccione un nivel de demanda:  ");
        
        int opcion = scanner.nextInt();
        scanner.nextLine();
        
        switch(opcion){
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

    private EstadoDepartamento seleccionarEstado() {
        System.out.println("Estados :");
        System.out.println("1 - DISPONIBLE");
        System.out.println("2 - RESERVADO");
        System.out.println("3 - VENDIDO");
        System.out.println("Seleccione un estado del departamento: ");
        
        int opcion = scanner.nextInt();
        scanner.nextLine();
        
        switch(opcion){
            case 1:
                return EstadoDepartamento.DISPONIBLE;
            case 2:
                return EstadoDepartamento.RESERVADO;
            case 3:
                return EstadoDepartamento.VENDIDO;
            default:
                return EstadoDepartamento.DISPONIBLE;         
        }
        
    }
    
}