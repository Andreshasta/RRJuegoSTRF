package modelo.procesos;

/**
 *
 * @author miguelangel bronze
 */
public class Proceso2 extends ProcesoAbstract {

   
    public Proceso2 () {
    	this.tiempoVida=5;
    }
    @Override
    public void tarea() {
        System.out.println("ESTOY HACIENDO MI TAREA DE beber cafe");
    }

}
