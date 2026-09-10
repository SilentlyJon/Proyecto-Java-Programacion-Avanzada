package com.mycompany.proyecto.ventana;

import com.mycompany.proyecto.modelo.Departamento;
import com.mycompany.proyecto.modelo.DepartamentoPremium;
import com.mycompany.proyecto.modelo.Inmobiliaria;
import com.mycompany.proyecto.modelo.Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Panel equivalente a MenuDepartamento. Primero se elige el proyecto
 * (combo), y sobre ese proyecto se listan/gestionan sus departamentos.
 */
public class PanelDepartamento extends JPanel {

    private final Inmobiliaria inmobiliaria;
    private final JComboBox<String> comboProyectos = new JComboBox<>();
    private final DefaultTableModel modeloTabla;
    private final JTable tabla;

    public PanelDepartamento(Inmobiliaria inmobiliaria) {
        this.inmobiliaria = inmobiliaria;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new JLabel("Proyecto:"));
        panelSuperior.add(comboProyectos);
        add(panelSuperior, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "Nro", "m2", "Precio base", "Demanda", "Estado", "Tipo", "Precio final"}, 0) {
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
        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);
        add(panelBotones, BorderLayout.SOUTH);

        comboProyectos.addActionListener(e -> cargarTabla());
        btnAgregar.addActionListener(e -> agregar());
        btnModificar.addActionListener(e -> modificar());
        btnEliminar.addActionListener(e -> eliminar());
        btnBuscar.addActionListener(e -> buscar());

        refrescarProyectos();
    }

    /** Se llama desde afuera (PanelProyecto) cuando cambia la lista de proyectos. */
    public void refrescarProyectos() {
        String seleccionPrevia = (String) comboProyectos.getSelectedItem();
        comboProyectos.removeAllItems();
        for (Proyecto p : inmobiliaria.getProyectos().values()) {
            comboProyectos.addItem(p.getCodigo());
        }
        if (seleccionPrevia != null) {
            comboProyectos.setSelectedItem(seleccionPrevia);
        }
        cargarTabla();
    }

    private Proyecto proyectoSeleccionado() {
        String codigo = (String) comboProyectos.getSelectedItem();
        if (codigo == null) return null;
        return inmobiliaria.buscarProyecto(codigo);
    }

    private void cargarTabla() {
        modeloTabla.setRowCount(0);
        Proyecto proyecto = proyectoSeleccionado();
        if (proyecto == null) return;

        for (Departamento d : proyecto.getDepartamentos()) {
            String tipo = (d instanceof DepartamentoPremium) ? "Premium" : "Básico";
            modeloTabla.addRow(new Object[]{
                    d.getId(), d.getNumero(), d.getMetrosCuadrados(), d.getPrecioBase(),
                    d.getDemanda(), d.getEstado(), tipo, d.calcularPrecioFinal()
            });
        }
    }

    private String idSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un departamento de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        return (String) modeloTabla.getValueAt(fila, 0);
    }

    private void agregar() {
        Proyecto proyecto = proyectoSeleccionado();
        if (proyecto == null) {
            JOptionPane.showMessageDialog(this, "Primero selecciona (o crea) un proyecto.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        DialogoDepartamento dialogo = new DialogoDepartamento((Frame) SwingUtilities.getWindowAncestor(this), null);
        dialogo.setVisible(true);

        if (dialogo.isConfirmado()) {
            if (proyecto.buscarDepartamento(dialogo.getId()) != null) {
                JOptionPane.showMessageDialog(this, "Ya existe un departamento con ese ID en este proyecto.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Departamento nuevo = dialogo.construirDepartamento();
            proyecto.agregarDepartamento(nuevo);
            cargarTabla();
        }
    }

    private void modificar() {
        Proyecto proyecto = proyectoSeleccionado();
        if (proyecto == null) return;
        String id = idSeleccionado();
        if (id == null) return;

        Departamento existente = proyecto.buscarDepartamento(id);
        DialogoDepartamento dialogo = new DialogoDepartamento((Frame) SwingUtilities.getWindowAncestor(this), existente);
        dialogo.setVisible(true);

        if (dialogo.isConfirmado()) {
            proyecto.modificarDepartamento(id, dialogo.getNumero(), dialogo.getMetrosCuadrados(),
                    dialogo.getPrecioBase(), dialogo.getDemandaSeleccionada(), dialogo.getEstadoSeleccionado());

            if (existente instanceof DepartamentoPremium) {
                DepartamentoPremium premium = (DepartamentoPremium) existente;
                premium.modificarDatosPremium(dialogo.isTienePiscina(), dialogo.isTieneGarage(), dialogo.isTieneBidet());
            }
            cargarTabla();
        }
    }

    private void eliminar() {
        Proyecto proyecto = proyectoSeleccionado();
        if (proyecto == null) return;
        String id = idSeleccionado();
        if (id == null) return;

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Eliminar el departamento " + id + "?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            proyecto.eliminarDepartamento(id);
            cargarTabla();
        }
    }

    private void buscar() {
        Proyecto proyecto = proyectoSeleccionado();
        if (proyecto == null) return;
        String id = JOptionPane.showInputDialog(this, "ID del departamento a buscar:");
        if (id == null || id.isBlank()) return;

        Departamento departamento = proyecto.buscarDepartamento(id);
        if (departamento != null) {
            JOptionPane.showMessageDialog(this, departamento.mostrarInformacion(), "Departamento encontrado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el departamento.", "Sin resultados", JOptionPane.WARNING_MESSAGE);
        }
    }
}