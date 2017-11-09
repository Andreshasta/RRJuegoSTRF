package estructuras;

import java.io.Serializable;
import modelo.procesos.Proceso;

/**
 *
 * @author Andres
 */
public class NodoW implements Serializable, INodo {

    private NodoW siguiente;
    private Proceso proceso;

    public NodoW() {
    }

    public NodoW(Proceso proceso) {
        this.siguiente = null;
        this.proceso = proceso;
    }

    @Override
    public INodo getSiguiente() {
        return siguiente;
    }

    @Override
    public void setSiguiente(INodo siguiente) {
        if (siguiente instanceof NodoW) {
            this.siguiente = (NodoW) siguiente;
        }
    }

    @Override
    public Proceso getProceso() {
        return proceso;

    }

}
