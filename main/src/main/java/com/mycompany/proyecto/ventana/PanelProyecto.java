package com.mycompany.proyecto.ventana;

import com.mycompany.proyecto.modelo.Inmobiliaria;
import com.mycompany.proyecto.modelo.Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Panel equivalente a MenuProyecto, pero con tabla y botones en vez de
 * un menú de consola.
 */
public class PanelProyecto extends JPanel {

    private final Inmobiliaria inmobiliaria;
    private final DefaultTableModel modeloTabla;
    private final JTable tabla;
    private Runnable alActualizar; // avisa a otros paneles cuando cambia la lista de proyectos

    public PanelProyecto(Inmobiliaria inmobiliaria) {
        this.inmobiliaria = inmobiliaria;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        modeloTabla = new DefaultTableModel(new Object[]{"Código", "Nombre", "Ubicación", "Demanda"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla = new JTable(modeloTabla);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAgregar = new JButton("Agregar");
        JButton btnModificar = new JButton("Modificar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnRefrescar = new JButton("Refrescar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnRefrescar);
        add(panelBotones, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> agregar());
        btnModificar.addActionListener(e -> modificar());
        btnEliminar.addActionListener(e -> eliminar());
        btnBuscar.addActionListener(e -> buscar());
        btnRefrescar.addActionListener(e -> cargarTabla());

        cargarTabla();
    }

    public void setAlActualizar(Runnable callback) {
        this.alActualizar = callback;
    }

    private void notificarCambio() {
        if (alActualizar != null) alActualizar.run();
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        for (Proyecto p : inmobiliaria.getProyectos().values()) {
            modeloTabla.addRow(new Object[]{p.getCodigo(), p.getNombre(), p.getUbicacion(), p.getDemanda()});
        }
    }

    private String codigoSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un proyecto de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        return (String) modeloTabla.getValueAt(fila, 0);
    }

    private void agregar() {
        DialogoProyecto dialogo = new DialogoProyecto((Frame) SwingUtilities.getWindowAncestor(this), null);
        dialogo.setVisible(true);

        if (dialogo.isConfirmado()) {
            if (inmobiliaria.buscarProyecto(dialogo.getCodigo()) != null) {
                JOptionPane.showMessageDialog(this, "Ya existe un proyecto con ese código.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Proyecto proyecto = new Proyecto(dialogo.getCodigo(), dialogo.getNombreProyecto(),
                    dialogo.getUbicacion(), dialogo.getDemandaSeleccionada());
            inmobiliaria.agregarProyecto(proyecto);
            cargarTabla();
            notificarCambio();
        }
    }

    private void modificar() {
        String codigo = codigoSeleccionado();
        if (codigo == null) return;

        Proyecto proyecto = inmobiliaria.buscarProyecto(codigo);
        DialogoProyecto dialogo = new DialogoProyecto((Frame) SwingUtilities.getWindowAncestor(this), proyecto);
        dialogo.setVisible(true);

        if (dialogo.isConfirmado()) {
            inmobiliaria.modificarProyecto(codigo, dialogo.getNombreProyecto(),
                    dialogo.getUbicacion(), dialogo.getDemandaSeleccionada());
            cargarTabla();
            notificarCambio();
        }
    }

    private void eliminar() {
        String codigo = codigoSeleccionado();
        if (codigo == null) return;

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Eliminar el proyecto " + codigo + "?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            inmobiliaria.eliminarProyecto(codigo);
            cargarTabla();
            notificarCambio();
        }
    }

    private void buscar() {
        String codigo = JOptionPane.showInputDialog(this, "Código del proyecto a buscar:");
        if (codigo == null || codigo.isBlank()) return;

        Proyecto proyecto = inmobiliaria.buscarProyecto(codigo);
        if (proyecto != null) {
            JOptionPane.showMessageDialog(this, proyecto.toString(), "Proyecto encontrado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el proyecto.", "Sin resultados", JOptionPane.WARNING_MESSAGE);
        }
    }
}