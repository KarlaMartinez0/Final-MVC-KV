/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.util.List;
import modelo.Estudiante;

/**
 *
 * @author g.perezmoreno
 */
public class VistaEstudiante {
    
    public void mostrarDetallesEstudiantes(List<Estudiante> estudiantes) {
        System.out.println("Listo de estudiantes: ");
        
        for (Estudiante estudiante : estudiantes) {
            System.out.println(
                "Id: " + estudiante.getId()
                + ", Nombre: " + estudiante.getNombre()
                + ", Edad: " + estudiante.getEdad()
            );
        }
    }
}
