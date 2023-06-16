class Main
{
    public static void main(String[] args) 
    {
        Grafo grafo = new Grafo(5);
        grafo.addAresta(0, 1);
        grafo.addAresta(0, 4);
        grafo.addAresta(2, 3);
        grafo.removeAresta(1, 0);
        System.out.print(grafo);
    }
}