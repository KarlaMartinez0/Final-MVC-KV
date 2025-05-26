/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import java.util.List;
import modelo.Docente;
/**
 *
 * @author amart
 */
public class VistaDocente {
    public void mostrarDetallesDocentes(List<Docente> docentes) {
        System.out.println("Listo de estudiantes: ");
        
        for (Docente estudiante : docentes) {
            System.out.println(
                "Id: " + estudiante.getIdDocente()
                + ", Nombre: " + estudiante.getNomDocente()
            );
        }
    }
}
