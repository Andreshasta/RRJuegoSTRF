package modelo.algoritmo;

import estructuras.ColaW;
import java.util.Calendar;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.Procesador;
import modelo.procesos.Proceso;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Andres
 */
//@ManagedBean(name = "ejecutor")
//@SessionScoped
public class Ejecutor {

    private Calendar quantum;
    private Calendar tinicio;
    private Calendar tfin;

    public Ejecutor() {
        quantum = Calendar.getInstance();
        quantum.setTimeInMillis(2000);
    }

    public void iniciarEjecucion(Procesador procesador) {
        calcularQuantum(procesador);
        FacesContext x = FacesContext.getCurrentInstance();
        HttpSession ses = (HttpSession) x.getExternalContext().getSession(false);
        RequestContext.getCurrentInstance().update("frmjuego");
//        System.out.println("ejecutor: " + procesador);
        while (!procesador.getCpu().comprobarVacio() || !procesador.getListos().comprobarVacio()
                || !procesador.getSuspendidos().comprobarVacio() || !procesador.getBloqueados().comprobarVacio()) {
            hacerSalto(procesador);
            System.out.println("pasarinicial: " + procesador);
            ejecutar((Proceso) (procesador.getCpu().desapilar().getProceso()));
            RequestContext.getCurrentInstance().update("frmjuego");
//            System.out.println("ejecutado: " + procesador);s
        }

        System.out.println("ejecutado: " + procesador);
    }

    public void ejecutar(Proceso proceso) {
        this.tinicio = Calendar.getInstance();
        this.tfin = Calendar.getInstance();
        proceso.setQuantum(this.quantum.getTimeInMillis());
        boolean ejecu = true;
        while ((tfin.getTimeInMillis() - tinicio.getTimeInMillis()) < quantum.getTimeInMillis()) {
//            System.out.println("quantum: " + quantum.getTimeInMillis());
//            System.out.println("restante: " + proceso.getTRestante());
//            System.out.println("dif: " + (tfin.getTimeInMillis() - tinicio.getTimeInMillis()));
            if (ejecu) {
                proceso.ejecutarTarea();
                ejecu = false;
            }
            tfin = Calendar.getInstance();
        }
        proceso.setTrestante(proceso.getTrestante() - (tfin.getTimeInMillis() - tinicio.getTimeInMillis()));
    }

    public void calcularQuantum(Procesador procesador) {
        if (!procesador.getListos().comprobarVacio()) {
            ColaW cola = new ColaW();
            cola.apilarCola(procesador.getListos());
            while (!cola.comprobarVacio()) {
                Proceso proceso = (Proceso) (cola.consultaCabeza().getProceso());
//                if ((proceso.getDuracion() - this.quantum.getTimeInMillis()) < 0) {
                if ((proceso.getTrestante() - this.quantum.getTimeInMillis()) < 0) {
                    this.quantum.setTimeInMillis(proceso.getDuracion());
                }
            }
        } else {
            if (!procesador.getSuspendidos().comprobarVacio()) {
                pasarDeSusAListos(procesador);
            }
            if (!procesador.getBloqueados().comprobarVacio()) {
                pasarDeBloqAListos(procesador);
            }
        }
    }

    public void hacerSalto(Procesador procesador) {
        System.out.println("hacerSalto_1: " + procesador);
        Proceso transicion = null;
        if (!procesador.getCpu().comprobarVacio()) {
            transicion = (Proceso) (procesador.getCpu().consultaCabeza().getProceso());
        }
        try {
            pasarACpu(procesador);
        } catch (NullPointerException n1) {
            System.out.println("nulo pasano a CPU");
        }
        try {
            pasarDeSusAListos(procesador);
        } catch (NullPointerException n1) {
            System.out.println("nulo pasano a CPU");
        }
        try {
            pasarDeBloqAListos(procesador);
        } catch (NullPointerException n1) {
            System.out.println("nulo pasano a CPU");
        }
        if (transicion != null) {
            if (transicion.getTrestante() <= 0) {
                pasarAFinalizados(procesador, transicion);
            } else {
                procesador.addSuspendidos(transicion);
            }
        }
        calcularQuantum(procesador);
        System.out.println("hacerSalto_2: " + procesador);
    }

    public void pasarAFinalizados(Procesador procesador, Proceso proceso) {
        procesador.addFinalizados(proceso);
    }

    public void pasarACpu(Procesador procesador) {
//        System.out.println("pasarACpu: " + procesador);
        procesador.toString();
        if (!procesador.getListos().comprobarVacio()) {
            procesador.addCpu((Proceso) (procesador.getListos().consultaCabeza().getProceso()));
        }
    }

    public void pasarABloqueado(Procesador procesador) {
//        System.out.println("pasarABloqueado: " + procesador);
        procesador.toString();
        if (!procesador.getCpu().comprobarVacio()) {
            procesador.addBloqueados((Proceso) (procesador.getCpu().consultaCabeza().getProceso()));
        }
    }

    public void pasarASuspendido(Procesador procesador) {
//        System.out.println("pasarASuspendido: " + procesador);
        procesador.toString();
        if (!procesador.getCpu().comprobarVacio()) {
            procesador.addSuspendidos((Proceso) (procesador.getCpu().consultaCabeza().getProceso()));
        }
    }

    public void pasarDeSusAListos(Procesador procesador) {
//        System.out.println("pasarDeSusAListos: " + procesador);
        procesador.toString();
        if (!procesador.getSuspendidos().comprobarVacio()) {
            procesador.addListos((Proceso) (procesador.getSuspendidos().consultaCabeza().getProceso()));
        }
    }

    public void pasarDeBloqAListos(Procesador procesador) {
//        System.out.println("pasarDeBloqAListos: " + procesador);
        procesador.toString();
        if (!procesador.getBloqueados().comprobarVacio()) {
            procesador.addListos((Proceso) (procesador.getBloqueados().consultaCabeza().getProceso()));
        }
    }

    public Calendar getQuantum() {
        return quantum;
    }

    public void setQuantum(Calendar quantum) {
        this.quantum = quantum;
    }

}
