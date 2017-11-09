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
public class ProcesoAtaca2 extends ProcesoAbstract {

   
    public ProcesoAtaca2 () {
    	this.tiempoVida=2;
    	this.setRecursoNecesario(0, 30);
    	this.setNombre("Ataca 2");
    }
    @Override
    public void tarea() {
        System.out.println("Jala el jugador 2 ");
    }

}
