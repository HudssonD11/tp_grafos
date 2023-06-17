

public class ArestaLista extends Aresta
{
    private int vertice;

    public ArestaLista() 
    {

    }
    public ArestaLista(int v) 
    {
        this.vertice = v;
    }

    public void setVertice(int v)
    {
        this.vertice = v;
    }

    public int getVertice()
    {
        return vertice;
    }

    public String toString()
    {

        return "" + vertice;
    }
}



class Aresta 
{
    private Integer peso;
    private String nome;

    public Aresta() 
    {
        peso = 0;
        nome = null;
    }

    public void setPeso(Integer peso)
    {
        this.peso = peso;
    }

    public Integer getPeso()
    {
        return peso;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getNome()
    {
        return nome;
    }
}

