package estructuras;

import modelo.procesos.Proceso;

/**
 *
 * @author Andres
 */
public interface INodo {
    public INodo getSiguiente();
    public void setSiguiente(INodo siguiente);
    public Proceso getProceso();
}
