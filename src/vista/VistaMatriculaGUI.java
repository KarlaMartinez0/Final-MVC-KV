
package vista;
import controlador.ControladorMatricula;
import modelo.Matricula;

import javax.swing.*; // Para construir componentes gráficos.
import javax.swing.table.DefaultTableModel; // Modelo de datos para la JTable.
import java.awt.*; // Para layouts y eventos.
import java.awt.event.*;
import java.util.List;
import javax.swing.table.TableColumn;


public class VistaMatriculaGUI extends JFrame {
    private JTextField txtNota, txtIdCurso, txtIdEstudiante;
    private JButton btnAgregarMatricula, btnActualizarMatricula, btnEliminarMatricula, btnLimpiarMatricula;
    private JTable tablaMatriculas;
    private DefaultTableModel modeloTablaMatricula;

    private ControladorMatricula controladorMatricula;
    private int matriculaSeleccionadoId = -1;

    public VistaMatriculaGUI(ControladorMatricula controladorMatricula) {
        this.controladorMatricula = controladorMatricula;
        initComponents();
        cargarMatricula();
    }

    private void initComponents() {
        setTitle("Gestión de Matricula");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel Superior - Formulario
        JPanel panelFormulario = new JPanel(new GridLayout(2, 4, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Matricula"));

        txtNota = new JTextField();
        txtIdCurso = new JTextField();
        txtIdEstudiante = new JTextField();
        
        
        btnAgregarMatricula = new JButton("Agregar");
        btnActualizarMatricula = new JButton("Actualizar");
        btnEliminarMatricula = new JButton("Eliminar");
        btnLimpiarMatricula = new JButton("Limpiar");

        panelFormulario.add(new JLabel("Nota"));
        panelFormulario.add(txtNota);
        panelFormulario.add(new JLabel("Curso:"));
        panelFormulario.add(txtIdCurso);
        panelFormulario.add(new JLabel("Estudiante:"));
        panelFormulario.add(txtIdEstudiante);
        panelFormulario.add(btnAgregarMatricula);
        panelFormulario.add(btnActualizarMatricula);
        panelFormulario.add(btnEliminarMatricula);
        panelFormulario.add(btnLimpiarMatricula);

        add(panelFormulario, BorderLayout.NORTH);

        // Tabla
        modeloTablaMatricula = new DefaultTableModel(new Object[]{"ID", "Nota", "Id Curso","Id Estudiante"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        tablaMatriculas = new JTable(modeloTablaMatricula);
        
        JScrollPane scrollPane = new JScrollPane(tablaMatriculas);
        add(scrollPane, BorderLayout.CENTER);

        TableColumn columnId = tablaMatriculas.getColumnModel().getColumn(1);
        
        columnId.setPreferredWidth(200);
        
        // Listeners
        btnAgregarMatricula.addActionListener(e -> agregarMatricula());
        btnActualizarMatricula.addActionListener(e -> actualizarMatricula());
        btnEliminarMatricula.addActionListener(e -> eliminarEstudiante());
        btnLimpiarMatricula.addActionListener(e -> limpiarCampos());

        // codigo para escuchar el evento de la tabla
        tablaMatriculas.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tablaMatriculas.getSelectedRow();
                if (fila >= 0) {
                    System.out.println("ID: " + modeloTablaMatricula.getValueAt(fila, 0).toString());
                    System.out.println("Nombre: " + modeloTablaMatricula.getValueAt(fila, 1).toString());
                    System.out.println("Edad: " + modeloTablaMatricula.getValueAt(fila, 2).toString());
                    
                    matriculaSeleccionadoId = Integer.parseInt(modeloTablaMatricula.getValueAt(fila, 0).toString());
                    txtNota.setText(modeloTablaMatricula.getValueAt(fila, 1).toString());
                    txtIdCurso.setText(modeloTablaMatricula.getValueAt(fila, 2).toString());
                    txtIdEstudiante.setText(modeloTablaMatricula.getValueAt(fila, 3).toString());
                }
            }
        });

        setSize(600, 400);
        setLocationRelativeTo(null); // Centrar ventana
        setVisible(true);
    }

    private void cargarMatricula() {
        modeloTablaMatricula.setRowCount(0); // limpiar tabla
        List<Matricula> lista = controladorMatricula.obtenerMatricula();
        for (Matricula e : lista) {
            modeloTablaMatricula.addRow(new Object[]{e.getIdMatricula(), e.getNota(), e.getIdCurso(), e.getIdEstudiante()});
        }
    }

    private void agregarMatricula() {
        if (validarCampos()) {
            Matricula nuevaMatricula = new Matricula();
            nuevaMatricula.setNota(Integer.parseInt(txtNota.getText()));
            nuevaMatricula.setIdCurso(Integer.parseInt(txtIdCurso.getText()));
            nuevaMatricula.setIdEstudiante(Integer.parseInt(txtIdEstudiante.getText()));
            controladorMatricula.crearMatricula(nuevaMatricula);
            cargarMatricula();
            limpiarCampos();
        }
    }

    private void actualizarMatricula() {
        if (validarCampos() && matriculaSeleccionadoId != -1) {
            Matricula actualizadoMatricula = new Matricula();
            actualizadoMatricula.setIdMatricula(matriculaSeleccionadoId);
            actualizadoMatricula.setNota(Integer.parseInt(txtNota.getText()));
            actualizadoMatricula.setIdCurso(Integer.parseInt(txtIdCurso.getText()));
            actualizadoMatricula.setIdEstudiante(Integer.parseInt(txtIdEstudiante.getText()));
            controladorMatricula.actualizarMatricula(actualizadoMatricula);
            cargarMatricula();
            limpiarCampos();
        }
    }

    private void eliminarEstudiante() {
        if (matriculaSeleccionadoId != -1) {
            controladorMatricula.removerMatricula(matriculaSeleccionadoId);
            cargarMatricula();
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        txtNota.setText("");
        txtIdCurso.setText("");
        txtIdEstudiante.setText("");
        matriculaSeleccionadoId = -1;
        tablaMatriculas.clearSelection();
    }

    private boolean validarCampos() {
        if (txtNota.getText().isEmpty() || txtIdCurso.getText().isEmpty() || txtIdEstudiante.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return false;
        }
        try {
            Integer.parseInt(txtNota.getText());
            Integer.parseInt(txtIdCurso.getText());
            Integer.parseInt(txtIdEstudiante.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Edad debe ser un número válido");
            return false;
        }
        return true;
    }
}
