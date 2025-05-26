
package vista;

import controlador.ControladorCurso;
import controlador.ControladorEstudiante;
import modelo.Estudiante;

import javax.swing.*; // Para construir componentes gráficos.
import javax.swing.table.DefaultTableModel; // Modelo de datos para la JTable.
import java.awt.*; // Para layouts y eventos.
import java.awt.event.*;
import java.util.List;
import javax.swing.table.TableColumn;
import modelo.Curso;


public class VistaCursoGUI extends JFrame {
    
    private JTextField txtNomCurso, txtCodDocente;
    private JButton btnAgregarCurso, btnActualizarCurso, btnEliminarCurso, btnLimpiarCurso;
    private JTable tablaCurso;
    private DefaultTableModel modeloTablaCurso;

    private ControladorCurso controladorCurso;
    private int cursoSeleccionadoId = -1;

    public VistaCursoGUI(ControladorCurso controladorCurso) {
        this.controladorCurso = controladorCurso;
        initComponentsCurso();
        cargarEstudiantes();
    }
     private void initComponentsCurso() {
        setTitle("Gestión de Curso");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        
        JPanel panelFormulario = new JPanel(new GridLayout(2, 4, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Estudiante"));

        txtNomCurso = new JTextField();
        txtCodDocente = new JTextField();

        btnAgregarCurso = new JButton("Agregar");
        btnActualizarCurso = new JButton("Actualizar");
        btnEliminarCurso = new JButton("Eliminar");
        btnLimpiarCurso = new JButton("Limpiar");
        
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNomCurso);
        panelFormulario.add(new JLabel("Nombre docente:"));
        panelFormulario.add(txtCodDocente);
        panelFormulario.add(btnAgregarCurso);
        panelFormulario.add(btnActualizarCurso);
        panelFormulario.add(btnEliminarCurso);
        panelFormulario.add(btnLimpiarCurso);

        add(panelFormulario, BorderLayout.NORTH);
        
        
        modeloTablaCurso = new DefaultTableModel(new Object[]{"ID", "Nombre", "codDocente"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        
        tablaCurso = new JTable(modeloTablaCurso);
        
        JScrollPane scrollPane = new JScrollPane(tablaCurso);
        add(scrollPane, BorderLayout.CENTER);

        TableColumn columnId = tablaCurso.getColumnModel().getColumn(1);
        
        columnId.setPreferredWidth(200);
        
        btnAgregarCurso.addActionListener(e -> agregarCurso());
        btnActualizarCurso.addActionListener(e -> actualizarCurso());
        btnEliminarCurso.addActionListener(e -> eliminarCurso());
        btnLimpiarCurso.addActionListener(e -> limpiarCampos());
        
        tablaCurso.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tablaCurso.getSelectedRow();
                if (fila >= 0) {
                    System.out.println("ID: " + modeloTablaCurso.getValueAt(fila, 0).toString());
                    System.out.println("Nombre: " + modeloTablaCurso.getValueAt(fila, 1).toString());
                    System.out.println("Edad: " + modeloTablaCurso.getValueAt(fila, 2).toString());
                    
                    cursoSeleccionadoId = Integer.parseInt(modeloTablaCurso.getValueAt(fila, 0).toString());
                    txtNomCurso.setText(modeloTablaCurso.getValueAt(fila, 1).toString());
                    txtCodDocente.setText(modeloTablaCurso.getValueAt(fila, 2).toString());
                }
            }
        });
        
        setSize(600, 400);
        setLocationRelativeTo(null); // Centrar ventana
        setVisible(true);
    }
     
    private void cargarEstudiantes() {
        modeloTablaCurso.setRowCount(0); // limpiar tabla
        List<Curso> lista = controladorCurso.obtenerCursos();
        for( Curso e : lista) {
            modeloTablaCurso.addRow(new Object[]{e.getIdCurso(), e.getNomCurso(), e.getCodDocente()});
        }
    } 
    
     private void agregarCurso() {
        if (validarCampos()) {
            Curso nuevoCurso = new Curso();
            nuevoCurso.setNomCurso(txtNomCurso.getText());
            nuevoCurso.setCodDocente(Integer.parseInt(txtCodDocente.getText()));
            controladorCurso.crearCurso(nuevoCurso);
            cargarEstudiantes();
            limpiarCampos();
        }
    }
     
    private void actualizarCurso() {
        if (validarCampos() && cursoSeleccionadoId != -1) {
            Curso actualizadoCurso = new Curso();
            actualizadoCurso.setIdCurso(cursoSeleccionadoId);
            actualizadoCurso.setNomCurso(txtNomCurso.getText());
            actualizadoCurso.setCodDocente(Integer.parseInt(txtCodDocente.getText()));
            controladorCurso.actualizarCurso(actualizadoCurso);
            cargarEstudiantes();
            limpiarCampos();
        }
    }
    
    private void eliminarCurso() {
        if (cursoSeleccionadoId != -1) {
            controladorCurso.removerCurso(cursoSeleccionadoId);
            cargarEstudiantes();
            limpiarCampos();
        }
    }
    private void limpiarCampos() {
        txtNomCurso.setText("");
        txtCodDocente.setText("");
        cursoSeleccionadoId = -1;
        tablaCurso.clearSelection();
    }
    
    private boolean validarCampos() {
        if (txtNomCurso.getText().isEmpty() || txtCodDocente.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return false;
        }
        try {
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "debe ser válido");
            return false;
        }
        return true;
    }
}






































