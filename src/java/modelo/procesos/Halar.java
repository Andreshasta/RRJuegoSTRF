package modelo.procesos;


/**
 *
 * @author Andres
 */
public class Halar implements Proceso{

    private  int id;
    private String nombre;
    private long duracion;
    private long trestante;
    private long quantum;

    public Halar() {
    }

    public Halar(int id, String nombre, long duracion) {
        this.id=id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.trestante = duracion;
        this.quantum=duracion;
    }
    
    @Override
    public int ejecutarTarea() {
        System.out.println("Halando.");
        return 0;
    }
    
    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public long getDuracion() {
        return duracion;
    }
    
    @Override
    public long getTrestante() {
        return trestante;
    }
    
    @Override
    public void setTrestante(long time){
        this.trestante = time;
    }

    

    

    @Override
    public int getId() {
        return id;
    }

    @Override
    public long getQuantum() {
        return quantum;
    }

    @Override
    public void setQuantum(long quantum) {
        this.quantum=quantum;
    }

    @Override
    public String toString() {
        return "Halar{" + "id=" + id + ", nombre=" + nombre + ", duracion=" + duracion + ", trestante=" + trestante + ", quantum=" + quantum + '}';
    }
    
}
