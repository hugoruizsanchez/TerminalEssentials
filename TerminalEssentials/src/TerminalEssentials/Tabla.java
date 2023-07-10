package TerminalEssentials;

import java.io.IOException;

import terminal.TerminalBasico;

/**
 * Tabla dinamica en cuanto a longitud horizontal de registros. 
 */

public class Tabla {
	
	// Atributos
	
	protected int filasTabla;
	protected int columnasTabla;
	
	protected int fila; 
	protected int columna; 
	
	protected String titulo;
	
	protected String [][] contenido = new String [filasTabla][columnasTabla];

	// Constructor
	
	private TerminalBasico tb = new TerminalBasico();
	
	public Tabla(int filasTabla, int columnasTabla, int fila, int columna, String[][] contenido) {
		this.filasTabla = filasTabla;
		this.columnasTabla = columnasTabla;
		this.fila = fila;
		this.columna = columna;
		this.contenido = contenido;
	}
	
	// Métodos
	
	/** 
	 * Extrae el texto mas largo de una columna, precisa la longitud para facilitar que la tabla sea dinamica. 
	 * @param columna
	 * @return
	 */
	
	protected int textoMasLargo (int columna) {
		
		int salida =0;
		
		for (int i=0; i<contenido.length; i++) {
			
			for (int j=0; j<columnasTabla; j++) {
				
				if (contenido [i] [columna].length()>salida) salida = contenido [i] [columna].length();
				
			}
			
		}
		
		return salida+3;
		
	}
	
	/**
	 * Concatena los resultados de la columna mas larga. 
	 * @param columna
	 * @return
	 */
	
	protected int concatenarTextoMasLargo (int columna) {
		
		int salida =0;
		
		for (int i=columna; i>-1; i--) {
			salida = salida + textoMasLargo (i);
		}

		return salida;
	}
	
	/**
	 * Muestreo de la tabla. 
	 * @throws IOException
	 */
	
	public void mostrar () throws IOException {
		
		// Encabezado de la tabla
		
		for (int i=0; i<columnasTabla;i++) {
			tb.printStringAt(fila, columna+concatenarTextoMasLargo(i-1), "| "+contenido [0] [i]);
			
		}
		
		
		// Cuerpo de la tabla 
		
	
		for (int i=1; i<filasTabla; i++) {
			
			for (int j=0; j<columnasTabla; j++) {
				
				tb.printStringAt(fila+(i+1), columna+concatenarTextoMasLargo (j-1), "| "+contenido [i] [j]);
				
			}
			
		}
		
		// Adornos de la tabla
		
		for (int i=0; i<columnasTabla;i++) {
			tb.printStringAt(fila+1, columna, "|"+"-".repeat(concatenarTextoMasLargo(i)-1)+"|");
			tb.printStringAt(fila-1, columna, "|"+"-".repeat(concatenarTextoMasLargo(i)-1)+"|");
		}
		
		for (int i=0; i<columnasTabla-1; i++) {
			tb.printStringAt(fila+1, columna+concatenarTextoMasLargo(i), "+");
			tb.printStringAt(fila-1, columna+concatenarTextoMasLargo(i), "-");
		}
		
		for (int i=0; i<filasTabla+1; i++) {
			tb.printStringAt(fila+i, columna+concatenarTextoMasLargo(columnasTabla-1), "|");
		}
		
		
		if (filasTabla>1)tb.printStringAt(fila+filasTabla+1, columna,"|"+ "-".repeat(concatenarTextoMasLargo(columnasTabla-1)-1)+"|");

		
		
	}
	

	/* Ejemplo de uso. 
	String [] [] empleados = 
				{
					{"Nombre", "Puesto", "Sueldo"},
		            {"Hugo Ruin", "Muchimillonario", "Infinito"},
		            {"Óscar Ruiz", "asies", "-99999"},
		            {"Alex Jones", "Periodista", "35,000"},
		            {"Hank Scorpio", "Genio del mal", "100,000"},
		            {"Michael Jackson", "Cantante", "100,000,000"}							
				};
			
			Tabla prueba = new Tabla (empleados.length, empleados[0].length, 5,5, empleados);

			prueba.mostrar();
	 */
	
	
	


}
