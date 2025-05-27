/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.List;
import modelo.Estudiante;
import modelo.Matricula;
import vista.VistaMatricula;

public class ControladorMatricula {
    private Matricula modeloMatricula;
    private VistaMatricula vistaMatricula;

    public ControladorMatricula(Matricula modeloMatricula, VistaMatricula vistaMatricula) {
        this.modeloMatricula = modeloMatricula;
        this.vistaMatricula = vistaMatricula;
    }
    public void setMatriculaNota(float nota) {
        modeloMatricula.setNota(nota);
    }
    public float getMatriculaNota() {
        return modeloMatricula.getNota();
    }
    public void setMatriculaCurso(int idCurso) {
        modeloMatricula.setIdCurso(idCurso);
    }
    public float getMatriculaCurso() {
        return modeloMatricula.getIdCurso();
    }
    public void setMatriculaEstudiante(int idEstudiante) {
        modeloMatricula.setIdEstudiante(idEstudiante);
    }
    public float getMatriculaEstudiante() {
        return modeloMatricula.getIdEstudiante();
    }
    public void crearMatricula(Matricula nuevaMatricula){
        nuevaMatricula.insertarMatricula(nuevaMatricula);
        System.out.println("Matricula creado correctamente!");
    }
    public List<Matricula> obtenerMatricula() {
        return modeloMatricula.obtenerTodosLasMatriculas();
    }
    
    public void removerMatricula(int id){
        modeloMatricula.deleteMatricula(id);
        System.out.println("Matricula con id " + id + " eliminado correctamente!");
    }
    
    public void actualizarMatricula(Matricula matricula){
        modeloMatricula.updateMatricula(matricula);
        System.out.println("Matricula actualizado correctamente!");
    }
    
}
