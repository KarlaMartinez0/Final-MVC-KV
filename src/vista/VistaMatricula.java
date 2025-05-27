/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.util.List;
import modelo.Matricula;

public class VistaMatricula {
    
    public void mostrarDetallesEstudiantes(List<Matricula> matriculas) {
        System.out.println("Listo de Matriculas: ");
        
        for (Matricula matricula : matriculas) {
            System.out.println(
                "Id: " + matricula.getIdMatricula()
                + ", Nombre: " + matricula.getNota()
                + ", Nombre: " + matricula.getIdCurso()
                + ", Edad: " + matricula.getIdEstudiante()
            );
        }
    }
}
