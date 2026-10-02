package com.mycompany.proyecto.ventana;

import com.mycompany.main.Persistencia.EscritorDeDatos;
import com.mycompany.proyecto.modelo.Inmobiliaria;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

/**
 * Ventana principal de gestión de la Inmobiliaria.
 * Reemplaza a MenuConsola pero usando una interfaz gráfica con pestañas.
 */
public class MenuVentana extends JFrame {

    public MenuVentana(Inmobiliaria inmobiliaria) {
        super("Gestión de " + inmobiliaria.getNombre());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(950, 600);
        setLocationRelativeTo(null);

        JTabbedPane pestanas = new JTabbedPane();

        PanelProyecto panelProyecto = new PanelProyecto(inmobiliaria);
        PanelDepartamento panelDepartamento = new PanelDepartamento(inmobiliaria);
        PanelRecomendador panelRecomendador = new PanelRecomendador(inmobiliaria);

        // Cuando se agrega/elimina un proyecto, el combo de la pestaña
        // de Departamentos se refresca automáticamente.
        panelProyecto.setAlActualizar(panelDepartamento::refrescarProyectos);

        pestanas.addTab("Proyectos", panelProyecto);
        pestanas.addTab("Departamentos", panelDepartamento);
        pestanas.addTab("Recomendador", panelRecomendador);

        add(pestanas);
        
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                System.out.println("Guardando datos.....");
                EscritorDeDatos.guardarProyectos("proyectos.txt", inmobiliaria);
                EscritorDeDatos.guardarDepartamento("departamentos.txt", inmobiliaria);
                dispose();
                System.exit(0);
            }
        });
        
        
    }
}