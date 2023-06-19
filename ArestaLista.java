

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

