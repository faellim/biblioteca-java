import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDaoPostgresql implements AutorDao {
    public void create(Autor autor) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO AUTORES (AUT_ID, AUT_NOME, AUT_PAIS) VALUES (?, ?, ?)")) {
            stmt.setInt(1, autor.getId());
            stmt.setString(2, autor.getNome());
            stmt.setString(3, autor.getPais());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Autor read(int id) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM AUTORES WHERE AUT_ID = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Autor(rs.getInt("AUT_ID"), rs.getString("AUT_NOME"), rs.getString("AUT_PAIS"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Autor> readAll() {
        List<Autor> autores = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM AUTORES")) {
            while (rs.next()) {
                autores.add(new Autor(rs.getInt("AUT_ID"), rs.getString("AUT_NOME"), rs.getString("AUT_PAIS")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autores;
    }

    public void update(Autor autor) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE AUTORES SET AUT_NOME = ?, AUT_PAIS = ? WHERE AUT_ID = ?")) {
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getPais());
            stmt.setInt(3, autor.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM AUTORES WHERE AUT_ID = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}