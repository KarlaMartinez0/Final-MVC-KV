/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class Matricula {
    private int idMatricula;
    private float nota;
    private int idCurso;
    private int idEstudiante;
    
    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public float getNota() {
        return nota;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }
   
    public static void insertarMatricula (Matricula matricula) {
        Connection conexion = ConexionDatabase.getConnection();
        String sql = "INSERT INTO matricula (id, cod_estuduante,cod_curso,nota_curso) VALUES (?, ?, ?, ?)";

        //String sql = "CALL insertar_estudiante(?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setFloat(1, matricula.getNota());
            statement.setInt(2, matricula.getIdCurso());
            statement.setInt(3, matricula.getIdEstudiante());
            

            // INSERT, UPDATE, DELETE
            statement.executeUpdate();

            // SELECT
            // statement.executeQuery();
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public static List<Matricula> obtenerTodosLasMatriculas() {
        List<Matricula> listaMatriculas = new ArrayList<>();
        String sql = "SELECT id, nombre, edad FROM estudiante";
        //String sql = "SELECT id, nombre, edad FROM get_estudiantes";

        try {
            Connection conexion = ConexionDatabase.getConnection();
            Statement stmt = conexion.createStatement();
            ResultSet resultado = stmt.executeQuery(sql);

            while (resultado.next()) {
                Matricula matricula = new Matricula();
                matricula.setIdMatricula(resultado.getInt("id"));
                matricula.setNota(resultado.getFloat("Nota"));
                matricula.setIdCurso(resultado.getInt("id Curso"));
                matricula.setIdEstudiante(resultado.getInt("id Curso"));

                listaMatriculas.add(matricula);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return listaMatriculas;
    }

    public static void deleteMatricula(int idMatricula) {
        Connection conexion = ConexionDatabase.getConnection();
        String sql = "DELETE FROM matricula WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idMatricula);

            // INSERT, UPDATE, DELETE
            statement.executeUpdate();

        } catch (Exception error) {
            error.printStackTrace();
        }
    }

    public static void updateMatricula(Matricula matricula) {
        Connection conexion = ConexionDatabase.getConnection();
        String sql = "UPDATE matricula SET cod_estudiante = ?, cod_curso = ?, nota_curso = ? WHERE id = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setFloat(1, matricula.getNota());
            statement.setInt(2, matricula.getIdCurso());
            statement.setInt(3, matricula.getIdEstudiante());

            // INSERT, UPDATE, DELETE
            statement.executeUpdate();

        } catch (Exception error) {
            error.printStackTrace();
        }
    }
                   
    
}
