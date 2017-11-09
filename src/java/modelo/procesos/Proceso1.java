package modelo.procesos;

/**
 *
 * @author miguelangel bronze
 */
public class Proceso1 extends ProcesoAbstract {

   
    public Proceso1 () {
    	this.tiempoVida=10;
    }
    @Override
    public void tarea() {
        System.out.println("ESTOY HACIENDO MI TAREA DE BEBERME EL AGUARDIENTE");
    }

}
