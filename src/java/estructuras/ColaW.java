package estructuras;

import java.io.Serializable;
import modelo.procesos.Proceso;

/**
 *
 * @author Andres
 */
public class ColaW implements Serializable, ICola {

    private NodoW cabeza;
    private NodoW cola;

    public ColaW() {
    }

    public ColaW(INodo cabeza) {
        if (cabeza instanceof NodoW) {
            this.cabeza = (NodoW) cabeza;
        } else {
            System.out.println("El elemento recibido no es de tipo NodoW");
        }
    }

    @Override
    public void add(Proceso elemento) {
        apilar((new NodoW(elemento)));
    }

    @Override
    public void apilar(INodo siguiente) {
        if (comprobarVacio()) {
            cabeza = (NodoW)siguiente;
            cola = cabeza;
        } else {
            cola.setSiguiente(siguiente);
            cola = (NodoW) siguiente;
        }
    }

    @Override
    public INodo desapilar() {
        INodo auxiliar = null;
        if (!comprobarVacio()) {
            auxiliar = cabeza;
            cabeza = (NodoW) auxiliar.getSiguiente();
        }
        return auxiliar;
    }

    @Override
    public INodo consultaCabeza() {
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
        INodo auxiliar = cabeza;
        int tamano = 0;
        while (auxiliar != null) {
            tamano++;
            auxiliar = auxiliar.getSiguiente();
        }
        return tamano;
    }

    @Override
    public void clear() {
        while (!this.comprobarVacio()) {
            this.desapilar();
        }
    }

    @Override
    public void apilarCola(ICola cola) {
        ICola aux = new ColaW();
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
    public INodo getNodo() {
        return cabeza;
    }

    @Override
    public INodo get(int posicion) {
        INodo auxiliar = cabeza;
        int contador = 0;
        while (contador != posicion) {
            auxiliar = auxiliar.getSiguiente();
            contador++;
        }
        return auxiliar;
    }

    @Override
    public void imprimir() {
        if (cabeza != null) {
            INodo auxiliar = cabeza;
            while (auxiliar != null) {
                System.out.println("Proceso: " + auxiliar.getProceso().getNombre() + " | " + auxiliar.getProceso().getTrestante());
                auxiliar = auxiliar.getSiguiente();
            }
        } else {
            System.out.println("Cola vacia");
        }
    }

}
