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
    
    public void agregarProyecto(){
        proyecto.put(proyecto.getCodigo(),proyecto);
    }

    public Proyecto buscarProyecto(String codigo){
        return proyecto.get(codigo);
    }

    public void modificarProyecto(String codigo, String nuevoNombre, String nuevaUbicacion, NivelDemanda nuevaDemanda){
        Proyecto proyecto = proyecto.get(codigo);
        
        if(proyecto != null){
            proyecto.setNombre(nuevoNombre);
            proyecto.setUbicacion(nuevaUbicacion);
            proyecto.setDemanda(nuevaDemanda);
        }
    }

    public void eliminarProyecto(String codigo){
        proyectos.remove(codigo);
    }

    public void mostrarProyecto(){
        System.out.println(proyecto);
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
        this.contacto = contacto;
    }

    public Map<String, Proyecto> getProyectos(){
        return proyectos;
    }

    public void setProyectos(){
        this.proyectos = proyectos;
    }
}
