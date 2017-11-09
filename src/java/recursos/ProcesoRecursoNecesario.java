package recursos;

/**
 *
 * @author Felipe
 */
public class ProcesoRecursoNecesario {

    private int id;
    private double cantidad;
    private boolean otorgado;

    public ProcesoRecursoNecesario(int id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean getOtorgado() {
        return otorgado;
    }

    public void setOtorgado(boolean otorgado) {
        this.otorgado = otorgado;
    }

}

