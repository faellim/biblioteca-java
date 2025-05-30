public class Livro {
    private String isbn;
    private String titulo;
    private int ano;
    private Autor autor;

    public Livro(String isbn, String titulo, int ano, Autor autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.ano = ano;
        this.autor = autor;
    }

    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public int getAno() { return ano; }
    public Autor getAutor() { return autor; }

    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setAno(int ano) { this.ano = ano; }
    public void setAutor(Autor autor) { this.autor = autor; }

    @Override
    public String toString() {
        return "Livro{" + "isbn='" + isbn + "', titulo='" + titulo + "', ano=" + ano + ", autor=" + autor + "}";
    }
}