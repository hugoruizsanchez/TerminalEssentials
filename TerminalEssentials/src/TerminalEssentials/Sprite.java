package TerminalEssentials;

import java.io.IOException;
import java.util.ArrayList;

import com.googlecode.lanterna.TextColor;

import terminal.TerminalBasico;
/**
 * Conjunto de caracteres desplazables que forman un elemento en pantalla, para una mas sencilla manipulacion de animaciones o juegos
 */
public class Sprite {
	
	
	
	// ATRIBUTOS
	
	protected TerminalBasico tb = new TerminalBasico();
	
	protected ArrayList <String> forma = new ArrayList(); 
	
	protected int fila; // Parametros para comenzar a imprimir 
	protected int columna; 
	
	private int columnaLimite = 0; // Guarda la mayor longitud de cada sprite, lo que permite delimitarlas en el cuadro. 
	
	protected ArrayList <Integer []> filasColumnasOcupadas = new ArrayList(); // Cada integer [2] guarda
	// filas y columnas donde hay bits de la forma. 
	
	// CONSTRUCTOR
	
	/**
	 * Fila y columna donde se imprimira el sprite. 
	 * @param fila
	 * @param columna
	 */
	
	public Sprite (int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}
	
	public Sprite() {
	
	}

	public ArrayList<String> getForma() {
		return forma;
	}

	public void setForma(ArrayList<String> forma) {
		this.forma = forma;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}
	
	public ArrayList<Integer[]> getFilasColumnasOcupadas() {
		return filasColumnasOcupadas;
	}
	
	public void setFilasColumnasOcupadas(ArrayList<Integer[]> filasColumnasOcupadas) {
		this.filasColumnasOcupadas = filasColumnasOcupadas;
	}
	
	// METODOS
	
	/**
	 * Metodo para mostrar el sprite, imprimiendolo en pantalla. 
	 * @throws IOException
	 */
	
	public void mostrar () throws IOException {
		
		for (int i=0; i<forma.size(); i++) {
			tb.printStringAt(fila+i, columna, forma.get(i));	
			
			for (int j=0; j<forma.get(i).length(); j++) {			
				filasColumnasOcupadas.add(new Integer [] {fila+i, columna+j});	// Guarda las coordenadas de cada una de las columnas asociadas a la fila dentro de los bits ocupados
			}
			
			if (forma.get(i).length()>columnaLimite) columnaLimite = forma.get(i).length(); // determinar la longitud maxima y asi saber las colisiones. 
				
		}	
		
	}
	
	/**
	 * Metodo para borrar el sprite, sustituyendo el espacio que ocupaba por espacios en blanco. 
	 * @throws IOException
	 */
	
	public void borrar () throws IOException {
		
		for (int i=0; i<forma.size(); i++) {
			tb.printStringAt(fila+i, columna, " ".repeat(forma.get(i).length()));	
			filasColumnasOcupadas.clear();
		}			
		
	}
	
	
		// METODOS DE MOVIMIENTO -> POR DEFETO. LIIM
	
	/**
	 * Mover a la derecha limitado a  los bordes por defecto.
	 * @param puntos
	 * @throws IOException
	 */
	
	public void moverDerecha (int puntos) throws IOException {
		
		borrar ();
		
		if (columna+columnaLimite < tb._MAXCOL) columna = columna + puntos;
		
	}
	
	/**
	 * Mover a la izquierda limitado a  los bordes por defecto.
	 * @param puntos
	 * @throws IOException
	 */
	
	public void moverIzquierda (int puntos) throws IOException {
		
		borrar ();
	    if (columna >=1) columna = columna - puntos;
		
	}
	
	/**
	 * Mover arriba limitado a  los bordes por defecto.
	 * @param puntos
	 * @throws IOException
	 */
	
	public void moverArriba (int puntos) throws IOException {
		borrar ();
		if (fila >=1)  fila = fila - puntos;
	}
	
	/**
	 * Mover abajo limitado a  los bordes por defecto.
	 * @param puntos
	 * @throws IOException
	 */
	
	public void moverAbajo (int puntos) throws IOException {
		borrar ();
		
		if (fila+forma.size() < tb._MAXFIL) fila = fila + puntos;
		
	}
	
	/**
	 * Mover a la derecha limitado a  los bordes definidos por el parametro. 
	 * @param puntos
	 * @param limite
	 * @throws IOException
	 */
	
	
	public void moverDerecha (int puntos, int limite) throws IOException {
		
		borrar ();
		
		if (columna+columnaLimite < limite) columna = columna + puntos;
		
	}
	
	/**
	 * Mover a la izquierda limitado a los bordes deifnidos por el parametro. 
	 * @param puntos
	 * @param limite
	 * @throws IOException
	 */
	
	public void moverIzquierda (int puntos, int limite) throws IOException {
		
		borrar ();
	    if (columna >=limite) columna = columna - puntos;
		
	}
	
	/**
	 * Mover arriba limitado a  los bordes definidos por el parametro. 
	 * @param puntos
	 * @param limite
	 * @throws IOException
	 */
	
	public void moverArriba (int puntos, int limite) throws IOException {
		borrar ();
		if (fila >=limite)  fila = fila - puntos;
	}

	/**
	 * Mover a la abajo limitado a  los bordes definidos por el parametro. 
	 * @param puntos
	 * @param limite
	 * @throws IOException
	 */
	
	public void moverAbajo (int puntos, int limite) throws IOException {
		borrar ();
			
		if (fila+forma.size() < limite) fila = fila + puntos;
		
	}
	
	
	/** 
	 * Movimiento diagonal arriba derecha
	 * @param puntos
	 * @throws IOException
	 */
	
	public void moverArribaDerecha (int puntos) throws IOException {
		borrar ();
		moverArriba (puntos);
		moverDerecha (puntos);
	}
	
	/** 
	 * Movimiento diagonal abajo derecha
	 * @param puntos
	 * @throws IOException
	 */
	
	public void moverAbajoDerecha (int puntos) throws IOException {
		borrar ();
		moverAbajo (puntos);
		moverDerecha (puntos);
	}
	
	/** 
	 * Movimiento diagonal arriba izquierda
	 * @param puntos
	 * @throws IOException
	 */
	
	public void moverArribaIzquierda (int puntos) throws IOException {
		borrar ();
		moverIzquierda (puntos);
		moverArriba (puntos);
	}
	
	/** 
	 * Movimiento diagonal abajo izquierda
	 * @param puntos
	 * @throws IOException
	 */
	
	public void moverAbajoIzquierda (int puntos) throws IOException {
		borrar ();
		moverIzquierda (puntos);
		moverAbajo (puntos);
	}
	
	
	
	/** 
	 * Movimiento diagonal arriba derecha con modificacion de limite
	 * @param puntos
	 * @throws IOException
	 */
	
	public void moverArribaDerecha (int puntos, int limitef, int limitec) throws IOException {
		borrar ();
		moverArriba (puntos, limitef);
		moverDerecha (puntos, limitec);
	}
	
	/** 
	 * Movimiento diagonal abajo derecha con modificacion de limite
	 * @param puntos
	 * @throws IOException
	 */
	
	public void moverAbajoDerecha (int puntos, int limitef, int limitec) throws IOException {
		borrar ();
		moverDerecha (puntos, limitef);
		moverAbajo (puntos, limitec);
	
	}
	
	/** 
	 * Movimiento diagonal arriba izquierda con modificacion de limite
	 * @param puntos
	 * @throws IOException
	 */
	
	public void moverArribaIzquierda (int puntos, int limitef, int limitec) throws IOException {
		borrar ();
		moverIzquierda (puntos, limitef);
		moverArriba (puntos, limitec);

		
	}
	
	/** 
	 * Movimiento diagonal abajo izquierda con modificacion de limite
	 * @param puntos
	 * @throws IOException
	 */
	
	public void moverAbajoIzquierda (int puntos, int limitef, int limitec) throws IOException {
		borrar ();
		moverIzquierda (puntos, limitef);
		moverAbajo (puntos, limitec);

		
	}
	
	/**
	 * Al momento de usar mas sprites y programar las colisiones, al introducir una fila y una columna comprueba si ese pixel esta siendo ocupado por este sprite. 
	 * @param fila
	 * @param columna
	 * @return
	 */
	
	
	public boolean pixelOcupado (int fila, int columna) {
		
		Integer [] entrada = new Integer [2];
		entrada [0] = fila;
		entrada [1] = columna;
			
		
		for (int i=0; i<filasColumnasOcupadas.size(); i++) {
			
			if (filasColumnasOcupadas.get(i)[0] == fila && filasColumnasOcupadas.get(i)[1] == columna  ) {
				return true;
			}		
		}
		
		return false;
		
	}
	
	
	
	
	
	
	
	


	

}
