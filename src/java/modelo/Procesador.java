package modelo;

import estructuras.ColaW;
import estructuras.ICola;
import modelo.procesos.Proceso;

/**
 *
 * @author Andres
 */
public class Procesador {

    private ICola cpu;
    private ICola listos;
    private ICola suspendidos;
    private ICola bloqueados;
    private ICola finalizados;

    public Procesador() {
        cpu = new ColaW();
        listos = new ColaW();
        suspendidos = new ColaW();
        bloqueados = new ColaW();
        finalizados = new ColaW();
    }

    public ICola getCpu() {
        return cpu;
    }

    public void setCpu(ColaW cpu) {
        this.cpu = cpu;
    }

    public void addCpu(Proceso elemento) {
        cpu.add(elemento);
    }

    public ICola getListos() {
        return listos;
    }

    public void setListos(ColaW listos) {
        this.listos = listos;
    }

    public void addListos(Proceso elemento) {
        listos.add(elemento);
    }

    public ICola getSuspendidos() {
        return suspendidos;
    }

    public void setSuspendidos(ColaW suspendidos) {
        this.suspendidos = suspendidos;
    }

    public void addSuspendidos(Proceso elemento) {
        suspendidos.add(elemento);
    }

    public ICola getBloqueados() {
        return bloqueados;
    }

    public void setBloqueados(ColaW bloqueados) {
        this.bloqueados = bloqueados;
    }

    public void addBloqueados(Proceso elemento) {
        bloqueados.add(elemento);
    }

    public ICola getFinalizados() {
        return finalizados;
    }

    public void setFinalizados(ColaW finalizados) {
        this.finalizados = finalizados;
    }

    public void addFinalizados(Proceso elemento) {
        this.finalizados.add(elemento);
    }

    @Override
    public String toString() {
        return "Procesador{" + "cpu=" + cpu
                + ",\n listos=" + listos
                + ",\n suspendidos=" + suspendidos
                + ",\n bloqueados=" + bloqueados
                + ",\n finalizados=" + finalizados + '}';
    }

}
