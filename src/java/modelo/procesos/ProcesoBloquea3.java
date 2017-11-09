/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.procesos;

/**
 *
 * @author miguelangel bronze
 */
public class ProcesoBloquea3 extends ProcesoAbstract {

   
    public ProcesoBloquea3 () {
    	this.tiempoVida=1;
    	this.setRecursoNecesario(0,-15);
    }
    @Override
    public void tarea() {
        System.out.println("Jala el jugador1 ");
    }

}
