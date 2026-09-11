package com.mycompany.proyecto.ventana;

import com.mycompany.proyecto.modelo.Departamento;
import com.mycompany.proyecto.modelo.DepartamentoPremium;
import com.mycompany.proyecto.modelo.EstadoDepartamento;
import com.mycompany.proyecto.modelo.Inmobiliaria;
import com.mycompany.proyecto.modelo.NivelDemanda;
import com.mycompany.proyecto.modelo.Proyecto;
import com.mycompany.proyecto.excepciones.DatosInvalidosException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Panel equivalente a MenuDepartamento. Primero se elige el proyecto
 * (combo), y sobre ese proyecto se listan/gestionan sus departamentos.
 * Incluye filtros por estado y por demanda (equivalente a la sobrecarga
 * de mostrarDepartamento() del menu de consola) y cambio rapido de estado
 * (equivalente a la sobrecarga de modificarDatos() del menu de consola).
 */
public class PanelDepartamento extends JPanel {

    private final Inmobiliaria inmobiliaria;
    private final JComboBox<String> comboProyectos = new JComboBox<>();
    private final JComboBox<String> comboFiltroEstado = new JComboBox<>();
    private final JComboBox<String> comboFiltroDemanda = new JComboBox<>();
    private final DefaultTableModel modeloTabla;
    private final JTable tabla;
    private final NumberFormat formatoPrecio = NumberFormat.getNumberInstance(new Locale("es", "CL"));

    private static final String TODOS = "Todos";

    public PanelDepartamento(Inmobiliaria inmobiliaria) {
        this.inmobiliaria = inmobiliaria;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new JLabel("Proyecto:"));
        panelSuperior.add(comboProyectos);

        comboFiltroEstado.addItem(TODOS);
        for (EstadoDepartamento estado : EstadoDepartamento.values()) {
            comboFiltroEstado.addItem(estado.name());
        }
        panelSuperior.add(new JLabel("Estado:"));
        panelSuperior.add(comboFiltroEstado);

        comboFiltroDemanda.addItem(TODOS);
        for (NivelDemanda demanda : NivelDemanda.values()) {
            comboFiltroDemanda.addItem(demanda.name());
        }
        panelSuperior.add(new JLabel("Demanda:"));
        panelSuperior.add(comboFiltroDemanda);

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
        JButton btnCambiarEstado = new JButton("Cambiar estado");
        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnCambiarEstado);
        add(panelBotones, BorderLayout.SOUTH);

        comboProyectos.addActionListener(e -> cargarTabla());
        comboFiltroEstado.addActionListener(e -> cargarTabla());
        comboFiltroDemanda.addActionListener(e -> cargarTabla());
        btnAgregar.addActionListener(e -> agregar());
        btnModificar.addActionListener(e -> modificar());
        btnEliminar.addActionListener(e -> eliminar());
        btnBuscar.addActionListener(e -> buscar());
        btnCambiarEstado.addActionListener(e -> cambiarEstado());

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

        String filtroEstado = (String) comboFiltroEstado.getSelectedItem();
        String filtroDemanda = (String) comboFiltroDemanda.getSelectedItem();

        for (Departamento d : proyecto.getDepartamentos()) {
            if (filtroEstado != null && !filtroEstado.equals(TODOS) && d.getEstado() != EstadoDepartamento.valueOf(filtroEstado)) {
                continue;
            }
            if (filtroDemanda != null && !filtroDemanda.equals(TODOS) && d.getDemanda() != NivelDemanda.valueOf(filtroDemanda)) {
                continue;
            }

            String tipo = (d instanceof DepartamentoPremium) ? "Premium" : "Básico";
            modeloTabla.addRow(new Object[]{
                    d.getId(), d.getNumero(), d.getMetrosCuadrados(),
                    "$" + formatoPrecio.format(Math.round(d.getPrecioBase())),
                    d.getDemanda(), d.getEstado(), tipo,
                    "$" + formatoPrecio.format(Math.round(d.calcularPrecioFinal()))
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
            try {
                Departamento nuevo = dialogo.construirDepartamento();
                proyecto.agregarDepartamento(nuevo);
                cargarTabla();
            } catch (DatosInvalidosException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos inválidos", JOptionPane.ERROR_MESSAGE);
            }
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
            try {
                proyecto.modificarDepartamento(id, dialogo.getNumero(), dialogo.getMetrosCuadrados(),
                        dialogo.getPrecioBase(), dialogo.getDemandaSeleccionada(), dialogo.getEstadoSeleccionado());

                if (existente instanceof DepartamentoPremium) {
                    DepartamentoPremium premium = (DepartamentoPremium) existente;
                    premium.modificarDatosPremium(dialogo.isTienePiscina(), dialogo.isTieneGarage(), dialogo.isTieneBidet());
                }
                cargarTabla();
            } catch (DatosInvalidosException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos inválidos", JOptionPane.ERROR_MESSAGE);
            }
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

    /**
     * Cambio rapido de estado: usa la sobrecarga Departamento.modificarDatos(EstadoDepartamento),
     * sin pedir el resto de los datos (numero, metros, precio, demanda).
     */
    private void cambiarEstado() {
        Proyecto proyecto = proyectoSeleccionado();
        if (proyecto == null) return;
        String id = idSeleccionado();
        if (id == null) return;

        Departamento departamento = proyecto.buscarDepartamento(id);
        if (departamento == null) return;

        EstadoDepartamento[] opciones = EstadoDepartamento.values();
        EstadoDepartamento seleccion = (EstadoDepartamento) JOptionPane.showInputDialog(
                this,
                "Nuevo estado para " + id + ":",
                "Cambiar estado",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                departamento.getEstado());

        if (seleccion != null) {
            departamento.modificarDatos(seleccion);
            cargarTabla();
        }
    }
}