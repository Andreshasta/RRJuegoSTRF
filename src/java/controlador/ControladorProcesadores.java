package Controlador;

import controlador.ControladorHilo;
import java.util.ArrayList;
import recursos.RecursoSistema;
import recursos.RecursoSistema1;
import modelo.procesos.Proceso1;
import modelo.procesos.Proceso2;
import modelo.procesos.Proceso3;
import modelo.procesos.ProcesoAbstract;

public class ControladorProcesadores {

    public void generarProcesadores() {
        ArrayList<RecursoSistema> arrayRecursos = new ArrayList<>();

        RecursoSistema agua = new RecursoSistema1(0, "Aguardiente", 100);
        
        arrayRecursos.add(agua);

        ControladorHilo procesador1 = new ControladorHilo(arrayRecursos,1);
        
        ProcesoAbstract proceso1 = new Proceso1();
        proceso1.setNombre("Beber aguardiente");
        proceso1.setRecursoNecesario(0, 99);
        //proceso1.setTiempoInicio(0);
        //proceso1.setTiempoFin(30);
        
        ProcesoAbstract proceso2 = new Proceso2();
        proceso2.setNombre("Beber cafe");
        proceso2.setRecursoNecesario(0, 40);
        
        ProcesoAbstract proceso3 = new Proceso3();
        proceso3.setNombre("comer sano");
        proceso3.setRecursoNecesario(0, 10);
        
        procesador1.anadirProceso(proceso1);
        procesador1.anadirProceso(proceso3);
        //procesador1.anadirProceso(proceso1);

        
        
        ControladorHilo procesador2 = new ControladorHilo(arrayRecursos,2);
        
       // procesador2.anadirProceso(proceso3);
        procesador2.anadirProceso(proceso2);
       // procesador2.anadirProceso(proceso1);
        
        
        ControladorHilo procesador3 = new ControladorHilo(arrayRecursos,3);
        procesador3.anadirProceso(proceso1);
        procesador3.anadirProceso(proceso3);

        procesador1.start();
        procesador2.start();
        //procesador3.start();

    }

}
