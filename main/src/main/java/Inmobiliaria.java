import java.util.HashMap;
import java.util.Map;


public class Inmobiliaria {
    private String nombre;
    private String direccio;
    private Map<String, Proyecto> proyecto;
    private String contacto;
    
    public Inmobiliaria(String nombre, String direccion, String contacto){
        this.nombre = nombre;
        this.direccion = direccion;
        this.contacto = contacto;
        this.proyecto = new HashMap<>();
    }

    public String getNombre(){
        return nombre;
    }
   
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getDireccion(){
        return direccion;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }

    public String getContacto(){
        return contacto;
    }

    public void setContacto(String contacto){
        this.contacto = contacto
    }

    public Map<> getProyectos(){
        return proyectos;
    }

    public void setProyectos(){
        this.proyectos = proyectos;
    }
}
