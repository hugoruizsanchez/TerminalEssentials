package TerminalEssentials;

import java.io.IOException;

import com.googlecode.lanterna.TextColor;

import terminal.TerminalBasico;




/**
 * Clase usada de forma interna por la clase menu. 
 */
public class Boton {
	


	
	TerminalBasico tb = new TerminalBasico();	
	
	// ATRIBUTOS
	
	protected TextColor colorFondo;
	protected TextColor colorTexto;
	
	protected String titulo;
	protected int fila;
	protected int columna;
	
	// GET Y SET 
	
	public TextColor getColorFondo() {
		return colorFondo;
	}

	public void setColorFondo(TextColor colorFondo) {
		this.colorFondo = colorFondo;
	}

	public TextColor getColorTexto() {
		return colorTexto;
	}

	public void setColorTexto(TextColor colorTexto) {
		this.colorTexto = colorTexto;
	}
	
	// CONSTRUCTOR
	
	
	protected Boton () {
		
	}
	


	/**
	 * Cada boton debe contener titulo, fila y columna donde se ubicaran, el color de fuente y fondo que tendran.
	 * @param titulo
	 * @param fila
	 * @param columna
	 * @param colorTexto
	 * @param colorFondo
	 */
	
	public Boton (String titulo, int fila, int columna, TextColor colorTexto, TextColor colorFondo) {
		
		this.titulo = titulo;
		this.fila = fila;
		this.columna = columna; 
		this.colorTexto = colorTexto;
		this.colorFondo = colorFondo;
		
	}
	
	// MeTODOS
	
	/**
	 * El papel y el color se modifican en base a la peticion del usuario - depende del fondo que tenga - y cambiando el texto por espaciados en blanco
	 * @param color
	 * @throws IOException
	 */
	
	public void ocultar (TextColor color) throws IOException {
		tb.paper(color);
		tb.ink(color);
		tb.printStringAt(fila, columna, " ".repeat(titulo.length()));
		
	}
	
	/**
	 * Muestra el boton segun las propiedades especificadas. 
	 * @throws IOException
	 */
	
	public void mostrar () throws IOException {
		
		tb.ink(colorTexto);
		tb.paper(colorFondo);
		tb.printStringAt(fila, columna, titulo);	
		
	}
	
	/**
	 * La seleccion de texto o marcado, cuando el texto esta marcado invertira los colores originales indicados en el constructor.
	 * @throws IOException
	 */
	
	public void seleccionar () throws IOException {
			
		ocultar (tb._BLUE);
		
		tb.ink(colorFondo);
		tb.paper(colorTexto);
		tb.printStringAt(fila, columna, titulo);		

	}
	
	/**
	 * Funcion no implementada en el menu vertical, dado que no tenia uso. Sirve para posicionar una flecha sobre el boton a medida que el usuario selecciona en el menu. 
	 * @throws IOException
	 */
	
	public void flechar () throws IOException {
		
		tb.ink(colorTexto);
		tb.paper(colorFondo);
		tb.printStringAt(fila-3, columna+(titulo.length()/2-1), "|");
		tb.printStringAt(fila-2, columna+(titulo.length()/2-1), "|");
		tb.printStringAt(fila-1, columna+(titulo.length()/2-1), "V");
	}
	
	/**
	 * Desflechar, es decir, borrar la flecha. 
	 * @throws IOException
	 */
	
	public void desflechar () throws IOException {
		
		tb.paper(colorFondo);
		tb.ink(colorFondo);
		
		tb.printStringAt(fila-3, columna+(titulo.length()/2-1), " ");
		tb.printStringAt(fila-2, columna+(titulo.length()/2-1), " ");
		tb.printStringAt(fila-1, columna+(titulo.length()/2-1), " ");
		
	}
	

	
	
	
	
	

}
