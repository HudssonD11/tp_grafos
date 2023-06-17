import java.lang.StringBuilder;
import java.util.List;
import java.util.LinkedList;

public class Grafo {
    private ImplementacaoGrafo implementacao;
    private Vertice[] vertices;
    private int n;

    public Grafo(int v) {
        this.n = v;
        this.vertices = new Vertice[v];
        this.implementacao = new ListaAdjacencia(v);
        for (int i = 0; i < v; i++) {
            vertices[i] = new Vertice();
        }
    }

    public Grafo(int v, char representacao) {
        this.n = v;
        this.vertices = new Vertice[v];
        if(representacao == 'L' || representacao == 'l')
        {
            this.implementacao = new ListaAdjacencia(v);
        } else 
        {
            this.implementacao = new MatrizAdjacencia(v);
        }
        for (int i = 0; i < v; i++) {
            vertices[i] = new Vertice();
        }
    }

    public boolean verticesAdjacentes(int v1, int v2)
    {
        return existeAresta(v1, v2);
    }

    public boolean arestasAdjacentes(int a1, int a2, int b1, int b2)
    {
        if(existeAresta(b1, b2) && existeAresta(a1, a2))
        {
            return (a1 == b1) || (a1 == b2) || (a2 == b1) || (a2 == b2);
        }
        return false;
    }

    public boolean incidencia(int v, int a1, int a2)
    {
        if(existeAresta(a1, a2) && validVertice(v))
        {
            return v == a1 || v == a2;
        }
        return false;
    }

    public boolean existeAresta(int v1, int v2)
    {
        return implementacao.existeAresta(v1, v2);
    }

    public int quantidadeVertices()
    {
        return n;
    }

    public int quantidadeArestas()
    {
        return implementacao.getQuantidade();
    }

    public boolean vazio()
    {
        return implementacao.getQuantidade() == 0;
    }

    public boolean completo()
    {
        return implementacao.getQuantidade() == n;
    }

    public void setNomeVertice(int v, String nome)
    {
        if(validVertice(v))
        {
            vertices[v].setNome(nome);
        }
    }

    public void setPesoVertice(int v, Integer peso)
    {
        if(validVertice(v))
        {
            vertices[v].setPeso(peso);
        }
    }

    public String getNomeVertice(int v)
    {
        if(validVertice(v))
        {
            return vertices[v].getNome();
        }
        return null;
    }

    public Integer getPesoVertice(int v)
    {
        if(validVertice(v))
        {
            return vertices[v].getPeso();
        }
        return null;
    }

    public void setNomeAresta(int v1, int v2, String nome)
    {
        implementacao.setNome(v1, v2, nome);
    }

    public String getNomeAresta(int v1, int v2, String nome)
    {
        return implementacao.getNome(v1, v2);
    }

    public void setPesoAresta(int v1, int v2, Integer peso)
    {
        implementacao.setPeso(v1, v2, peso);
    }

    public Integer getPesoAresta(int v1, int v2)
    {
        return implementacao.getPeso(v1, v2);
    }

    private boolean validVertice(int v)
    {
        return v<n;
    }

    public void addAresta(int v1, int v2) {
        this.implementacao.addAresta(v1, v2);
    }

    public void removeAresta(int v1, int v2) {
        this.implementacao.removeAresta(v1, v2);
    }

    public String toString() {
        return this.implementacao.toString();
    }
}

class MatrizAdjacencia implements ImplementacaoGrafo {
    private ArestaMatriz[][] arestas;
    private int n;
    private int arestas_n;

    public MatrizAdjacencia(int v) {
        this.n = v;
        this.arestas_n = 0;
        arestas = new ArestaMatriz[v][v];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arestas[i][j] = new ArestaMatriz(false);
            }
        }
    }

    @Override
    public void addAresta(int v1, int v2) {
        if (validVertices(v1, v2) && !existeAresta(v1, v2)) {
            this.arestas_n++;
            arestas[v1][v2].setExiste(true);
            arestas[v2][v1].setExiste(true);
        }
    }

    private boolean validVertices(int v1, int v2) {
        return (v1 < n && v2 < n);
    }

    @Override
    public void removeAresta(int v1, int v2) {
        if (existeAresta(v1, v2)) {
            this.arestas_n--;
            arestas[v1][v2].setExiste(false);
            arestas[v2][v1].setExiste(false);
        }
    }

    @Override
    public void setPeso(int v1, int v2, int peso) {
        if (existeAresta(v1, v2)) {
            arestas[v1][v2].setPeso(peso);
            arestas[v2][v1].setPeso(peso);
        }
    }

    

    @Override
    public void setNome(int v1, int v2, String nome) {
        if (existeAresta(v1, v2)) {
            arestas[v1][v2].setNome(nome);
            arestas[v2][v1].setNome(nome);
        }
    }

    @Override
    public boolean existeAresta(int v1, int v2) {
        if (validVertices(v1, v2)) {
            return arestas[v1][v2].existe();
        }
        return false;
    }

    public String toString() {
        StringBuilder resp = new StringBuilder();
        for (int i = 0; i < n; i++) {
            resp.append(i + " : [");
            for (int j = 0; j < n; j++) {
                if(existeAresta(i, j))
                {
                    resp.append(""+j+", ");
                }
            }
            resp.append("]\n");
        }
        return resp.toString().replace(", ]", "]");
    }

    @Override
    public String getNome(int v1, int v2) {
        if(existeAresta(v1, v2))
        {
            return arestas[v1][v2].getNome();
        }
        return null;
    }

    @Override
    public Integer getPeso(int v1, int v2) {
        if(existeAresta(v1, v2))
        {
            return arestas[v1][v2].getPeso();
        }
        return null;
    }

    public int getQuantidade()
    {
        return arestas_n;
    }
}

class ListaAdjacencia implements ImplementacaoGrafo {
    private List<ArestaLista> lista[];
    private int arestas_n;
    private int n;

    public ListaAdjacencia(int v) {
        this.n = v;
        this.arestas_n = 0;
        this.lista = new LinkedList[v];

        for (int i = 0; i < n; i++) {
            lista[i] = new LinkedList<ArestaLista>();
        }
    }

    private boolean validVertices(int v1, int v2) {
        return (v1 < n && v2 < n);
    }

    @Override
    public void addAresta(int v1, int v2) {
        if (validVertices(v1, v2) && !existeAresta(v1, v2)) {
            this.arestas_n++;
            ArestaLista tmp1 = new ArestaLista(v1);
            ArestaLista tmp2 = new ArestaLista(v2);
            lista[v1].add(tmp2);
            lista[v2].add(tmp1);
        }
    }

    @Override
    public void removeAresta(int v1, int v2) {
        if (validVertices(v1, v2) && existeAresta(v1, v2)) {
            this.arestas_n--;
            lista[v1].remove(indexOf(v1, v2));
            lista[v2].remove(indexOf(v2, v1));
        }
    }

    private int indexOf(int v1, int v2) {
        if (validVertices(v1, v2)) {
            for (int i = 0; i < lista[v1].size(); i++) {
                if (lista[v1].get(i).getVertice() == v2) {
                    return i;
                }
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    public String toString() {
        StringBuilder resp = new StringBuilder();
        for (int i = 0; i < n; i++) {
            resp.append(i + " : [");
            for (int j = 0; j < lista[i].size(); j++) {
                resp.append(lista[i].get(j).toString() + ", ");
            }
            resp.append("]\n");
        }
        return resp.toString().replace(", ]", "]");
    }

    @Override
    public void setPeso(int v1, int v2, int peso) {
        if (existeAresta(v1, v2)) {
            getAresta(v1, v2).setPeso(peso);
            getAresta(v2, v1).setPeso(peso);
        }
    }

    private ArestaLista getAresta(int v1, int v2) {
        ArestaLista resp = null;
        for (int i = 0; i < lista[v1].size(); i++) {
            if (lista[v1].get(i).getVertice() == v2) {
                resp = lista[v1].get(i);
            }
        }
        return resp;
    }

    @Override
    public void setNome(int v1, int v2, String nome) {
        if (existeAresta(v1, v2)) {
            getAresta(v1, v2).setNome(nome);
            getAresta(v2, v1).setNome(nome);
        }
    }

    @Override
    public boolean existeAresta(int v1, int v2) {
        if (validVertices(v1, v2)) {
            for (int i = 0; i < lista[v1].size(); i++) {
                if (lista[v1].get(i).getVertice() == v2) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public String getNome(int v1, int v2) {
        if(existeAresta(v1, v2))
        {
            return lista[v1].get(v2).getNome();
        }
        return null;
    }

    @Override
    public Integer getPeso(int v1, int v2) {
        if(existeAresta(v1, v2))
        {
            return lista[v1].get(v2).getPeso();
        }
        return null;
    }

    public int getQuantidade()
    {
        return arestas_n;
    }
}

class Vertice {
    private Integer peso;
    private String nome;

    public Vertice() {

    }

    public Vertice(Integer peso) {
        this.peso = peso;
    }

    public Vertice(String nome) {
        this.nome = nome;
    }

    public Vertice(String nome, Integer peso) {
        this.nome = nome;
        this.peso = peso;
    }

    void setPeso(Integer peso) {
        this.peso = peso;
    }

    Integer getPeso() {
        return this.peso;
    }

    void setNome(String nome) {
        this.nome = nome;
    }

    String getNome() {
        return this.nome;
    }
}