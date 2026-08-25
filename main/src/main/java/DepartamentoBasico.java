public class DepartamentoBasico extends Departamento{
    public DepartamentoBasico(String id, int numero, double metrosCuadrados, double precioBase, NivelDemanda demanda, EstadoDepartamento estado){
        super(id, numero, metrosCuadrados, precioBase, demanda, estado);
    }
    
    @Override
    public double calcularPrecioFinal(){
        return getPrecioBase(); 
    }
    
    @Override
    public String mostrarInformacion(){
       return super.mostarInformacion() + " | Tipo: Basico";
    }
}
