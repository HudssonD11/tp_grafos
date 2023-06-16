public class ArestaMatriz extends Aresta
{
    private boolean existe = false;

    public ArestaMatriz(boolean e)
    {
        this.existe = e;
    }

    public void setExiste(boolean existe)
    {
        this.existe = existe;
    }

    public boolean getExiste()
    {
        return existe;
    }
}
