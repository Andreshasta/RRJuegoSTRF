package modelo;

import java.util.LinkedList;
import java.util.Queue;
import modelo.procesos.Proceso;

/**
 *
 * @author Andres
 */
public class Procesador {

    private Queue cpu;
    private Queue listos;
    private Queue suspendidos;
    private Queue bloqueados;
    private Queue finalizados;

    public Procesador() {
        cpu = new LinkedList();
        listos = new LinkedList();
        suspendidos = new LinkedList();
        bloqueados = new LinkedList();
        finalizados = new LinkedList();
    }

    public Queue getCpu() {
        return cpu;
    }

    public void setCpu(Queue cpu) {
        this.cpu = cpu;
    }

    public void addCpu(Proceso elemento) {
        cpu.add(elemento);
    }

    public Queue getListos() {
        return listos;
    }

    public void setListos(Queue listos) {
        this.listos = listos;
    }

    public void addListos(Proceso elemento) {
        listos.add(elemento);
    }

    public Queue getSuspendidos() {
        return suspendidos;
    }

    public void setSuspendidos(Queue suspendidos) {
        this.suspendidos = suspendidos;
    }

    public void addSuspendidos(Proceso elemento) {
        suspendidos.add(elemento);
    }

    public Queue getBloqueados() {
        return bloqueados;
    }

    public void setBloqueados(Queue bloqueados) {
        this.bloqueados = bloqueados;
    }

    public void addBloqueados(Proceso elemento) {
        bloqueados.add(elemento);
    }

    public Queue getFinalizados() {
        return finalizados;
    }

    public void setFinalizados(Queue finalizados) {
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
