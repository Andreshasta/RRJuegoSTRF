package estructuras;

import java.io.Serializable;
import modelo.procesos.Proceso;
import modelo.procesos.ProcesoAbstract;

/**
 *
 * @author miguelangel bronze
 */
public class Cola implements Serializable, ICola {

    private Nodo cabeza;
    private Nodo cola;

    public Cola() {
        cabeza = null;
    }

    public void add(ProcesoAbstract elemento) {
        apilar(new Nodo(elemento));
    }

    public void apilar(Nodo siguiente) {
        if (comprobarVacio()) {
            cabeza = siguiente;
            cola = cabeza;
        } else {
            cola.setSiguiente(siguiente);
            cola = siguiente;
        }
    }

    @Override
    public INodo desapilar() {
        INodo auxiliar = null;
        if (!comprobarVacio()) {
            auxiliar = cabeza;
            cabeza = (Nodo) auxiliar.getSiguiente();
        }
        return auxiliar;
    }

    @Override
    public Nodo consultaCabeza() {
        if (!comprobarVacio()) {
            return cabeza;
        } else {
            return null;
        }
    }

    @Override
    public boolean comprobarVacio() {
        return cabeza == null;
    }

    @Override
    public int size() {
        Nodo auxiliar = cabeza;
        int tamano = 0;
        while (auxiliar != null) {
            tamano++;
            auxiliar = (Nodo) auxiliar.getSiguiente();
        }
        return tamano;
    }

    @Override
    public void clear() {
        while (!this.comprobarVacio()) {
            this.desapilar();
        }
    }

    public void apilarCola(Cola cola) {
        Cola aux = new Cola();
        System.out.println("tam this: " + this.size());
        System.out.println("tam cola: " + cola.size());
        while (!cola.comprobarVacio()) {
            aux.apilar(cola.desapilar());
        }
        System.out.println("tam aux: " + aux.size());
        while (!aux.comprobarVacio()) {
            this.apilar(aux.desapilar());
        }
        System.out.println("tam this fin: " + this.size());
    }

    @Override
    public Nodo getNodo() {
        return cabeza;
    }
    

    @Override
    public Nodo get(int posicion) {
        Nodo auxiliar = cabeza;
        int contador = 0;
        while (contador != posicion) {
            auxiliar = (Nodo)auxiliar.getSiguiente();
            contador++;
        }
        return auxiliar;
    }

    @Override
    public void imprimir() {
        if (cabeza != null) {
            Nodo auxiliar = cabeza;
            while (auxiliar != null) {
                System.out.println("Proceso: " + auxiliar.getProceso().getNombre() + " | " + auxiliar.getProceso().getTrestante());
                auxiliar = (Nodo)auxiliar.getSiguiente();
            }
        } else {
            System.out.println("Cola vacia");
        }
    }

    @Override
    public void add(Proceso elemento) {
        if (elemento instanceof ProcesoAbstract){
            add((ProcesoAbstract)(elemento));
        } else {
            System.out.println("El elemeno no es de tipo ProcesoAbstract");
        }
    }

    @Override
    public void apilar(INodo siguiente) {
        if (siguiente instanceof Nodo) {
            apilar((Nodo)(siguiente));
        }else{
            System.out.println("El elemento recibido no es de tipo Nodo");
        }
    }

    @Override
    public void apilarCola(ICola cola) {
        if (cola instanceof Cola) {
            apilarCola ((Cola)(cola));
        } else {
            System.out.println("El elemento recibido no es de tipo Cola");
        }
    }

}
