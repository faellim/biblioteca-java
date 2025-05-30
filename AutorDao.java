import java.util.List;

public interface AutorDao {
    void create(Autor autor);
    Autor read(int id);
    List<Autor> readAll();
    void update(Autor autor);
    void delete(int id);
}