/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ControladorDocente;
import controlador.ControladorEstudiante;
import modelo.Docente;

import javax.swing.*; // Para construir componentes gráficos.
import javax.swing.table.DefaultTableModel; // Modelo de datos para la JTable.
import java.awt.*; // Para layouts y eventos.
import java.awt.event.*;
import java.util.List;
import javax.swing.table.TableColumn;

public class VistaDocenteGUI extends JFrame {
    
   

    private JTextField txtNombre, txtEdad;
    private JButton btnAgregar, btnActualizar, btnEliminar, btnLimpiar;
    private JTable tablaDocentes;
    private DefaultTableModel modeloTabla;

    private ControladorDocente controladorD;
    private int docenteSeleccionadoId = -1;

    public VistaDocenteGUI(ControladorDocente controladorD) {
        this.controladorD = controladorD;
        initComponents();
        cargarDocentes();
    }

       

    private void initComponents() {
        setTitle("Gestión de Docentes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel Superior - Formulario
        JPanel panelFormulario = new JPanel(new GridLayout(2, 4, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Docente"));

        txtNombre = new JTextField();
        txtEdad = new JTextField();

        btnAgregar = new JButton("Agregar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(btnAgregar);
        panelFormulario.add(btnActualizar);
        panelFormulario.add(btnEliminar);
        panelFormulario.add(btnLimpiar);

        add(panelFormulario, BorderLayout.NORTH);

        // Tabla
        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Nombre"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        tablaDocentes = new JTable(modeloTabla);
        
        JScrollPane scrollPane = new JScrollPane(tablaDocentes);
        add(scrollPane, BorderLayout.CENTER);

        TableColumn columnId = tablaDocentes.getColumnModel().getColumn(1);
        
        columnId.setPreferredWidth(200);
        
        // Listeners
        btnAgregar.addActionListener(e -> agregarDocente());
        btnActualizar.addActionListener(e -> actualizarDocente());
        btnEliminar.addActionListener(e -> eliminarDocente());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        // codigo para escuchar el evento de la tabla
        tablaDocentes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tablaDocentes.getSelectedRow();
                if (fila >= 0) {
                    System.out.println("ID: " + modeloTabla.getValueAt(fila, 0).toString());
                    System.out.println("Nombre: " + modeloTabla.getValueAt(fila, 1).toString());
                    System.out.println("Edad: " + modeloTabla.getValueAt(fila, 2).toString());
                    
                    docenteSeleccionadoId = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
                    txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
                    txtEdad.setText(modeloTabla.getValueAt(fila, 2).toString());
                }
            }
        });

        setSize(600, 400);
        setLocationRelativeTo(null); // Centrar ventana
        setVisible(true);
    }

    private void cargarDocentes() {
        modeloTabla.setRowCount(0); // limpiar tabla
        List<Docente> lista = controladorD.obtenerDocentes();
        for (Docente e : lista) {
            modeloTabla.addRow(new Object[]{e.getIdDocente(), e.getNomDocente()});
        }
    }

    private void agregarDocente() {
        if (validarCampos()) {
            Docente nuevo = new Docente();
            nuevo.setNomDocente(txtNombre.getText());
            controladorD.crearDocente(nuevo);
            cargarDocentes();
            limpiarCampos();
        }
    }

    private void actualizarDocente() {
        if (validarCampos() && docenteSeleccionadoId != -1) {
            Docente actualizado = new Docente();
            actualizado.setIdDocente(docenteSeleccionadoId);
            actualizado.setNomDocente(txtNombre.getText());
            controladorD.actualizarDocente(actualizado);
            cargarDocentes();
            limpiarCampos();
        }
    }

    private void eliminarDocente() {
        if (docenteSeleccionadoId != -1) {
            controladorD.removerDocente(docenteSeleccionadoId);
            cargarDocentes();
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtEdad.setText("");
        docenteSeleccionadoId = -1;
        tablaDocentes.clearSelection();
    }
    private boolean validarCampos() {
        if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return false;
        }
        try {
            Integer.parseInt(txtEdad.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ok");
            return false;
        }
        return true;
    }

    
}
