import java.lang.StringBuilder;
import java.util.List;
import java.util.LinkedList;

public class Grafo 
{
    private MatrizAdjacencia matriz;
    private ListaAdjacencia lista;
    private Vertice [] vertices;
    private int n;

    public Grafo(int v)
    {
        this.n = v;
        this.vertices = new Vertice[v];
        this.matriz = new MatrizAdjacencia(v);
        this.lista = new ListaAdjacencia(v);
        for(int i=0; i<v; i++)
        {
            vertices[i] = new Vertice();
        }
    }

    public String toString() 
    {
        // StringBuilder resp = new StringBuilder();
        return this.lista.toString();
    }
}

class MatrizAdjacencia
{
    private boolean [][] arestas;

    public MatrizAdjacencia(int v)
    {
        arestas = new boolean [v][v];
    }
}

class ListaAdjacencia
{
    private List lista []; 
    private int n;

    public ListaAdjacencia(int v)
    {
        this.n = v;
        this.lista = new LinkedList [v];

        for(int i=0; i<n; i++)
        {
            lista[i] = new LinkedList<Integer>();
        }
    }

    public void addAresta(int v1, int v2) 
    {
        if(v1 < n && v2 < n)
        {
            if(!lista[v1].contains(v2))
            {
                lista[v1].add(v2);
            }
            if(!lista[v2].contains(v1))
            {
                lista[v2].add(v1);
            }
        }
    }

    public void removeAresta(int v1, int v2)
    {
        if(v1 < n && v2 < n)
        {
            if(lista[v1].contains(v2))
            {
                lista[v1].remove(lista[v1].indexOf(v2));
            }
            if(lista[v2].contains(v1))
            {
                lista[v2].remove(lista[v2].indexOf(v1));
            }
        }
    }

    @SuppressWarnings("unchecked")
    public String toString() 
    {
        StringBuilder resp = new StringBuilder();
        for(int i=0; i<n; i++)
        {
            resp.append(i + " : ");
            resp.append(lista[i].toString() + "\n");
        }
        return resp.toString();
    }
}

class Vertice
{
    private int valor;
    private String rotulo;

    public Vertice()
    {

    }

    public Vertice(int valor)
    {
        this.valor = valor;
    }

    public Vertice(String rotulo)
    {
        this.rotulo = rotulo;
    }

    public Vertice(String rotulo, int valor)
    {
        this.rotulo = rotulo;
        this.valor = valor;
    }

    void setValor(int valor)
    {
        this.valor = valor;
    }

    int getValor()
    {
        return this.valor;
    }

    void setRotulo(String rotulo)
    {
        this.rotulo = rotulo;
    }

    String getRotulo()
    {
        return this.rotulo;
    }
}