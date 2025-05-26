/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.List;
import modelo.Estudiante;
import vista.VistaEstudiante;

public class ControladorEstudiante {

    private Estudiante modelo;
    private VistaEstudiante vista;

    public ControladorEstudiante(Estudiante modelo, VistaEstudiante vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void setNombreEstudiante(String nombre) {
        modelo.setNombre(nombre);
    }

    public String getNombreEstudiante() {
        return modelo.getNombre();
    }

    public void setEdadEstudiante(int edad) {
        modelo.setEdad(edad);
    }

    public int getEdadEstudiante() {
        return modelo.getEdad();
    }

    public void crearEstudiante(Estudiante nuevoEstudiante){
        nuevoEstudiante.insertarEstudiante(nuevoEstudiante);
        System.out.println("Estudiante creado correctamente!");
    }
    
    public List<Estudiante> obtenerEstudiantes() {
        return modelo.obtenerTodosLosEstudiantes();
    }
    
    public void removerEstudiante(int id){
        modelo.deleteEstudiante(id);
        System.out.println("Estudiante con id " + id + " eliminado correctamente!");
    }
    
    public void actualizarEstudiante(Estudiante estudiante){
        modelo.updateEstudiante(estudiante);
        System.out.println("Estudiante actualizado correctamente!");
    }
    
}
