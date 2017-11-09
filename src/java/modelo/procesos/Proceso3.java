package modelo.procesos;

/**
 *
 * @author miguelangel bronze
 */
public class Proceso3 extends ProcesoAbstract {

   
    public Proceso3 () {
    	this.tiempoVida=16;
    }
    @Override
    public void tarea() {
        System.out.println("ESTOY HACIENDO MI TAREA DE comer sano");
    }

}
