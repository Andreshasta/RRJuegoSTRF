package controlador;

import estructuras.Cola;
import estructuras.Nodo;
import java.util.ArrayList;
import java.util.Collections;
import recursos.RecursoSistema;
import modelo.procesos.ProcesoAbstract;


public class ControladorHilo extends Thread {

	static final int PENALIZACIONSUSPENDIDO = 10000;
	private Cola listo, suspendido, bloqueado, auxiliarListo;
	private Cola copiaListo, copiaSuspendido, copiaBloqueado;
	private ProcesoAbstract proceso;
	ArrayList<RecursoSistema> recursosSistema;
	private long quantum;
	private int variable, variable2;
	int xx;

	private int idProcesador;

	public ControladorHilo(ArrayList<RecursoSistema> recursosSistemas, int i) {

		listo = new Cola();
		suspendido = new Cola();
		bloqueado = new Cola();
		this.recursosSistema = recursosSistemas;
		auxiliarListo = null;
		idProcesador = i;
		copiaBloqueado=new Cola();
		copiaSuspendido=new Cola();
		copiaListo=new Cola();

	}



	public void run() {
		while (true) {

			if (!listo.comprobarVacio()) {
				System.out.println("Procesador " + idProcesador);
				
				if (auxiliarListo != listo) {
					calcularQuantum();
				}
				proceso = (ProcesoAbstract) listo.desapilar().getProceso();
			
					if(entregarRecursos()) {
						System.out.println("Estan los recursos "+idProcesador+ "tarea");
						proceso.setTiempoInicio(System.currentTimeMillis());
						realizarTarea(proceso);
						proceso.setTiempoFin(System.currentTimeMillis());
						devolverRecursos();
						
						if (proceso.getTiempoRestante() > 0) {
							suspendido.apilar(new Nodo(proceso));
							System.out.println("SE HA SUSPENDIDO UN PROCESO " + idProcesador);
						}
						
					}else {						
						// aca se bloquea
						System.out.println("SE HA BLOQUEADO UN PROCESO " + idProcesador);
						devolverRecursos();
						cambiarBloqueado();
						
					}
			} else {
				 //System.out.println("no hay recursos");
				esperarXsegundos(1);

			}
			if (!suspendido.comprobarVacio()) {

				if (((System.currentTimeMillis())
						- ((long) suspendido.get(0).getProceso().SalidaEjecucion())) >= PENALIZACIONSUSPENDIDO) {
					listo.apilar(suspendido.get(0));
					suspendido.desapilar();
					System.out.println("SE HA ENVIADO A LISTO UN PROCESO SUSPENDIDO");
					/// esperarXsegundos(10);

				}

			}
			if (!bloqueado.comprobarVacio()) {
				System.out.println("HAY PROCESOS BLOQUEADOS " + idProcesador);
				// while (!bloqueado.comprobarVacio()) {
				if (verificarRecursos(bloqueado.get(0).getProceso())) {

					listo.apilar(bloqueado.get(0));
					bloqueado.desapilar();
					System.out.println("SE HA MANDADO A LISTO UN PROCESO BLOQUEADO " + idProcesador);
				}
				// }

			}
			//System.out.println("si los carga");
			copiaBloqueado=bloqueado;
			copiaListo=listo;
			copiaSuspendido=suspendido;
		}
	}

	// Métodos para el cambio de colas en los procesos
	public void anadirProceso(ProcesoAbstract proceso) {
		listo.apilar(new Nodo(proceso));
	}

	public void realizarTarea(ProcesoAbstract proceso) {
		proceso.tarea();
		// esperarXsegundos((int) proceso.getTiempoQuantum());// esto se va de aqui

		while ((System.currentTimeMillis() - proceso.inicioEjecucion()) <= (proceso.getTiempoQuantum())) {
		}
	}

	// Métodos para el control de los procesos
	public synchronized boolean verificarRecursos(ProcesoAbstract proceso) {
		boolean recursos = false;
		for (int i = 0; i < proceso.getRecursoNecesario().size(); i++) {
			for (int j = 0; j < recursosSistema.size(); j++) {
				System.out.println(recursosSistema.get(j).getId() + " cantidad disponible "
						+ recursosSistema.get(j).getCantidadDisponible());
				if (recursosSistema.get(j).getId() == proceso.getRecursoNecesario().get(i).getId()) {
					if (recursosSistema.get(j).comprobarRecursos(proceso.getRecursoNecesario().get(i).getCantidad())) {
						recursos = true;
					}
					
				} else {
					return false;
				}
			}
		}
		return recursos;
	}

	public synchronized boolean entregarRecursos() {
		for (int i = 0; i < proceso.getRecursoNecesario().size(); i++) {
			for (int j = 0; j < recursosSistema.size(); j++) {
				// aca los recursos
				if (recursosSistema.get(j).comprobarRecursos(proceso.getRecursoNecesario().get(i).getCantidad())) {
					recursosSistema.get(j).extraerRecurso(proceso.getRecursoNecesario().get(i).getCantidad());
					proceso.getRecursoNecesario().get(i).setOtorgado(true);
					System.out.println("Recurso necesario "+idProcesador+" : " + proceso.getRecursoNecesario().get(i).getCantidad());
					System.out.println(recursosSistema.get(j).getId() + " cantidad disponible "
							+ recursosSistema.get(j).getCantidadDisponible());
				} else {
					j = recursosSistema.size();
					i = proceso.getRecursoNecesario().size();					
					return false;
				}
			}
		}
		return true;
	}

	public synchronized void devolverRecursos() {
		for (int i = 0; i < proceso.getRecursoNecesario().size(); i++) {
			if (proceso.getRecursoNecesario().get(i).getOtorgado()) {
				for (int j = 0; j < recursosSistema.size(); j++) {
					if (recursosSistema.get(j).getId() == proceso.getRecursoNecesario().get(i).getId()) {
						// aca los recursos son los mismos
						recursosSistema.get(j).retornarRecurso(proceso.getRecursoNecesario().get(i).getCantidad());
					}
				}
			}
		}
	}

	public void cambiarListo() {
		listo.apilar(new Nodo(proceso));
	}

	public void cambiarSuspendido() {

	}

	public void cambiarBloqueado() {
		bloqueado.apilar(new Nodo(proceso));
		
	}

	// Métodos para calcular Quantum
	public void calcularQuantum() {
		//System.out.println("Cuantum entra " + idProcesador);
		Cola auxiliar = listo;
		//medianaCuantum();
		//mediaCuantum();
		int promedio=(int) Math.sqrt(medianaCuantum()*mediaCuantum());
		quantum= desviacion(promedio);
		//quantum= (int) Math.sqrt(medianaCuantum()*mediaCuantum());
		//System.out.println(quantum);
		listo = null;
		listo = new Cola();
		ArrayList<Long> tiempo;
		long tiempoProceso;
		int largo = obtenerTiempos(auxiliar).size();

		//System.out.println("tama�o de cola" + idProcesador + "  " + largo);
		
		while (!auxiliar.comprobarVacio() ) {
			tiempoProceso = auxiliar.get(0).getProceso().getTiempoRestante();
			
			if(  largo >1  ) {				
				

				if (tiempoProceso >= quantum) {
					Math.sqrt(Math.pow((quantum+tiempoProceso)/2,2));
					auxiliar.get(0).getProceso().setTiempoQuantum(quantum);
					listo.apilar(auxiliar.get(0));
				} else {
					auxiliar.get(0).getProceso().setTiempoQuantum(tiempoProceso);
					listo.apilar(auxiliar.get(0));
				}
				

			}else {
				auxiliar.get(0).getProceso().setTiempoQuantum(tiempoProceso);
				listo.apilar(auxiliar.get(0));
//				
			}
			
			System.out.println("Tiempo restante " + idProcesador + "  " + tiempoProceso + " Cuantum de "
					+ auxiliar.get(0).getProceso().getTiempoQuantum());

			auxiliar.desapilar();
			
			
		}
		auxiliar = listo;
	}

	public int desviacion(int promedio) {
		
		int media=0;
		Cola auxiliar = listo;
		ArrayList<Long> tiempo = obtenerTiempos(auxiliar);
		for(int i=0; i<tiempo.size();i++) {
			media+= Math.pow(Math.abs( promedio-tiempo.get(i)),2) ;
		}
		return (int)Math.sqrt(media/tiempo.size());
	}
	
	public long mediaCuantum() {
		int media=0;
		Cola auxiliar = listo;
		ArrayList<Long> tiempo = obtenerTiempos(auxiliar);
		for(int i=0; i<tiempo.size();i++) {
			media+= tiempo.get(i);
		}
		return media/tiempo.size();
	}
	public long medianaCuantum() {
		Cola auxiliar = listo;
		ArrayList<Long> tiempo = obtenerTiempos(auxiliar);
		Collections.sort(tiempo);

		//System.out.println("tama�o de cola" + idProcesador + "  " + tiempo.size());
		if (tiempo.size() % 2 == 0) {// frederick es un mk si el tama�o es uno ese valor esta en la poscicion 0
			variable = ((tiempo.size()) / 2) - 1;
			variable2 = (tiempo.size()) / 2;
			return  (tiempo.get(variable) + tiempo.get(variable2)) / 2;
		} else {
			variable = ((tiempo.size() + 1) / 2) - 1;
			return  tiempo.get(variable);
		}
		//System.out.println("Mediana " + idProcesador + "	" + quantum);
	}

	public ArrayList<Long> obtenerTiempos(Cola procesos) {
		Cola auxiliar = procesos;
		ArrayList<Long> tiempo = new ArrayList<Long>();
		for (int i = 0; i < auxiliar.size(); i++) {
			System.out.println("tiempo  " + idProcesador + "  :" + auxiliar.get(i).getProceso().getTiempoRestante());
			tiempo.add(auxiliar.get(i).getProceso().getTiempoRestante());
		}
		return tiempo;
	}

	private void esperarXsegundos(int segundos) {
		try {
			Thread.sleep(segundos * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	
	public Cola getCola() {
		return copiaListo;
		//return listo;
	}



	public Cola getSuspendido() {
		return copiaSuspendido;
	}



	public Cola getBloqueado() {
		return copiaBloqueado;
	}

}
