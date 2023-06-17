public class ArestaMatriz extends Aresta
{
    private boolean existe = false;

    public ArestaMatriz(boolean e)
    {
        this.existe = e;
    }

    private void reset()
    {
        setNome(null);
        setPeso(null);
    }

    public void setExiste(boolean existe)
    {
        this.existe = existe;
        if(!existe)
        {
            reset();
        }
    }

    public boolean existe()
    {
        return existe;
    }
}
