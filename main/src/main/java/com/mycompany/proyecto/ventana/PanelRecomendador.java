package com.mycompany.proyecto.ventana;

import com.mycompany.proyecto.modelo.Departamento;
import com.mycompany.proyecto.modelo.DepartamentoPremium;
import com.mycompany.proyecto.modelo.Inmobiliaria;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Panel equivalente a la "Funcionalidad especial" del menu de consola (SIA-9):
 * recomienda departamentos DISPONIBLES cuyo precio final no supera el
 * presupuesto ingresado, agrupados por proyecto.
 */
public class PanelRecomendador extends JPanel {

    private final Inmobiliaria inmobiliaria;
    private final JTextField campoPresupuesto = new JTextField(15);
    private final DefaultTableModel modeloTabla;
    private final NumberFormat formatoPrecio = NumberFormat.getNumberInstance(new Locale("es", "CL"));

    public PanelRecomendador(Inmobiliaria inmobiliaria) {
        this.inmobiliaria = inmobiliaria;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSuperior.add(new JLabel("Presupuesto maximo del cliente:"));
        panelSuperior.add(campoPresupuesto);
        JButton btnBuscar = new JButton("Buscar opciones");
        panelSuperior.add(btnBuscar);
        add(panelSuperior, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(
                new Object[]{"Proyecto", "ID", "Nro", "m2", "Tipo", "Precio final"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tabla = new JTable(modeloTabla);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnBuscar.addActionListener(e -> buscar());
    }

    private void buscar() {
        double presupuesto;
        try {
            presupuesto = Double.parseDouble(campoPresupuesto.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingresa un presupuesto numerico valido.", "Dato inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (presupuesto <= 0) {
            JOptionPane.showMessageDialog(this, "El presupuesto debe ser mayor a cero.", "Dato inválido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Map<String, List<Departamento>> resultado = inmobiliaria.buscarDisponiblesPorPresupuesto(presupuesto);

        modeloTabla.setRowCount(0);
        for (Map.Entry<String, List<Departamento>> entrada : resultado.entrySet()) {
            String codigoProyecto = entrada.getKey();
            for (Departamento d : entrada.getValue()) {
                String tipo = (d instanceof DepartamentoPremium) ? "Premium" : "Básico";
                modeloTabla.addRow(new Object[]{
                        codigoProyecto, d.getId(), d.getNumero(), d.getMetrosCuadrados(), tipo,
                        "$" + formatoPrecio.format(Math.round(d.calcularPrecioFinal()))
                });
            }
        }

        if (modeloTabla.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No hay departamentos disponibles dentro de ese presupuesto.", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}