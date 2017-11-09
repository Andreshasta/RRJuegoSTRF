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
public class ProcesoAtaca1 extends ProcesoAbstract {

   
    public ProcesoAtaca1 () {
    	this.tiempoVida=9;
    	this.setRecursoNecesario(0, 40);
    	this.setNombre("Ataca 1");
    }
    @Override
    public void tarea() {
        System.out.println("ataque 1 ");
    }

}
