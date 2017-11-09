package recursos;

/**
 *
 * @author Felipe
 */
public abstract class RecursoSistema {
    private int id;
    private String nombre;
    private double cantidad;
    
    public RecursoSistema(int id, String nombre, double cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCantidadDisponible(double cantidadDisponible) {
        this.cantidad = cantidadDisponible;
    }

    public double getCantidadDisponible() {
        return cantidad;
    }

    public boolean comprobarRecursos(double pedido) {
        return pedido <= getCantidadDisponible();
    }

    public void extraerRecurso(double cantidad) {
        this.cantidad -= cantidad;
    }

    public void retornarRecurso(double cantidad) {
        this.cantidad += cantidad;
    }

}
