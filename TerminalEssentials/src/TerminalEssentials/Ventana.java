package TerminalEssentials;

import java.io.IOException;

import terminal.TerminalBasico;
/**
 * Ventanas o recuadros con o sin titulo. 
 */
public class Ventana  {
	
	

	protected int fila1; 
	protected int columna1; 
	protected int fila2; 
	protected int columna2;
	protected String titulo; 
	
	/**
	 * Crea un recuadro o cuadrado sin texto, deben especificarse las coordenadas XY XY
	 * @param fila1
	 * @param columna1
	 * @param fila2
	 * @param columna2
	 */
	

	public Ventana (int fila1, int columna1, int fila2, int columna2) {
		this.fila1 = fila1; 
		this.columna1 = columna1;
		this.fila2 = fila2;
		this.columna2 = columna2;
	}
	
	/**
	 * Crea una ventana con texto. Deben especificarse las coordenadas XY XY 
	 * @param fila1
	 * @param columna1
	 * @param fila2
	 * @param columna2
	 * @param titulo
	 */
	

	public Ventana (int fila1, int columna1, int fila2, int columna2, String titulo) {
		
		this.fila1 = fila1; 
		this.columna1 = columna1;
		this.fila2 = fila2;
		this.columna2 = columna2;
		this.titulo = titulo;
	
		
	}
	
	/**
	 * Crea una ventana que cubre la totalidad del ancho y alto de la pantalla
	 * @param titulo
	 */
	
	public Ventana (String titulo) {
		TerminalBasico tb = new TerminalBasico();
		this.fila1 =1;
		this.columna1 =0;
		this.fila2= tb._MAXFIL;
		this.columna2=tb._MAXCOL;
		this.titulo = titulo;
		
	}
	
	/**
	 * Crea un recuadro que cubre la totalidad del ancho y alto de la pantalla
	 */
	
	public Ventana () {
		
		TerminalBasico tb = new TerminalBasico();
		this.fila1 =1;
		this.columna1 =0;
		this.fila2= tb._MAXFIL;
		this.columna2=tb._MAXCOL;
		
	}
	
	// MÉTODOS 
	
	/** 
	 * Muestra la ventana. 
	 * @throws IOException
	 */

	public void mostrar () throws IOException {  
		
		TerminalBasico tb = new TerminalBasico();
		
		if (titulo != null) {


			// L�NEA SUPERIOR E INFERIOR DEL TITULO
			
			for (int i=columna1; i<columna2+1; i++) {
				tb.locateAt(fila1, i);
				tb.printString("*");
			}
			
			// LINEAS HORIZONTALES 
			
			tb.printStringAt(fila1+1, columna1, "*");	
			tb.printStringAt (fila1+1, columna2, "*");
			
			// Imprime título y los carácteres especiales de minimizar, reducir y eliminar
			
			tb.locateAt(fila1+1, columna1+2);
			tb.printString(titulo);
			tb.locateAt(fila1+1, columna2-7);
		
			
			fila1 = fila1+2;
			fila2 = fila2+2;
			
		}
		
		// L�NEA SUPERIOR e inferior. 
		
		for (int i=columna1; i<columna2; i++) {
			tb.locateAt(fila1, i);
			tb.printString("*");
			tb.locateAt(fila2, i);
			tb.printString("*");
		}
		
		tb.printString("*"); // L�NEA extra para cuadrar el texto. 
		
		// L�NEA LATERAL
		
		for (int i=fila1; i<fila2; i++) {
			tb.locateAt(i, columna1);
			tb.printString("*");
			tb.locateAt(i, columna2);
			tb.printString("*");
		}
		
	}
	
	/**
	 * Borra la ventana.
	 * @throws IOException
	 */
	
	public void borrar () throws IOException {
		TerminalBasico tb = new TerminalBasico();
		
		if (titulo==null) {
			
			for (int i=fila1; i<fila2; i++) {
				
				for (int j=columna1; j<columna2; j++) {
					
					tb.printStringAt(i, j, " ");
					
				}					
			}	
		}
		else {
			
			for (int i=fila1-3; i<fila2-3; i++) {
				
				for (int j=columna1; j<columna2; j++) {
					
					tb.printStringAt(i, j, " ");
					
				}					
			}	
			
			
			
		}
		
		
		
	}
		
	
	}

