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
public class ProcesoAtaca3 extends ProcesoAbstract {

   
    public ProcesoAtaca3 () {
    	this.tiempoVida=6;
    	this.setRecursoNecesario(0, 50);
    	this.setNombre("Ataca 3");
    }
    @Override
    public void tarea() {
        System.out.println("ataque  3 ");
    }

}
