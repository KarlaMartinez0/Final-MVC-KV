/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.List;
import modelo.Curso;
import vista.VistaCurso;

public class ControladorCurso {
    
     private Curso modeloCurso;
    private VistaCurso vistaCurso;

    public ControladorCurso(Curso modeloCurso, VistaCurso vistaCurso) {
        this.modeloCurso = modeloCurso;
        this.vistaCurso = vistaCurso;
    }
    public void setNombreCurso(String nombre) {
        modeloCurso.setNomCurso(nombre);
    }

    public String getNombreCurso() {
        return modeloCurso.getNomCurso();
    }

    public void setCodigoDocente(int idDocente) {
        modeloCurso.setCodDocente(idDocente);
    }

    public int getCodigoDocente() {
        return modeloCurso.getCodDocente();
    }
    
    public void crearCurso(Curso nuevoCurso){
        nuevoCurso.insertarCurso(nuevoCurso);
        System.out.println("Curso creado correctamente!");
    }
    
    public List<Curso> obtenerCursos() {
        return modeloCurso.obtenerTodosLosCursos();
    }
    
    public void removerCurso(int id){
        modeloCurso.deleteCurso(id);
        System.out.println("Curso con id " + id + " eliminado correctamente!");
    }
    
    public void actualizarCurso(Curso curso){
        modeloCurso.updateCurso(curso);
        System.out.println("Curso actualizado correctamente!");
    }
}
