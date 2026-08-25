import java.util.Scanner;

public class MenuProyecto {
    private Inmobiliaria inmobiliaria;
    private Scanner scanner;

    public MenuProyecto(Inmobiliaria inmobiliaria){
        this.inmobilaria = inmobilaria;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu(){
        int opcion;
        do{
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
        NivelDemanda demanda = scanner.seleccionarDemanda();
        
        Proyecto proyecto = new Proyecto(codigo, nombre, ubicacion, demanda);
        inmobilaria.agregarProyecto(proyecto);
    }

    private void mostrarProyecto(){
        inmbilaria.mostrarProyecto();
    }

    private void buscarProyecto(){
        String codigo = scanner.nextLine();
        
        Proyecto proyecto = inmobilaria.buscarProyecto(codigo);

        if(proyecto != null){
            System.out.println("se encontro");
            System.out.println(proyecto);
        }else{
            System.out.println("no se encontro");
        }

    }

    public void modificarProyecto(){
        String codigo = scanner.nextLine();
        Proyecto proyecto = inmobilaria.buscarProyecto(codigo);

        if(proyecto == null){
            
            return;
        }

        String nombre = scanner.nextLine();
        String ubicacion = scanner.nextLine();
        NivelDemanda demanda = seleccionarDemanda();

        inmobilaria.modificarProyecto(codigo, nombre, ubicacion, demanda);


    }

    private void eliminarProyecto(){
        String codigo = scanner.nextLine();
        Proyecto proyecto = inmobilaria.buscarInmobilaria(codigo);

        if(proyecto != null){
            inmobilaria.eliminarProyecto(codigo);
        }else{
            System.out.println("no se encontro");
        }
    }

    private NivelDemanda seleccionarDemanda(){
        System.out.println("1) Baja");
        System.out.println("2) Media");
        System.out.println("3) Alta");
        
        int opcion = scanner.nextLine();
        
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
