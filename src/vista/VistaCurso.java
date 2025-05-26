/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.util.List;
import modelo.Curso;
/**
 *
 * @author soportetic2
 */
public class VistaCurso {
        public void mostrarDetallesCursos(List<Curso> cursos) {
        System.out.println("Listo de estudiantes: ");
        
        for (Curso curso : cursos) {
            System.out.println(
                "Id: " + curso.getIdCurso()
                + ", Nombre: " + curso.getNomCurso()
                + ", Docente: " + curso.getCodDocente()
            );
        }
    }
}
