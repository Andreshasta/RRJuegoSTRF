package estructuras;

import java.io.Serializable;
import modelo.procesos.ProcesoAbstract;

/**
 *
 * @author miguelangel
 */
public class Nodo implements INodo, Serializable{

    private INodo siguiente;
    private ProcesoAbstract proceso;

    public Nodo(ProcesoAbstract proceso) {
        this.siguiente = null;
        this.proceso = proceso;
    }

    public INodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public ProcesoAbstract getProceso() {
        return proceso;

    }

    @Override
    public void setSiguiente(INodo siguiente) {
        if (siguiente instanceof Nodo){
            setSiguiente((Nodo) (siguiente));
        }
    }

}
