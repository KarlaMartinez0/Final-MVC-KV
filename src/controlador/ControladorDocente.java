/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.List;
import modelo.Docente;
import modelo.Estudiante;
import vista.VistaDocente;
import vista.VistaDocenteGUI;
import vista.VistaEstudiante;

public class ControladorDocente {
    
    private Docente modelo;
    private VistaDocente vista;
    
    public ControladorDocente(Docente modelo, VistaDocente vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void crearDocente(Docente nuevoDocente){
        nuevoDocente.insertarDocente(nuevoDocente);
        System.out.println("Estudiante creado correctamente!");
    }
    
    public List<Docente> obtenerDocentes() {
        return modelo.obtenerTodosLosDocentes();
    }
    public void removerDocente(int id){
        modelo.deleteDocente(id);
        System.out.println("Estudiante con id " + id + " eliminado correctamente!");
    }
    
    public void actualizarDocente(Docente docente){
        modelo.updateDocente(docente);
        System.out.println("Estudiante actualizado correctamente!");
    }
}
