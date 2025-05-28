/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practicamvc411;

import controlador.ControladorDocente;
import controlador.ControladorEstudiante;
// import java.util.List;
import javax.swing.SwingUtilities;
import modelo.Docente;
import modelo.Estudiante;
import vista.VistaDocente;
import vista.VistaDocenteGUI;
// import vista.Prueba;
import vista.VistaEstudiante;
import vista.VistaEstudianteGUI;
import vista.VistaPrincipal;


public class PracticaMVC411 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Inicializamos el modelo creando un nuevo estudiante
        // Estudiante estudiante = new Estudiante();

        // Inicializamos la vista
        // VistaEstudiante vista = new VistaEstudiante();

        // ControladorEstudiante controlador = new ControladorEstudiante(estudiante, vista);
 
        // estudiante.setId(44);
        // estudiante.setNombre("Carlos-SP");
        //estudiante.setEdad(21);

        // controlador.crearEstudiante(estudiante);
        // controlador.removerEstudiante(40);
        // controlador.removerEstudiante(43);
        //controlador.actualizarEstudiante(estudiante);

        // List<Estudiante> estudiantes = controlador.obtenerEstudiantes();

        // vista.mostrarDetallesEstudiantes(estudiantes);

        
       
        SwingUtilities.invokeLater(() -> {
            new VistaPrincipal().setVisible(true);
        });
        
    
    }
    

}
