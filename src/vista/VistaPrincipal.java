package vista;

import controlador.*;
import modelo.Docente;

import javax.swing.*; // Para construir componentes gráficos.
import javax.swing.table.DefaultTableModel; // Modelo de datos para la JTable.
import java.awt.*; // Para layouts y eventos.
import java.awt.event.*;
import java.util.List;
import javax.swing.table.TableColumn;
import modelo.Curso;
import modelo.Estudiante;
import modelo.Matricula;

public class VistaPrincipal extends JFrame {

    public VistaPrincipal() {
        setTitle("Sistema Académico");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel(new GridLayout(2, 2, 10, 10));
        panelBotones.setBorder(BorderFactory.createTitledBorder("Opciones del sistema"));

        JButton btnDocentes = new JButton("Docente");
        JButton btnEstudiantes = new JButton("Estudiante");
        JButton btnCursos = new JButton("Curso");
        JButton btnMatriculas = new JButton(" Matrícula");

        btnDocentes.addActionListener(e -> {
            Docente modelo = new Docente();
            VistaDocente vista = new VistaDocente();
            ControladorDocente controlador = new ControladorDocente(modelo, vista);
            new VistaDocenteGUI(controlador);
        });
        
        btnEstudiantes.addActionListener(e -> {
            Estudiante modelo = new Estudiante();
            VistaEstudiante vista = new VistaEstudiante();
            ControladorEstudiante controlador = new ControladorEstudiante(modelo, vista);
            new VistaEstudianteGUI(controlador);
        });
        
        btnCursos.addActionListener(e -> {
            Curso modelo = new Curso();
            VistaCurso vista = new VistaCurso();
            ControladorCurso controlador = new ControladorCurso(modelo, vista);
            new VistaCursoGUI(controlador);
        });
        
        btnMatriculas.addActionListener(e -> {
            Matricula modelo = new Matricula();
            VistaMatricula vista = new VistaMatricula();
            ControladorMatricula controlador = new ControladorMatricula(modelo, vista);
            new VistaMatriculaGUI(controlador);
        });

        panelBotones.add(btnDocentes);
        panelBotones.add(btnEstudiantes);
        panelBotones.add(btnCursos);
        panelBotones.add(btnMatriculas);
        
        add(panelBotones, BorderLayout.CENTER);

    }
}

