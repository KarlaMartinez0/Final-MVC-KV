
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Docente {
    
    private int idDocente; 
    private String nomDocente;

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public void setNomDocente(String nomDocente) {
        this.nomDocente = nomDocente;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public String getNomDocente() {
        return nomDocente;
    }
    
        public static void insertarDocente(Docente docente) {
        Connection conexion = ConexionDatabase.getConnection();
        String sql = "INSERT INTO docente (nom_docente) VALUES (?)";
        
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, docente.getNomDocente());
            
            statement.executeUpdate();
            
        }catch (Exception error) {
            error.printStackTrace();
        }
    }  
    public static List<Docente> obtenerTodosLosDocentes() {
        List<Docente> listaDocentes = new ArrayList<>();
        String sql = "SELECT id_docente, nom_docente FROM docente";
       

        try {
            Connection conexion = ConexionDatabase.getConnection();
            Statement stmt = conexion.createStatement();
            ResultSet resultado = stmt.executeQuery(sql);

            while (resultado.next()) {
                Docente docente = new Docente();
                docente.setIdDocente(resultado.getInt("id_docente"));
                docente.setNomDocente(resultado.getString("nom_docente"));
                

                listaDocentes.add(docente);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return listaDocentes;
    }
    
    public static void deleteDocente(int idDocente) {
        Connection conexion = ConexionDatabase.getConnection();
        String sql = "DELETE FROM docente WHERE id_docente = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idDocente);

            // INSERT, UPDATE, DELETE
            statement.executeUpdate();

        } catch (Exception error) {
            error.printStackTrace();
        }
    }    
    
    public static void updateDocente(Docente docente) {
        Connection conexion = ConexionDatabase.getConnection();
        String sql = "UPDATE docente SET nom_docente = ? WHERE id_docente = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, docente.getNomDocente());
            statement.setInt(2, docente.getIdDocente());

            // INSERT, UPDATE, DELETE
            statement.executeUpdate();

        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}

    
