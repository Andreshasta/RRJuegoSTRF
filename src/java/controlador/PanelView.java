package controlador;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Procesador;
import modelo.algoritmo.Ejecutor;
import modelo.procesos.Halar;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.ToggleEvent;

/**
 *
 * @author Andres
 */
@ManagedBean(name = "panelView")
@SessionScoped
public class PanelView implements Serializable {
    
    Procesador procesador1;
    Procesador procesador2;
    Procesador procesador3;
    Queue colaFin;
    Ejecutor ejecutor;
    
    public PanelView() {
        procesador1 = new Procesador();
        procesador2 = new Procesador();
        procesador3 = new Procesador();
        colaFin = new LinkedList();
        ejecutor = new Ejecutor();
    }

    public void onClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void onToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void agregarProcesoHalar1() {
        System.out.println("agregando proceso Halar al nucleo 1");
        procesador1.addListos(new Halar(procesador1.getListos().size() + 1, "Halar", 3000));
        RequestContext.getCurrentInstance().update("frmjuego");
        System.out.println("add hl1: " + procesador1);
    }

    public void agregarProcesoBloquear1() {
        System.out.println("agregando proceso Bloquear al nucleo 1");
        procesador1.addListos(new Halar(procesador1.getListos().size() + 1, "Bloquear", 2000));
        RequestContext.getCurrentInstance().update("frmjuego");
        System.out.println("add bl1: " + procesador1);
    }

    public void agregarProcesoHalar2() {
        System.out.println("agregando proceso Halar al nucleo 2");
        procesador2.addListos(new Halar(procesador2.getListos().size() + 1, "Halar", 3000));
        RequestContext.getCurrentInstance().update("frmjuego");
        System.out.println("add hl2: " + procesador2);
    }

    public void agregarProcesoBloquear2() {
        System.out.println("agregando proceso Bloquear al nucleo 2");
        procesador2.addListos(new Halar(procesador2.getListos().size() + 1, "Bloquear", 2000));
        RequestContext.getCurrentInstance().update("frmjuego");
        System.out.println("add bl2: " + procesador2);
    }

    public void agregarProcesoHalar3() {
        System.out.println("agregando proceso Halar al nucleo 3");
        procesador3.addListos(new Halar(procesador3.getListos().size() + 1, "Halar", 3000));
        RequestContext.getCurrentInstance().update("frmjuego");
        System.out.println("add hl3: " + procesador3);
    }

    public void agregarProcesoBloquear3() {
        System.out.println("agregando proceso Bloquear al nucleo 3");
        procesador3.addListos(new Halar(procesador3.getListos().size() + 1, "Bloquear", 2000));
        RequestContext.getCurrentInstance().update("frmjuego");
        System.out.println("add bl3: " + procesador3);
    }

    public void iniciarejecucion() {
        ejecutor.iniciarEjecucion(procesador1);
//        ejecutor.iniciarEjecucion(procesador2);
//        ejecutor.iniciarEjecucion(procesador3);
        RequestContext.getCurrentInstance().update("frmjuego");
    }
////////////

    public Procesador getProcesador1() {
        return procesador1;
    }
    
    public void setProcesador1(Procesador procesador1) {
        this.procesador1 = procesador1;
    }
    
    public Procesador getProcesador2() {
        return procesador2;
    }
    
    public void setProcesador2(Procesador procesador2) {
        this.procesador2 = procesador2;
    }
    
    public Procesador getProcesador3() {
        return procesador3;
    }
    
    public void setProcesador3(Procesador procesador3) {
        this.procesador3 = procesador3;
    }
    
    public Queue getColaFin() {
        colaFin.clear();
        colaFin.addAll(procesador1.getFinalizados());
        colaFin.addAll(procesador2.getFinalizados());
        colaFin.addAll(procesador3.getFinalizados());
        return colaFin;
    }
    
    public void setColaFin(Queue colaFin) {
        this.colaFin = colaFin;
    }
    
}
