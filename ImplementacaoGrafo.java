public interface ImplementacaoGrafo 
{
    void addAresta(int v1, int v2);
    void removeAresta(int v1, int v2);
    void setPeso(int v1, int v2, int peso);
    void setNome(int v1, int v2, String nome);
    String getNome(int v1, int v2);
    Integer getPeso(int v1, int v2);
    boolean existeAresta(int v1, int v2);
    int getQuantidade();
    
}