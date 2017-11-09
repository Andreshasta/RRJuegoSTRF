package estructuras;

import modelo.procesos.Proceso;

/**
 *
 * @author Andres
 */
public interface ICola {
    public void add(Proceso elemento);
    public void apilar(INodo siguiente);
    public INodo desapilar();
    public INodo consultaCabeza();
    public boolean comprobarVacio();
    public int size();
    public void clear();
    public void apilarCola(ICola cola);
    public INodo getNodo();
    public INodo get(int posicion);
    public void imprimir();
}
