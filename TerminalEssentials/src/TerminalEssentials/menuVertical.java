
package TerminalEssentials;
import java.io.IOException;
import java.util.*;

import com.googlecode.lanterna.input.KeyType;

import terminal.TerminalBasico;

/**
 * Menu en disposicion vertical, conformado por botones
 */
public class menuVertical {
	
	
	
	// ATRIBUTOS
	
	protected TerminalBasico tb = new TerminalBasico();
	public ArrayList <Boton> botones = new ArrayList(); // menu.boton.add... 
	
	
	// CONSTRUCTOR

	public menuVertical () {
		
	}
	
	/**
	 * Un menu que se mueve horizontalmente solo es un un arraylist de botones. El boton no es capaz de usarse sin un menú.
	 * @param botones
	 */
	
	public menuVertical (ArrayList <Boton> botones) {
		this.botones = botones;	
	}
	
	// MÉTODOS
	
	
	/** 
	 * Arroja el numero de la eleccion realizada. 
	 * @return
	 * @throws IOException
	 */
	
	public int  eleccionMenu () throws IOException, InterruptedException {
		
		// Inicializar botones 

		for (int i=0; i<botones.size(); i++) {
			botones.get(i).mostrar();
		}
		
		int contador =0;
		
		botones.get(contador).seleccionar();
	
		tb.show();
		com.googlecode.lanterna.input.KeyStroke ks;
	
		
		do{
			
			ks = tb.readRawKey();	// Lea la entrada ... 
			if (ks.getKeyType().equals(KeyType.ArrowUp)) {
				
				if (contador!=0) {
					contador--;
					botones.get(contador).seleccionar(); 
					botones.get(contador+1).mostrar();
				}					
			}
			
			if (ks.getKeyType().equals(KeyType.ArrowDown)) {
				
				if (contador!=botones.size()-1) {
					contador++;
					botones.get(contador).seleccionar();
					botones.get(contador-1).mostrar();
				}		
			}
			
		tb.show();
		
		} while (!ks.getKeyType().equals(KeyType.Enter)); //... mientras no sea ENTER. 
			
		return contador;
	}
	


}
