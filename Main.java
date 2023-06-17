class Main
{
    public static void main(String[] args) 
    {
        Grafo grafo = new Grafo(5, 'm');
        System.out.println(grafo.completo());
        System.out.println(grafo.vazio());

        grafo.addAresta(0, 1);
        grafo.addAresta(0, 2);
        grafo.addAresta(0, 3);
        grafo.addAresta(0, 4);
        grafo.addAresta(1, 2);
        grafo.addAresta(1, 3);
        grafo.addAresta(1, 4);
        grafo.addAresta(2, 3);
        grafo.addAresta(2, 4);
        grafo.addAresta(3, 4);
        System.out.println(grafo.completo());
        System.out.println(grafo.quantidadeArestas());
        System.out.println(grafo.quantidadeVertices());
        System.out.print(grafo);
    }
}