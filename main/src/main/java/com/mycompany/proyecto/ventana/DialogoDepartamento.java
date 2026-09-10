package com.mycompany.proyecto.ventana;

import com.mycompany.proyecto.modelo.Departamento;
import com.mycompany.proyecto.modelo.DepartamentoBasico;
import com.mycompany.proyecto.modelo.DepartamentoPremium;
import com.mycompany.proyecto.modelo.EstadoDepartamento;
import com.mycompany.proyecto.modelo.NivelDemanda;

import javax.swing.*;
import java.awt.*;

/**
 * Formulario para agregar o modificar un Departamento (Básico o Premium).
 */
public class DialogoDepartamento extends JDialog {

    private final JTextField campoId = new JTextField(10);
    private final JTextField campoNumero = new JTextField(10);
    private final JTextField campoMetros = new JTextField(10);
    private final JTextField campoPrecio = new JTextField(10);
    private final JComboBox<NivelDemanda> comboDemanda = new JComboBox<>(NivelDemanda.values());
    private final JComboBox<EstadoDepartamento> comboEstado = new JComboBox<>(EstadoDepartamento.values());

    private final JRadioButton radioBasico = new JRadioButton("Básico", true);
    private final JRadioButton radioPremium = new JRadioButton("Premium");
    private final JCheckBox checkPiscina = new JCheckBox("Piscina");
    private final JCheckBox checkGarage = new JCheckBox("Garage");
    private final JCheckBox checkBidet = new JCheckBox("Bidet");

    private boolean confirmado = false;

    public DialogoDepartamento(Frame propietario, Departamento departamentoExistente) {
        super(propietario, departamentoExistente == null ? "Agregar departamento" : "Modificar departamento", true);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int fila = 0;
        agregarFila(gbc, fila++, "ID:", campoId);
        agregarFila(gbc, fila++, "Número:", campoNumero);
        agregarFila(gbc, fila++, "Metros cuadrados:", campoMetros);
        agregarFila(gbc, fila++, "Precio base:", campoPrecio);
        agregarFila(gbc, fila++, "Demanda:", comboDemanda);
        agregarFila(gbc, fila++, "Estado:", comboEstado);

        ButtonGroup grupoTipo = new ButtonGroup();
        grupoTipo.add(radioBasico);
        grupoTipo.add(radioPremium);
        JPanel panelTipo = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelTipo.add(radioBasico);
        panelTipo.add(radioPremium);
        agregarFila(gbc, fila++, "Tipo:", panelTipo);

        JPanel panelPremium = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelPremium.add(checkPiscina);
        panelPremium.add(checkGarage);
        panelPremium.add(checkBidet);
        agregarFila(gbc, fila++, "Extras Premium:", panelPremium);

        radioBasico.addActionListener(e -> actualizarEstadoExtras());
        radioPremium.addActionListener(e -> actualizarEstadoExtras());
        actualizarEstadoExtras();

        if (departamentoExistente != null) {
            campoId.setText(departamentoExistente.getId());
            campoId.setEditable(false); // el ID no se cambia al modificar
            campoNumero.setText(String.valueOf(departamentoExistente.getNumero()));
            campoMetros.setText(String.valueOf(departamentoExistente.getMetrosCuadrados()));
            campoPrecio.setText(String.valueOf(departamentoExistente.getPrecioBase()));
            comboDemanda.setSelectedItem(departamentoExistente.getDemanda());
            comboEstado.setSelectedItem(departamentoExistente.getEstado());

            boolean esPremium = departamentoExistente instanceof DepartamentoPremium;
            radioPremium.setSelected(esPremium);
            radioBasico.setSelected(!esPremium);
            // El tipo (Básico/Premium) no se cambia al modificar, solo sus datos.
            radioBasico.setEnabled(false);
            radioPremium.setEnabled(false);

            if (esPremium) {
                DepartamentoPremium premium = (DepartamentoPremium) departamentoExistente;
                checkPiscina.setSelected(premium.isTienePiscina());
                checkGarage.setSelected(premium.isGarage());
                checkBidet.setSelected(premium.isTieneBidet());
            }
            actualizarEstadoExtras();
        }

        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");
        btnAceptar.addActionListener(e -> aceptar());
        btnCancelar.addActionListener(e -> dispose());

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0;
        gbc.gridy = fila;
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

    private void actualizarEstadoExtras() {
        boolean premium = radioPremium.isSelected();
        checkPiscina.setEnabled(premium);
        checkGarage.setEnabled(premium);
        checkBidet.setEnabled(premium);
    }

    private void aceptar() {
        try {
            if (campoId.getText().isBlank()) {
                throw new NumberFormatException("El ID es obligatorio.");
            }
            Integer.parseInt(campoNumero.getText().trim());
            Double.parseDouble(campoMetros.getText().trim());
            Double.parseDouble(campoPrecio.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Revisa los campos numéricos (Número, Metros, Precio).", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        confirmado = true;
        dispose();
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public String getId() {
        return campoId.getText().trim();
    }

    public int getNumero() {
        return Integer.parseInt(campoNumero.getText().trim());
    }

    public double getMetrosCuadrados() {
        return Double.parseDouble(campoMetros.getText().trim());
    }

    public double getPrecioBase() {
        return Double.parseDouble(campoPrecio.getText().trim());
    }

    public NivelDemanda getDemandaSeleccionada() {
        return (NivelDemanda) comboDemanda.getSelectedItem();
    }

    public EstadoDepartamento getEstadoSeleccionado() {
        return (EstadoDepartamento) comboEstado.getSelectedItem();
    }

    public boolean isTienePiscina() {
        return checkPiscina.isSelected();
    }

    public boolean isTieneGarage() {
        return checkGarage.isSelected();
    }

    public boolean isTieneBidet() {
        return checkBidet.isSelected();
    }

    /** Solo se usa al AGREGAR: construye el objeto según el tipo elegido (Básico/Premium). */
    public Departamento construirDepartamento() {
        if (radioPremium.isSelected()) {
            return new DepartamentoPremium(isTienePiscina(), isTieneGarage(), isTieneBidet(),
                    getId(), getNumero(), getMetrosCuadrados(), getPrecioBase(),
                    getDemandaSeleccionada(), getEstadoSeleccionado());
        }
        return new DepartamentoBasico(getId(), getNumero(), getMetrosCuadrados(), getPrecioBase(),
                getDemandaSeleccionada(), getEstadoSeleccionado());
    }
}