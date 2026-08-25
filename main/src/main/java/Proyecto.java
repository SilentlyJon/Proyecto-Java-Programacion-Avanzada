import java.util.ArrayList;

public class Proyecto {
    private String codigo;
    private String nombre;
    private String ubicacion;
    private NivelDemanda demanda;
    private ArrayList<Departamento> departamentos;

    public Proyecto(String codigo, String nombre, String ubicacion, NivelDemanda demanda){
        this.codigo = codigo;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.demanda = demanda;
        this.departamentos = new ArrayList<>();
    }
    
    public String getCodigo(){
        return codigo;
    }

    public void set(String codigo){
        this.codigo = codigo;
    }

    public String getNombre(String nombre){
        return nombre;
    }

    public void setNombre(){
        this.nombre = nombre;
    }

    public String getUbicacion(){
        return ubicacion;
    }

    public void setUbicacion(String ubicacion){
        this.ubicacion = ubicacion;
    }
    public NivelDemanda getDemanda(NivelDemanda){
        return demanda;
    }

    public void setDemanda(NivelDemanda demanda){
        this.demanda = demanda;
    }

}
