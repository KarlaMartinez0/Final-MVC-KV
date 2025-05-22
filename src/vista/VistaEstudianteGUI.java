package vista;

import controlador.ControladorEstudiante;
import modelo.Estudiante;

import javax.swing.*; // Para construir componentes gráficos.
import javax.swing.table.DefaultTableModel; // Modelo de datos para la JTable.
import java.awt.*; // Para layouts y eventos.
import java.awt.event.*;
import java.util.List;
import javax.swing.table.TableColumn;

/**
 *
 * @author Ing. gperezm.
 */
public class VistaEstudianteGUI extends JFrame {

    private JTextField txtNombre, txtEdad;
    private JButton btnAgregar, btnActualizar, btnEliminar, btnLimpiar;
    private JTable tablaEstudiantes;
    private DefaultTableModel modeloTabla;

    private ControladorEstudiante controlador;
    private int estudianteSeleccionadoId = -1;

    public VistaEstudianteGUI(ControladorEstudiante controlador) {
        this.controlador = controlador;
        initComponents();
        cargarEstudiantes();
    }

    private void initComponents() {
        setTitle("Gestión de Estudiantes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel Superior - Formulario
        JPanel panelFormulario = new JPanel(new GridLayout(2, 4, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Estudiante"));

        txtNombre = new JTextField();
        txtEdad = new JTextField();

        btnAgregar = new JButton("Agregar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");

        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Edad:"));
        panelFormulario.add(txtEdad);
        panelFormulario.add(btnAgregar);
        panelFormulario.add(btnActualizar);
        panelFormulario.add(btnEliminar);
        panelFormulario.add(btnLimpiar);

        add(panelFormulario, BorderLayout.NORTH);

        // Tabla
        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Nombre", "Edad"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        tablaEstudiantes = new JTable(modeloTabla);
        
        JScrollPane scrollPane = new JScrollPane(tablaEstudiantes);
        add(scrollPane, BorderLayout.CENTER);

        TableColumn columnId = tablaEstudiantes.getColumnModel().getColumn(1);
        
        columnId.setPreferredWidth(200);
        
        // Listeners
        btnAgregar.addActionListener(e -> agregarEstudiante());
        btnActualizar.addActionListener(e -> actualizarEstudiante());
        btnEliminar.addActionListener(e -> eliminarEstudiante());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        // codigo para escuchar el evento de la tabla
        tablaEstudiantes.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tablaEstudiantes.getSelectedRow();
                if (fila >= 0) {
                    System.out.println("ID: " + modeloTabla.getValueAt(fila, 0).toString());
                    System.out.println("Nombre: " + modeloTabla.getValueAt(fila, 1).toString());
                    System.out.println("Edad: " + modeloTabla.getValueAt(fila, 2).toString());
                    
                    estudianteSeleccionadoId = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
                    txtNombre.setText(modeloTabla.getValueAt(fila, 1).toString());
                    txtEdad.setText(modeloTabla.getValueAt(fila, 2).toString());
                }
            }
        });

        setSize(600, 400);
        setLocationRelativeTo(null); // Centrar ventana
        setVisible(true);
    }

    private void cargarEstudiantes() {
        modeloTabla.setRowCount(0); // limpiar tabla
        List<Estudiante> lista = controlador.obtenerEstudiantes();
        for (Estudiante e : lista) {
            modeloTabla.addRow(new Object[]{e.getId(), e.getNombre(), e.getEdad()});
        }
    }

    private void agregarEstudiante() {
        if (validarCampos()) {
            Estudiante nuevo = new Estudiante();
            nuevo.setNombre(txtNombre.getText());
            nuevo.setEdad(Integer.parseInt(txtEdad.getText()));
            controlador.crearEstudiante(nuevo);
            cargarEstudiantes();
            limpiarCampos();
        }
    }

    private void actualizarEstudiante() {
        if (validarCampos() && estudianteSeleccionadoId != -1) {
            Estudiante actualizado = new Estudiante();
            actualizado.setId(estudianteSeleccionadoId);
            actualizado.setNombre(txtNombre.getText());
            actualizado.setEdad(Integer.parseInt(txtEdad.getText()));
            controlador.actualizarEstudiante(actualizado);
            cargarEstudiantes();
            limpiarCampos();
        }
    }

    private void eliminarEstudiante() {
        if (estudianteSeleccionadoId != -1) {
            controlador.removerEstudiante(estudianteSeleccionadoId);
            cargarEstudiantes();
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtEdad.setText("");
        estudianteSeleccionadoId = -1;
        tablaEstudiantes.clearSelection();
    }

    private boolean validarCampos() {
        if (txtNombre.getText().isEmpty() || txtEdad.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return false;
        }
        try {
            Integer.parseInt(txtEdad.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Edad debe ser un número válido");
            return false;
        }
        return true;
    }
}
