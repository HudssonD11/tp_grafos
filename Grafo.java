import java.lang.StringBuilder;
import java.util.List;
import java.util.LinkedList;

public class Grafo {
    private MatrizAdjacencia matriz;
    private ListaAdjacencia lista;
    private Vertice[] vertices;
    private int n;

    public Grafo(int v) {
        this.n = v;
        this.vertices = new Vertice[v];
        this.matriz = new MatrizAdjacencia(v);
        this.lista = new ListaAdjacencia(v);
        for (int i = 0; i < v; i++) {
            vertices[i] = new Vertice();
        }
    }

    public void addAresta(int v1, int v2) {
        this.lista.addAresta(v1, v2);
        this.matriz.addAresta(v1, v2);
    }

    public void removeAresta(int v1, int v2) {
        this.lista.removeAresta(v1, v2);
        this.matriz.removeAresta(v1, v2);
    }

    public String toString() {
        // StringBuilder resp = new StringBuilder();
        return this.matriz.toString();
    }
}

class MatrizAdjacencia implements ImplementacaoGrafo {
    private ArestaMatriz[][] arestas;
    private int n;

    public MatrizAdjacencia(int v) {
        this.n = v;
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
}

class ListaAdjacencia implements ImplementacaoGrafo {
    private List<ArestaLista> lista[];
    private int n;

    public ListaAdjacencia(int v) {
        this.n = v;
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
            ArestaLista tmp1 = new ArestaLista(v1);
            ArestaLista tmp2 = new ArestaLista(v2);
            lista[v1].add(tmp2);
            lista[v2].add(tmp1);
        }
    }

    @Override
    public void removeAresta(int v1, int v2) {
        if (validVertices(v1, v2) && existeAresta(v1, v2)) {
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
}

class Vertice {
    private int peso;
    private String nome;

    public Vertice() {

    }

    public Vertice(int peso) {
        this.peso = peso;
    }

    public Vertice(String nome) {
        this.nome = nome;
    }

    public Vertice(String nome, int peso) {
        this.nome = nome;
        this.peso = peso;
    }

    void setPeso(int peso) {
        this.peso = peso;
    }

    int getPeso() {
        return this.peso;
    }

    void setNome(String nome) {
        this.nome = nome;
    }

    String getNome() {
        return this.nome;
    }
}