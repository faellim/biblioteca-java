public class Autor {
    private int id;
    private String nome;
    private String pais;

    public Autor(int id, String nome, String pais) {
        this.id = id;
        this.nome = nome;
        this.pais = pais;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getPais() { return pais; }

    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setPais(String pais) { this.pais = pais; }

    @Override
    public String toString() {
        return "Autor{" + "id=" + id + ", nome='" + nome + "', pais='" + pais + "'}";
    }
}