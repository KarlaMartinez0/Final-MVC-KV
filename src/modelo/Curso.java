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

public class Curso {
    
    private int idCurso;
    private String nombreCurso;
    private int codDocente;

    public void setCodDocente(int codDocente) {
        this.codDocente = codDocente;
    }

    public int getCodDocente() {
        return codDocente;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public String getNomCurso() {
        return nombreCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public void setNomCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }
    
    public static void insertarCurso(Curso curso) {
        Connection conexion = ConexionDatabase.getConnection();
        String sql = "INSERT INTO Curso (nom_curso, docenteCurso) VALUES (?, ?)";

        //String sql = "CALL insertar_estudiante(?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, curso.getNomCurso());
            statement.setInt(2, curso.getCodDocente());

            // INSERT, UPDATE, DELETE
            statement.executeUpdate();

            // SELECT
            // statement.executeQuery();
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
    
    public static List<Curso> obtenerTodosLosCursos() {
        List<Curso> listaCursos = new ArrayList<>();
        String sql = "SELECT id_curso, nom_curso, docenteCurso FROM curso";
        //String sql = "SELECT id, nombre, edad FROM get_estudiantes";

        try {
            Connection conexion = ConexionDatabase.getConnection();
            Statement stmt = conexion.createStatement();
            ResultSet resultado = stmt.executeQuery(sql);

            while (resultado.next()) {
                Curso curso = new Curso();
                curso.setIdCurso(resultado.getInt("idCurso"));
                curso.setNomCurso(resultado.getString("nombreCurso"));
                curso.setCodDocente(resultado.getInt("docenteCurso"));

                listaCursos.add(curso);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return listaCursos;
    }
    
    public static void deleteCurso(int id_curso) {
        Connection conexion = ConexionDatabase.getConnection();
        String sql = "DELETE FROM curso WHERE id_curso = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id_curso);

            // INSERT, UPDATE, DELETE
            statement.executeUpdate();

        } catch (Exception error) {
            error.printStackTrace();
        }
    }
    
    public static void updateCurso(Curso curso) {
        Connection conexion = ConexionDatabase.getConnection();
        String sql = "UPDATE curso SET nom_curso = ?, docenteCurso = ? WHERE id_curso = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, curso.getNomCurso());
            statement.setInt(2, curso.getCodDocente());
            statement.setInt(3, curso.getIdCurso());

            // INSERT, UPDATE, DELETE
            statement.executeUpdate();

        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}
