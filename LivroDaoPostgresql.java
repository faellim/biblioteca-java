import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDaoPostgresql implements LivroDao {

    public void create(Livro livro) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO LIVROS (LIV_ISBN, LIV_TITULO, LIV_ANO, LIV_AUT_ID) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, livro.getIsbn());
            stmt.setString(2, livro.getTitulo());
            stmt.setInt(3, livro.getAno());
            stmt.setInt(4, livro.getAutor().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Livro read(String isbn) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                 "SELECT l.*, a.* FROM LIVROS l JOIN AUTORES a ON l.LIV_AUT_ID = a.AUT_ID WHERE l.LIV_ISBN = ?")) {
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Autor autor = new Autor(rs.getInt("AUT_ID"), rs.getString("AUT_NOME"), rs.getString("AUT_PAIS"));
                return new Livro(rs.getString("LIV_ISBN"), rs.getString("LIV_TITULO"), rs.getInt("LIV_ANO"), autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Livro> findByAutor(Autor autor) {
        List<Livro> livros = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM LIVROS WHERE LIV_AUT_ID = ?")) {
            stmt.setInt(1, autor.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                livros.add(new Livro(rs.getString("LIV_ISBN"), rs.getString("LIV_TITULO"), rs.getInt("LIV_ANO"), autor));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public void update(Livro livro) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE LIVROS SET LIV_TITULO = ?, LIV_ANO = ?, LIV_AUT_ID = ? WHERE LIV_ISBN = ?")) {
            stmt.setString(1, livro.getTitulo());
            stmt.setInt(2, livro.getAno());
            stmt.setInt(3, livro.getAutor().getId());
            stmt.setString(4, livro.getIsbn());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String isbn) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM LIVROS WHERE LIV_ISBN = ?")) {
            stmt.setString(1, isbn);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}