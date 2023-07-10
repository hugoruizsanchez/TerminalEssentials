package TerminalEssentials;
import java.io.IOException;

import com.googlecode.lanterna.input.KeyType;

import terminal.TerminalBasico;
/**
 *  Cursor desplazable con las flechas que permite la visualizacion de las filas y columnas en que se divide el terminal 
 */
public class modoDesarrollador {
	
	
	
	// ATRIBUTOS 
	
	public TerminalBasico tb = new TerminalBasico();
	public com.googlecode.lanterna.input.KeyStroke ks;
	public Sprite sprite = new Sprite (1,1);
	
	// CONSTRUCTOR 
	
	public modoDesarrollador () {
		
	}
	
	// MÉTODO
	
	/**
	 * Muestra fila y columna actual a partir de un desplazamiento por cursor a partir de un sprite.  
	 * @throws IOException
	 */
	
	public void iniciar () throws IOException {
		
		sprite.getForma().add("*");
		sprite.mostrar();
		tb.show();
		
		do{
			ks = tb.readRawKey();	// Lea la entrada ... 
			if (ks.getKeyType().equals(KeyType.ArrowDown))  sprite.moverAbajo(1); 
			if (ks.getKeyType().equals(KeyType.ArrowUp))  sprite.moverArriba(1);
			if (ks.getKeyType().equals(KeyType.ArrowLeft))  sprite.moverIzquierda(1);
			if (ks.getKeyType().equals(KeyType.ArrowRight)) sprite.moverDerecha(1);
			
			sprite.mostrar();
		
		tb.printStringAt(25, 25, "Fila: "+sprite.getFila()+"  "+"Columna: "+sprite.getColumna()+"  ");
		tb.show();
		} while (!ks.getKeyType().equals(KeyType.Enter)); //... mientras no sea ENTER.		
		
	}
	
	

}
