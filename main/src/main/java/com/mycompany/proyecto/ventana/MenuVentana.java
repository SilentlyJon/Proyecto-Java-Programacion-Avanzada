package com.mycompany.proyecto.ventana;

import com.mycompany.proyecto.modelo.Inmobiliaria;
import javax.swing.*;

/**
 * Ventana principal de gestión de la Inmobiliaria.
 * Reemplaza a MenuConsola pero usando una interfaz gráfica con pestañas.
 */
public class MenuVentana extends JFrame {

    public MenuVentana(Inmobiliaria inmobiliaria) {
        super("Gestión de " + inmobiliaria.getNombre());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(950, 600);
        setLocationRelativeTo(null);

        JTabbedPane pestanas = new JTabbedPane();

        PanelProyecto panelProyecto = new PanelProyecto(inmobiliaria);
        PanelDepartamento panelDepartamento = new PanelDepartamento(inmobiliaria);

        // Cuando se agrega/elimina un proyecto, el combo de la pestaña
        // de Departamentos se refresca automáticamente.
        panelProyecto.setAlActualizar(panelDepartamento::refrescarProyectos);

        pestanas.addTab("Proyectos", panelProyecto);
        pestanas.addTab("Departamentos", panelDepartamento);

        add(pestanas);
    }
}