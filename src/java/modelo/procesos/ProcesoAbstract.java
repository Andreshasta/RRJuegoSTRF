package modelo.procesos;

import java.util.ArrayList;
import recursos.ProcesoRecursoNecesario;

/**
 *
 * @author miguelangel
 */
public abstract class ProcesoAbstract implements Proceso {

    protected int id;
    protected String nombre;
    private ArrayList<Long> tiemposInicio;
    private ArrayList<Long> tiemposFin;
    protected long tiempoVida;
    private long tiempoQuantum;
    private long tiempoRestante;
    private ArrayList<ProcesoRecursoNecesario> recursoNecesario;

    public ProcesoAbstract() {
        tiemposInicio = new ArrayList<Long>();
        tiemposFin = new ArrayList<Long>();
        recursoNecesario = new ArrayList<ProcesoRecursoNecesario>();
    }

    public abstract void tarea();

    public void setTiempoInicio(long tiempo) {
        tiemposInicio.add(tiempo);
    }

    public void setTiempoFin(long tiempo) {
        tiemposFin.add(tiempo);
    }

    public float getTiempoInicio(int i) {
        return tiemposInicio.get(i);
    }

    public float getTiempoFin(int i) {
        return tiemposInicio.get(i);
    }

    public long tiempoLlevado() {
        long tiempo = 0;
        for (int x = 0; x < tiemposInicio.size(); x++) {
            tiempo += tiemposFin.get(x) - tiemposInicio.get(x);
        }
        return tiempo;
    }

    public long getTiempoRestante() {
        tiempoRestante = (tiempoVida * 1000) - tiempoLlevado();
        return tiempoRestante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<ProcesoRecursoNecesario> getRecursoNecesario() {
        return recursoNecesario;
    }

    public void setRecursoNecesario(int id, int cantidad) {
        this.recursoNecesario.add(new ProcesoRecursoNecesario(id, cantidad));
    }

    public long getTiempoQuantum() {
        return tiempoQuantum;
    }

    public void setTiempoQuantum(long tiempoCuantum) {
        this.tiempoQuantum = tiempoCuantum;
    }

    //ultimas veces
    public long SalidaEjecucion() {

        return tiemposFin.get(tiemposFin.size() - 1);

    }

    public long inicioEjecucion() {

        return tiemposInicio.get(tiemposInicio.size() - 1);

    }

    public long getTiempoVida() {
        return tiempoVida * 1000;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public long getDuracion() {
        return tiempoVida;
    }

    @Override
    public long getTrestante() {
        return tiempoRestante;
    }

    @Override
    public void setTrestante(long time) {
        tiempoRestante = time;
    }

    @Override
    public int ejecutarTarea() {
        tarea();
        return 0;
    }

    @Override
    public long getQuantum() {
        return tiempoQuantum;
    }

    @Override
    public void setQuantum(long quantum) {
        tiempoQuantum = quantum;
    }
}
