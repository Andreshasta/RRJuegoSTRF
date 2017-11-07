package modelo.algoritmo;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
        while (!procesador.getCpu().isEmpty() || !procesador.getListos().isEmpty()
                || !procesador.getSuspendidos().isEmpty() || !procesador.getBloqueados().isEmpty()) {
            hacerSalto(procesador);
            System.out.println("pasarinicial: " + procesador);
            ejecutar((Proceso) (procesador.getCpu().peek()));
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
        if (!procesador.getListos().isEmpty()) {
            Queue cola = new LinkedList();
            cola.addAll(procesador.getListos());
            while (!cola.isEmpty()) {
                Proceso proceso = (Proceso) (cola.poll());
//                if ((proceso.getDuracion() - this.quantum.getTimeInMillis()) < 0) {
                if ((proceso.getTrestante() - this.quantum.getTimeInMillis()) < 0) {
                    this.quantum.setTimeInMillis(proceso.getDuracion());
                }
            }
        } else {
            if (!procesador.getSuspendidos().isEmpty()) {
                pasarDeSusAListos(procesador);
            }
            if (!procesador.getBloqueados().isEmpty()) {
                pasarDeBloqAListos(procesador);
            }
        }
    }

    public void hacerSalto(Procesador procesador) {
        System.out.println("hacerSalto_1: " + procesador);
        Proceso transicion = null;
        if (!procesador.getCpu().isEmpty()) {
            transicion = (Proceso) (procesador.getCpu().poll());
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
        if (!procesador.getListos().isEmpty()) {
            procesador.addCpu((Proceso) (procesador.getListos().poll()));
        }
    }

    public void pasarABloqueado(Procesador procesador) {
//        System.out.println("pasarABloqueado: " + procesador);
        procesador.toString();
        if (!procesador.getCpu().isEmpty()) {
            procesador.addBloqueados((Proceso) (procesador.getCpu().poll()));
        }
    }

    public void pasarASuspendido(Procesador procesador) {
//        System.out.println("pasarASuspendido: " + procesador);
        procesador.toString();
        if (!procesador.getCpu().isEmpty()) {
            procesador.addSuspendidos((Proceso) (procesador.getCpu().poll()));
        }
    }

    public void pasarDeSusAListos(Procesador procesador) {
//        System.out.println("pasarDeSusAListos: " + procesador);
        procesador.toString();
        if (!procesador.getSuspendidos().isEmpty()) {
            procesador.addListos((Proceso) (procesador.getSuspendidos().poll()));
        }
    }

    public void pasarDeBloqAListos(Procesador procesador) {
//        System.out.println("pasarDeBloqAListos: " + procesador);
        procesador.toString();
        if (!procesador.getBloqueados().isEmpty()) {
            procesador.addListos((Proceso) (procesador.getBloqueados().poll()));
        }
    }

    public Calendar getQuantum() {
        return quantum;
    }

    public void setQuantum(Calendar quantum) {
        this.quantum = quantum;
    }

}
