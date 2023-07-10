package TerminalEssentials;

import java.io.IOException;

import com.googlecode.lanterna.input.KeyType;

import terminal.TerminalBasico;
/**
 * Scanner que permite el registro de Strings
 */
public class TScanner {
	
	

	// ATRIBUTOS
	TerminalBasico tb = new TerminalBasico();
	
	private boolean escrituraHabilitar = true;

	private int escrituraFila1; // Delimitador de la fila1 en la escritura.
	private int escrituraColumna1; // Delimitador de la columna1 en la escritura.
	private int escrituraFila2; // Delimitador de la fila2 en la escritura.
	private int escrituraColumna2; /// Delimitador de la columna2 en la escritura.

	private int aux_escrituraFila1; // Variable de guardado para respaldar fila1
	private int aux_escrituraColumna1; // Variable de guardado para respaldar columna1

	private KeyType escrituraTeclaSalir; // Tecla para salir del bucle scanner
	private KeyType escrituraTeclaSalto; // Tecla para hacer un salto de linea
	private KeyType escrituraTeclaBorrar; // Tecla para borrar texto

	private String salidaScanner = ""; // Guarda el texto introducido (no guarda saltos de linea, que considera como
										// espacios)

	private boolean limiteFinalExcedido = false; // Detecta si ha llegado a la ultima linea, asignando excepciones al
													// retroceso y a la escritura
	private boolean retornoDesdeArriba = false; // Detecta si se esta realizando un retorno desde arriba, asignando
												// excepciones al retroceso.

	// MeTODO CONSTRUCTOR
	
	/**
	 * Crea un recuadro usando dos vertices (esquina superior izquierda y esquina inferior derecha) con los botones de salida definidos como:
	 * 
	 * - enter como salto de linea
	 * - retroceso como borrado de caracteres.
	 * - "fin" como fin de la ejecucion. 
	 * @param fila1
	 * @param columna1
	 * @param fila2
	 * @param columna2
	 * @throws IOException 
	 */
	
	
	public TScanner(int fila1, int columna1, int fila2, int columna2) throws IOException {
		
		tb.locateAt(fila1, columna1);
		
		this.escrituraFila1 = fila1;
		this.escrituraFila2 = fila2;
		this.escrituraColumna1 = columna1;
		this.escrituraColumna2 = columna2;

		this.escrituraTeclaBorrar = KeyType.Backspace;
		this.escrituraTeclaSalir = KeyType.End;
		this.escrituraTeclaSalto = KeyType.Enter;

		this.aux_escrituraColumna1 = columna1;
		this.aux_escrituraFila1 = fila1;

	}
	
	/**
	 * Solo permite la entrada de una linea, limitado a un numero de caracteres ( 0 - n )
	 * @param fila
	 * @param columna
	 * @param caracteres
	 * @throws IOException 
	 */

	public TScanner(int fila, int columna, int caracteres) throws IOException {
		
		tb.locateAt(fila, columna);

		this.escrituraFila1 = fila;
		this.escrituraFila2 = fila;
		this.escrituraColumna1 = columna;
		this.escrituraColumna2 = columna + caracteres;

		this.escrituraTeclaSalir = KeyType.Enter;
		this.escrituraTeclaBorrar = KeyType.Backspace;

		this.aux_escrituraColumna1 = columna;
		this.aux_escrituraFila1 = fila;

	}
	
	/**
	 * Configura todos los parametros del Scanner, permite customizar las teclas. 
	 * @param fila1
	 * @param columna1
	 * @param fila2
	 * @param columna2
	 * @param teclasalir
	 * @param teclasaltar
	 * @param teclaborrar
	 * @throws IOException 
	 */

	public TScanner(int fila1, int columna1, int fila2, int columna2, KeyType teclasalir, KeyType teclasaltar, KeyType teclaborrar) throws IOException {

		tb.locateAt(fila1, columna1);
		
		this.escrituraFila1 = fila1;
		this.escrituraFila2 = fila2;
		this.escrituraColumna1 = columna1;
		this.escrituraColumna2 = columna2;

		this.escrituraTeclaBorrar = teclaborrar;
		this.escrituraTeclaSalir = teclasalir;
		this.escrituraTeclaSalto = teclasaltar;

		this.aux_escrituraColumna1 = columna1;
		this.aux_escrituraFila1 = fila1;

	}

	/**
	 * Funcion privada que registra los caracteres introducidos, y actualiza pantalla cada vez que se detectan pulsaciones.
	 * 
	 * @throws IOException
	 */

	private void escribirCaracter() throws IOException {

		com.googlecode.lanterna.input.KeyStroke ks;
		ks = tb.readRawKey();

		tb.locateAt(escrituraFila1, escrituraColumna1);

		if (ks.getKeyType().equals(escrituraTeclaSalir)) {
			escrituraHabilitar = false;
		}

		if (ks.getKeyType().equals(KeyType.Character) && escrituraFila1 <= escrituraFila2) {

			if (limiteFinalExcedido == false) {
				tb.printStringAt(escrituraFila1, escrituraColumna1, ks.getCharacter() + "");
				tb.show();
				salidaScanner = salidaScanner + ks.getCharacter();
			}

		} else if (ks.getKeyType().equals(escrituraTeclaSalto)) {

			if (escrituraFila1 < escrituraFila2) {

				escrituraFila1++;
				escrituraColumna1 = aux_escrituraColumna1 - 1;
				tb.locateAt(escrituraFila1, escrituraColumna1);
				tb.show();
				salidaScanner = salidaScanner + "\n";

			}

		} else if (ks.getKeyType().equals(escrituraTeclaBorrar)) {

			if (salidaScanner.length() > 0)
				salidaScanner = salidaScanner.substring(0, salidaScanner.length() - 1);

			if (escrituraColumna1 == aux_escrituraColumna1 && escrituraFila1 == aux_escrituraFila1) {
				tb.printStringAt(escrituraFila1, escrituraColumna1, " ");
				tb.locateAt(escrituraFila1, escrituraColumna1--);
				tb.show();
			} else if (escrituraColumna1 == aux_escrituraColumna1) {
				escrituraFila1--;
				escrituraColumna1 = escrituraColumna2;
				escrituraColumna1++;
				tb.locateAt(escrituraFila1, escrituraColumna1);
				retornoDesdeArriba = true;
				tb.show();
			}

			else if (limiteFinalExcedido == true) {
				tb.printStringAt(escrituraFila1, escrituraColumna1, " ");
				tb.locateAt(escrituraFila1, escrituraColumna1--);
				tb.show();
				limiteFinalExcedido = false;
			} else {
				escrituraColumna1--;
				tb.printStringAt(escrituraFila1, escrituraColumna1, " ");
				tb.locateAt(escrituraFila1, escrituraColumna1--);
				tb.show();
				retornoDesdeArriba = true;
			}

		}

	}
	
	/**
	 * Bucle que lleva a cabo funciones estructurales del texto no relacionadas con la pulsacion de las teclas, y continua
	 * hasta que se detecta la finalizacion de la ejecucion. 
	 * @throws IOException
	 */

	public void escanear() throws IOException {

		tb.show();
		while (escrituraHabilitar == true) {

			if (escrituraColumna1 > escrituraColumna2) {

				if (escrituraFila1 >= escrituraFila2) {
					escrituraColumna1--;
					tb.locateAt(escrituraFila1, escrituraColumna1 - 1);
					limiteFinalExcedido = true;
				} else if (retornoDesdeArriba == true) {
					retornoDesdeArriba = false;
				} else {
					escrituraFila1++;
					escrituraColumna1 = aux_escrituraColumna1;
				}

			}

			escribirCaracter();

			if (escrituraFila1 <= escrituraFila2)
				escrituraColumna1++;

		}

	}

	public String salida() {
		return salidaScanner;
	}
	

	/**
	 * Crea un recuadro usando dos vertices (esquina superior izquierda y esquina inferior derecha) con los botones de salida definidos como:
	 * 
	 * - enter como salto de linea
	 * - retroceso como borrado de caracteres.
	 * - "fin" como fin de la ejecucion. 
	 * @param fila1
	 * @param columna1
	 * @param fila2
	 * @param columna2
	 * @throws IOException 
	 */
	
	public void configurar (int fila1, int columna1, int fila2, int columna2) throws IOException {
		
		tb.locateAt(fila1, columna1);
		
		this.escrituraHabilitar=true;
		
		this.escrituraFila1 = fila1;
		this.escrituraFila2 = fila2;
		this.escrituraColumna1 = columna1;
		this.escrituraColumna2 = columna2; 
		
		this.escrituraTeclaBorrar = KeyType.Backspace;
		this.escrituraTeclaSalir = KeyType.End;
		this.escrituraTeclaSalto = KeyType.Enter;
		
		this.aux_escrituraColumna1 = columna1;
		this.aux_escrituraFila1 = fila1; 
	}
	
	/**
	 * Solo permite la entrada de una linea, limitado a un numero de caracteres ( 0 - n )
	 * @param fila
	 * @param columna
	 * @param caracteres
	 * @throws IOException 
	 */
	
	public void configurar (int fila, int columna ,int caracteres) throws IOException {
		
		tb.locateAt(fila, columna);
		
		this.escrituraHabilitar=true;
		
		this.escrituraFila1 = fila;
		this.escrituraFila2 = fila;
		this.escrituraColumna1= columna;
		this.escrituraColumna2 = columna+caracteres;
		
		this.escrituraTeclaSalir = KeyType.Enter;
		this.escrituraTeclaBorrar = KeyType.Backspace;
		
		this.aux_escrituraColumna1 = columna;
		this.aux_escrituraFila1 = fila;
		
	}
	
	/**
	 * Configura todos los parametros del Scanner, permite customizar las teclas. 
	 * @param fila1
	 * @param columna1
	 * @param fila2
	 * @param columna2
	 * @param teclasalir
	 * @param teclasaltar
	 * @param teclaborrar
	 * @throws IOException 
	 */
	
	public void configurar (int fila1, int columna1, int fila2, int columna2, KeyType teclasalir, KeyType teclasaltar, KeyType teclaborrar) throws IOException {
		
		tb.locateAt(fila1, columna1);
		
		this.escrituraHabilitar=true;
		
		this.escrituraFila1 = fila1;
		this.escrituraFila2 = fila2;
		this.escrituraColumna1 = columna1;
		this.escrituraColumna2 = columna2; 
		
		this.escrituraTeclaBorrar = teclaborrar;
		this.escrituraTeclaSalir = teclasalir;
		this.escrituraTeclaSalto = teclasaltar;
		
		this.aux_escrituraColumna1 = columna1;
		this.aux_escrituraFila1 = fila1; 
		
	}
	
	public void borrarEntrada () throws IOException {
		
		
		for (int i=escrituraFila1; i<escrituraFila2; i++) {
			
			for (int j=escrituraColumna1; j<escrituraColumna2; j++) {
				
				tb.printStringAt(i, j, " ");
				
			}					
		}	
	}

}
