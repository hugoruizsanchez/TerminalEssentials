package TerminalEssentials;

import java.io.IOException;

import terminal.TerminalBasico;
/**
 * Texto que se imprime caracter a caracter en un intervalo de tiempo determinado. 
 */
public class textoProgresivo {

	
	
	// ATRIBUTOS
	
	protected String texto;
	protected int espera;
	protected int fila; 
	protected int columna; 
	
	protected TerminalBasico tb = new TerminalBasico();

	// CONSTRUCTOR
	
	public textoProgresivo (int fila, int columna ,String texto, int espera) {
		this.fila= fila;
		this.columna = columna; 
		this.texto = texto;
		this.espera= espera;
	}
	
	// MÉTODOS
	
	/**
	 * Espera, intervalo en que se interrumpe el flujo de ejecucion del programa. 
	 * @param ms
	 */
	
	public static void esperar(int ms) {
	    try {
	        Thread.sleep(ms); // Pausa la ejecución durante 1000 milisegundos (1 segundo)
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Muestreo del boton modoficando parametros de fila, columna y espera. 
	 * @param fila_
	 * @param columna_
	 * @param espera_
	 * @throws IOException
	 */
	
	public void mostrar (int fila_, int columna_, int espera_) throws IOException {
		
		for (int i=0; i<texto.length(); i++) {
			tb.printCharAt(fila_, columna_+i, texto.charAt(i));
			esperar (espera_);
			
		}
		
	}
	
	/**
	 * Muestreo del boton. 
	 * @throws IOException
	 */
	
	public void mostrar () throws IOException {
		
		for (int i=0; i<texto.length(); i++) {
			tb.printCharAt(fila, columna+i, texto.charAt(i));
			esperar (espera);
			tb.show();
			
		}
		
	}
	/**
	 * Muestreo del boton cuando termine la espera. 
	 * @param espera_
	 * @throws IOException
	 */
	
	public void borrar (int espera_) throws IOException {
		
		for (int i=0; i<texto.length(); i++) {
			tb.printCharAt(fila, columna+i, ' ');
			esperar (espera_);
			tb.show();
			
		}
		
	}
	
	/**
	 * Borrado del boton. 
	 * @throws IOException
	 */
	
	public void borrar () throws IOException {
		
		for (int i=0; i<texto.length(); i++) {
			tb.printCharAt(fila, columna+i, ' ');
			esperar (espera);
			tb.show();
		}
		
	}
	
	
	
	// MÉTODOS GET Y SET 

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public int getEspera() {
		return espera;
	}

	public void setEspera(int espera) {
		this.espera = espera;
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

	public TerminalBasico getTb() {
		return tb;
	}

	public void setTb(TerminalBasico tb) {
		this.tb = tb;
	}
	
	
	
	
}
