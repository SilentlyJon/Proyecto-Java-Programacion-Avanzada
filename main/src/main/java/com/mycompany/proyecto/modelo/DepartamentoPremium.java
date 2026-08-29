
package com.mycompany.proyecto.modelo;

public class DepartamentoPremium extends Departamento{
    
    private boolean tienePiscina;
    private boolean garage;
    private boolean tieneBidet;

    public DepartamentoPremium(boolean tienePiscina, boolean garage, boolean tieneBidet, String id, int numero, double metrosCuadrados, double precioBase, NivelDemanda demanda, EstadoDepartamento estado) {
        super(id, numero, metrosCuadrados, precioBase, demanda, estado);
        this.tienePiscina = tienePiscina;
        this.garage = garage;
        this.tieneBidet = tieneBidet;
    }

    public boolean isTienePiscina() {
        return tienePiscina;
    }

    public void setTienePiscina(boolean tienePiscina) {
        this.tienePiscina = tienePiscina;
    }

    public boolean isGarage() {
        return garage;
    }

    public void setGarage(boolean garage) {
        this.garage = garage;
    }

    public boolean isTieneBidet() {
        return tieneBidet;
    }

    public void setTieneBidet(boolean tieneBidet) {
        this.tieneBidet = tieneBidet;
    }

    @Override
    public double calcularPrecioFinal() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion(); 
    }
    
   
}

