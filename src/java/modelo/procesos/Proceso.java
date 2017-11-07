package modelo.procesos;

/**
 *
 * @author Andres
 */
public interface Proceso {
    public int getId();
    public String getNombre();
    public long getDuracion();
    public long getTrestante();
    public void setTrestante(long time);
    public int ejecutarTarea();
    public long getQuantum();
    public void setQuantum(long quantum);
}
