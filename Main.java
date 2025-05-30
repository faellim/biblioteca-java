public class Main {
    public static void main(String[] args) {
        AutorDao autorDao = new AutorDaoPostgresql();
        LivroDao livroDao = new LivroDaoPostgresql();

        Autor autor = new Autor(1, "George Orwell", "Reino Unido");
        autorDao.create(autor);

        Livro livro = new Livro("9780141036144", "1984", 1949, autor);
        livroDao.create(livro);

        System.out.println("Livro carregado: " + livroDao.read("9780141036144"));

        for (Livro l : livroDao.findByAutor(autor)) {
            System.out.println("Livro do autor: " + l);
        }
    }
}