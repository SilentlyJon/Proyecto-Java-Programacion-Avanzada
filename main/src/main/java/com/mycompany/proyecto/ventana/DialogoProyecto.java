package com.mycompany.proyecto.ventana;

import com.mycompany.proyecto.modelo.NivelDemanda;
import com.mycompany.proyecto.modelo.Proyecto;

import javax.swing.*;
import java.awt.*;

/**
 * Formulario para agregar o modificar un Proyecto.
 * Si se le pasa un Proyecto existente, precarga los datos (modo edición).
 */
public class DialogoProyecto extends JDialog {

    private final JTextField campoCodigo = new JTextField(15);
    private final JTextField campoNombre = new JTextField(15);
    private final JTextField campoUbicacion = new JTextField(15);
    private final JComboBox<NivelDemanda> comboDemanda = new JComboBox<>(NivelDemanda.values());

    private boolean confirmado = false;

    public DialogoProyecto(Frame propietario, Proyecto proyectoExistente) {
        super(propietario, proyectoExistente == null ? "Agregar proyecto" : "Modificar proyecto", true);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        agregarFila(gbc, 0, "Código:", campoCodigo);
        agregarFila(gbc, 1, "Nombre:", campoNombre);
        agregarFila(gbc, 2, "Ubicación:", campoUbicacion);
        agregarFila(gbc, 3, "Demanda:", comboDemanda);

        if (proyectoExistente != null) {
            campoCodigo.setText(proyectoExistente.getCodigo());
            campoCodigo.setEditable(false); // el código no se cambia al modificar
            campoNombre.setText(proyectoExistente.getNombre());
            campoUbicacion.setText(proyectoExistente.getUbicacion());
            comboDemanda.setSelectedItem(proyectoExistente.getDemanda());
        }

        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");
        btnAceptar.addActionListener(e -> aceptar());
        btnCancelar.addActionListener(e -> dispose());

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(panelBotones, gbc);

        pack();
        setLocationRelativeTo(propietario);
    }

    private void agregarFila(GridBagConstraints gbc, int fila, String etiqueta, Component campo) {
        gbc.gridx = 0;
        gbc.gridy = fila;
        gbc.gridwidth = 1;
        add(new JLabel(etiqueta), gbc);
        gbc.gridx = 1;
        add(campo, gbc);
    }

    private void aceptar() {
        if (campoCodigo.getText().isBlank() || campoNombre.getText().isBlank() || campoUbicacion.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        confirmado = true;
        dispose();
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public String getCodigo() {
        return campoCodigo.getText().trim();
    }

    public String getNombreProyecto() {
        return campoNombre.getText().trim();
    }

    public String getUbicacion() {
        return campoUbicacion.getText().trim();
    }

    public NivelDemanda getDemandaSeleccionada() {
        return (NivelDemanda) comboDemanda.getSelectedItem();
    }
}