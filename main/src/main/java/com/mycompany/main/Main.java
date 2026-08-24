import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Selecciona la forma de visualización");
        System.out.println("1) Consola");
        System.out.println("2) Ventana");
        
        int opcion = scanner.nextInt();
        
        switch (opcion){
            case 1:
                MenuConsola menu = new MenuConsola();
                break;
            case 2:
                System.out.println("Abriendo Ventana.....");
                break;
            default:
                System.out.println("Opción Inválida");

        }
        
        scanner.close();
        
        System.out.println("Hello World!");
        System.out.println("Prueba");
    }
}
