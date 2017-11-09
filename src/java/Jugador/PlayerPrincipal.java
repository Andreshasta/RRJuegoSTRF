package Jugador;

import java.util.ArrayList;

import controlador.ControladorHilo;
import modelo.procesos.ProcesoAtaca1;
import modelo.procesos.ProcesoAtaca2;
import modelo.procesos.ProcesoAtaca3;
import modelo.procesos.ProcesoBloquea1;
import modelo.procesos.ProcesoBloquea2;
import modelo.procesos.ProcesoBloquea3;
import recursos.RecursoSistema;
import recursos.RecursoSistema1;


public class PlayerPrincipal {
/*
	public static final int XInicial = 250;
	public static final int YInicial = 200;
	public static final int YFinal = 240;
	public static final int XMovimiento = 3;
	public static final int YMovimiento = 3;
	public int YPos, XPos; // para la posición actual

	boolean ataca1,aataca1;
	boolean bloquea1,abloquea1 ;
	boolean ataca2,aataca2;
	boolean bloquea2,abloquea2;
	boolean ataca3,aataca3;
	boolean bloquea3,abloquea3;
	boolean sale;
	
	public int puntaje=0;

	TextureRegion frame;
	public Animation empate;
	public Animation ganaJugador1;
	public Animation ganaJugador2;
	public Animation ganaJugador3;
	public Animation cercaJugador1;
	public Animation cercaJugador2;
	public Animation cercaJugador3;
	public ControladorHilo procesador1;
	public ControladorHilo procesador2;
	public ControladorHilo procesador3;
	
	int random;
//	ProcesoAbstract procesoAtaca1;
//	ProcesoAbstract procesoAtaca2;
//	ProcesoAbstract procesoAtaca3;
//	ProcesoAbstract procesobloquea1;
//	ProcesoAbstract procesobloquea2;
//	ProcesoAbstract procesobloquea3;

	public PlayerPrincipal() {
		animacionCentro();
		animacionCercaJugador1();
		animacionCercaJugador2();
		animacionCercaJugador3();
		animacionGanaJugador1();
		animacionGanaJugador2();
		animacionGanaJugador3();
		generarProcesadores();
	}

	public void entradaTeclas() {
		// jugador 1
		//Gdx.input.isKeyJustPressed(key)
		//Gdx.input.isKeyPressed(Input.Keys.W)
		ataca1 = Gdx.input.isKeyJustPressed(Input.Keys.W);
		bloquea1 = Gdx.input.isKeyJustPressed(Input.Keys.S);
		// jugador 2
		ataca2 = Gdx.input.isKeyJustPressed(Input.Keys.UP);
		bloquea2 = Gdx.input.isKeyJustPressed(Input.Keys.DOWN);

		// jugador 3
		ataca3 = Gdx.input.isKeyJustPressed(Input.Keys.I);
		bloquea3 = Gdx.input.isKeyJustPressed(Input.Keys.K);
		
		sale = Gdx.input.isKeyJustPressed(Input.Keys.Q);

		int ancho = Gdx.graphics.getWidth() - 20;
		
	
		
		if (ataca1) {
			random= (int) (Math.random()*2);
			if(random==0)procesador1.anadirProceso(new ProcesoAtaca1());
			if(random==1)procesador1.anadirProceso(new ProcesoAtaca2());
			if(random==2)procesador1.anadirProceso(new ProcesoAtaca3());
			
			
		}
		if (bloquea1) {
			procesador1.anadirProceso(new ProcesoBloquea1());
		}
		if (ataca2) {
			random= (int) (Math.random()*2);
			if(random==0)procesador2.anadirProceso(new ProcesoAtaca1());
			if(random==1)procesador2.anadirProceso(new ProcesoAtaca2());
			if(random==2)procesador2.anadirProceso(new ProcesoAtaca3());
		}
		if (bloquea2) {
			procesador2.anadirProceso(new ProcesoBloquea2());
		}
		if (ataca3) {
			random= (int) (Math.random()*2);
			if(random==0)procesador3.anadirProceso(new ProcesoAtaca1());
			if(random==1)procesador3.anadirProceso(new ProcesoAtaca2());
			if(random==2)procesador3.anadirProceso(new ProcesoAtaca3());
		}
		if (bloquea3) {
			procesador3.anadirProceso(new ProcesoBloquea3());
		}

	}

	public void generarProcesadores() {
		ArrayList<RecursoSistema> arrayRecursos = new ArrayList<>();

		RecursoSistema cuerda = new RecursoSistema1(0, "Cuerda", 100);
		arrayRecursos.add(cuerda);

//		 procesoAtaca1 = new ProcesoAtaca1();
//		 procesoAtaca2 = new ProcesoAtaca2();
//		 procesoAtaca3 = new ProcesoAtaca3();
//
//		procesoAtaca1.setRecursoNecesario(0, 30);
//		procesoAtaca2.setRecursoNecesario(0, 20);
//		procesoAtaca3.setRecursoNecesario(0, 10);

		procesador1 = new ControladorHilo(arrayRecursos, 1);
		procesador2 = new ControladorHilo(arrayRecursos, 2);
		procesador3 = new ControladorHilo(arrayRecursos, 3);

		// procesador1.anadirProceso(proceso1);
		// procesador1.anadirProceso(proceso3);
		// //procesador1.anadirProceso(proceso1);
		// // procesador2.anadirProceso(proceso3);
		// procesador2.anadirProceso(proceso2);
		// // procesador2.anadirProceso(proceso1);

		// procesador3.anadirProceso(proceso1);
		// procesador3.anadirProceso(proceso3);

		procesador1.start();
		procesador2.start();
		procesador3.start();

	}

	public TextureRegion retornoFrame(float duracion) {// no puede ser void

		aataca1 = Gdx.input.isKeyPressed(Input.Keys.W);
		abloquea1 = Gdx.input.isKeyPressed(Input.Keys.S);
		// jugador 2
		aataca2 = Gdx.input.isKeyPressed(Input.Keys.UP);
		abloquea2 = Gdx.input.isKeyPressed(Input.Keys.DOWN);

		// jugador 3
		aataca3 = Gdx.input.isKeyPressed(Input.Keys.I);
		abloquea3 = Gdx.input.isKeyPressed(Input.Keys.K);
		
		//System.out.println(duracion);
		if (aataca1) {
			
			
			 frame = (TextureRegion) cercaJugador1.getKeyFrame(duracion, true);
			return frame;
		}
		if (aataca2) {
			
			 frame = (TextureRegion) cercaJugador2.getKeyFrame(duracion, true);
			return frame;
		}
		if (aataca3) {
			
			frame = (TextureRegion) cercaJugador3.getKeyFrame(duracion, true);
			return frame;
		}
		if (abloquea1) {
			
			frame = (TextureRegion) empate.getKeyFrame(duracion, true);
			return frame;
		}
		if (abloquea2) {
			
			frame = (TextureRegion) empate.getKeyFrame(duracion, true);
			return frame;
		}
		if (abloquea3) {
			
			frame = (TextureRegion) empate.getKeyFrame(duracion, true);
			return frame;
		}
		frame = (TextureRegion) empate.getKeyFrame(duracion, true);
		return frame;

	}

	public void animacionCentro() {
		TextureRegion[] lazoCentro = new TextureRegion[5];
		lazoCentro[0] = new TextureRegion(new Texture("Lazos/Lazo centrado.png"));
		lazoCentro[1] = new TextureRegion(new Texture("Lazos/Lazo centrado.png"));
		lazoCentro[2] = new TextureRegion(new Texture("Lazos/Lazo centrado.png"));
		lazoCentro[3] = new TextureRegion(new Texture("Lazos/Lazo centrado.png"));
		lazoCentro[4] = new TextureRegion(new Texture("Lazos/Lazo centrado.png"));
		empate = new Animation<>(0.1f, lazoCentro);// segundo y arreglo de frames
	}

	public void animacionCercaJugador1() {
		TextureRegion[] lazoCentro = new TextureRegion[5];
		lazoCentro[0] = new TextureRegion(new Texture("Lazos/Lazo 3.png"));
		lazoCentro[1] = new TextureRegion(new Texture("Lazos/Lazo 3.png"));
		lazoCentro[2] = new TextureRegion(new Texture("Lazos/Lazo 3.png"));
		lazoCentro[3] = new TextureRegion(new Texture("Lazos/Lazo 3.png"));
		lazoCentro[4] = new TextureRegion(new Texture("Lazos/Lazo 3.png"));
		cercaJugador1 = new Animation<>(0.8f, lazoCentro);// segundo y arreglo de frames
	}

	public void animacionCercaJugador2() {
		TextureRegion[] lazoCentro = new TextureRegion[5];
		lazoCentro[0] = new TextureRegion(new Texture("Lazos/Lazo 2.png"));
		lazoCentro[1] = new TextureRegion(new Texture("Lazos/Lazo 2.png"));
		lazoCentro[2] = new TextureRegion(new Texture("Lazos/Lazo 2.png"));
		lazoCentro[3] = new TextureRegion(new Texture("Lazos/Lazo 2.png"));
		lazoCentro[4] = new TextureRegion(new Texture("Lazos/Lazo 2.png"));
		cercaJugador2 = new Animation<>(0.8f, lazoCentro);// segundo y arreglo de frames
	}

	public void animacionCercaJugador3() {
		TextureRegion[] lazoCentro = new TextureRegion[5];
		lazoCentro[0] = new TextureRegion(new Texture("Lazos/Lazo 1.png"));
		lazoCentro[1] = new TextureRegion(new Texture("Lazos/Lazo 1.png"));
		lazoCentro[2] = new TextureRegion(new Texture("Lazos/Lazo 1.png"));
		lazoCentro[3] = new TextureRegion(new Texture("Lazos/Lazo 1.png"));
		lazoCentro[4] = new TextureRegion(new Texture("Lazos/Lazo 1.png"));
		cercaJugador3 = new Animation<>(0.8f, lazoCentro);// segundo y arreglo de frames
	}

	public void animacionGanaJugador1() {
		TextureRegion[] lazoCentro = new TextureRegion[5];
		lazoCentro[0] = new TextureRegion(new Texture("Lazos/Lazo 6.png"));
		lazoCentro[1] = new TextureRegion(new Texture("Lazos/Lazo 6.png"));
		lazoCentro[2] = new TextureRegion(new Texture("Lazos/Lazo 6.png"));
		lazoCentro[3] = new TextureRegion(new Texture("Lazos/Lazo 6.png"));
		lazoCentro[4] = new TextureRegion(new Texture("Lazos/Lazo 6.png"));
		ganaJugador1 = new Animation<>(3f, lazoCentro);// segundo y arreglo de frames
	}

	public void animacionGanaJugador2() {
		TextureRegion[] lazoCentro = new TextureRegion[5];
		lazoCentro[0] = new TextureRegion(new Texture("Lazos/Lazo 5.png"));
		lazoCentro[1] = new TextureRegion(new Texture("Lazos/Lazo 5.png"));
		lazoCentro[2] = new TextureRegion(new Texture("Lazos/Lazo 5.png"));
		lazoCentro[3] = new TextureRegion(new Texture("Lazos/Lazo 5.png"));
		lazoCentro[4] = new TextureRegion(new Texture("Lazos/Lazo 5.png"));
		ganaJugador2 = new Animation<>(3f, lazoCentro);// segundo y arreglo de frames
	}

	public void animacionGanaJugador3() {
		TextureRegion[] lazoCentro = new TextureRegion[5];
		lazoCentro[0] = new TextureRegion(new Texture("Lazos/Lazo 4.png"));
		lazoCentro[1] = new TextureRegion(new Texture("Lazos/Lazo 4.png"));
		lazoCentro[2] = new TextureRegion(new Texture("Lazos/Lazo 4.png"));
		lazoCentro[3] = new TextureRegion(new Texture("Lazos/Lazo 4.png"));
		lazoCentro[4] = new TextureRegion(new Texture("Lazos/Lazo 4.png"));
		ganaJugador3 = new Animation<>(3f, lazoCentro);// segundo y arreglo de frames
	}*/
}
