
package TerminalEssentials;
import java.io.IOException;
import java.util.*;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyType;

import terminal.TerminalBasico;

/**
 * Menu en disposicion horizontal, conformado por botones. 
 */
public class menuHorizontal {
	
	
	// ATRIBUTOS
	
	protected TerminalBasico tb = new TerminalBasico();

	public ArrayList <Boton> botones = new ArrayList(); // menu.boton.add... 
	
	// CONSTRUCTOR

	public menuHorizontal () {
		
	}
	
	/**
	 * Un menu que se mueve horizontalmente solo es un un arraylist de botones. El boton no es capaz de usarse sin un menu.
	 * @param botones
	 */
	
	public menuHorizontal (ArrayList <Boton> botones) {
		this.botones = botones;	
	}
	
	// MÉTODOS
	
	/*
	 * Ejemplo: 
	 * 
	 *  menu1.botones.add(new Boton ("Prueba 1", 20, 12, tb._WHITEB, tb._BLUEB));
		menu1.botones.add(new Boton ("Prueba 2", 20, 32, tb._WHITEB, tb._BLUEB));
		menu1.botones.add(new Boton ("Prueba 3", 20, 52, tb._WHITEB, tb._BLUEB));
		
		switch (menu1.eleccionMenu()) {
	 */
	
	/** 
	 * Arroja el numero de la eleccion realizada. 
	 * @return
	 * @throws IOException
	 */
	
	public int  eleccionMenu () throws IOException {
		
		// Inicializar botones 

		for (int i=0; i<botones.size(); i++) {
			botones.get(i).mostrar();
		}
		
		int contador =0;
		
		botones.get(contador).seleccionar();
	
		tb.show();
		com.googlecode.lanterna.input.KeyStroke ks;
		tb.show();
		do{
			ks = tb.readRawKey();	// Lea la entrada ... 
			if (ks.getKeyType().equals(KeyType.ArrowLeft)) {
				
				if (contador!=0) {
					contador--;
					botones.get(contador).seleccionar(); 
					botones.get(contador+1).mostrar();
					//botones.get(contador).flechar(); Opción adicional. 
					//botones.get(contador+1).desflechar();
				}					
			}
			
			if (ks.getKeyType().equals(KeyType.ArrowRight)) {
				
				if (contador!=botones.size()-1) {
					contador++;
					botones.get(contador).seleccionar();
					botones.get(contador-1).mostrar();
					//botones.get(contador).flechar();
					//botones.get(contador-1).desflechar();
				}		
			}
			
			
			
		tb.show();
		
		} while (!ks.getKeyType().equals(KeyType.Enter)); //... mientras no sea ENTER. 
			
		return contador;
	}
	
	/**
	 * Menu de seleccion multiple, siendo nOpcionProseguir la que permitira la continuacion.
	 * @param nOpcionProseguir
	 * @param colorTexto
	 * @param colorFondo
	 * @return
	 * @throws IOException
	 */
	
 public ArrayList<Integer>  eleccionMenu (int nOpcionProseguir, TextColor colorTexto, TextColor colorFondo) throws IOException {
	
	 	ArrayList <Integer> eleccion = new ArrayList ();
	 	boolean terminado = false;
		int contador =0;
		
		TextColor colorTextoAuxiliar = botones.get(contador).colorTexto;
		TextColor colorFondoAuxiliar = botones.get(contador).colorFondo;
	 	
	 	for (int i=0; i<botones.size(); i++) {
			botones.get(i).mostrar();
		}
				
		botones.get(contador).seleccionar();
	

		com.googlecode.lanterna.input.KeyStroke ks;
		tb.show();
		do{
			ks = tb.readRawKey();	// Lea la entrada ... 
			if (ks.getKeyType().equals(KeyType.ArrowLeft)) {
				
				if (contador!=0) {
					contador--;
					botones.get(contador).seleccionar(); 
					botones.get(contador+1).mostrar();
					//botones.get(contador).flechar(); Opción adicional. 
					//botones.get(contador+1).desflechar();
				}					
			}
			
			if (ks.getKeyType().equals(KeyType.ArrowRight)) {
				
				if (contador!=botones.size()-1) {
					contador++;
					botones.get(contador).seleccionar();
					botones.get(contador-1).mostrar();
					//botones.get(contador).flechar();
					//botones.get(contador-1).desflechar();
				}		
			}
					
			
		tb.show();
		
		if (ks.getKeyType().equals(KeyType.Enter) && contador != nOpcionProseguir && !eleccion.contains(contador)) {
			eleccion.add(contador);
			botones.get(contador).colorFondo = colorFondo;
			botones.get(contador).colorTexto = colorTexto;
			botones.get(contador).seleccionar();
			tb.show();
		}
		
		else if (ks.getKeyType().equals(KeyType.Enter) && contador != nOpcionProseguir && eleccion.contains(contador)) {
			for (int i=0; i<eleccion.size(); i++) {
				if (eleccion.get(i) == contador) {
					eleccion.remove(i);
				}
			}
			botones.get(contador).colorFondo = colorFondoAuxiliar;
			botones.get(contador).colorTexto = colorTextoAuxiliar;
			botones.get(contador).seleccionar();
			tb.show();
			
		}
		
		if (ks.getKeyType().equals(KeyType.Enter) && contador == nOpcionProseguir) {
			terminado = true;
		}
		
		} while (terminado == false); //... mientras no sea ENTER. 
		return eleccion;
	 	
		
	}
	
	
	


}
