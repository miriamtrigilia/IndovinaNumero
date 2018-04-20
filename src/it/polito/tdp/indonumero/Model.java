package it.polito.tdp.indonumero;

import java.security.InvalidParameterException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Model {
	
	private int NMAX = 100;
	private int TMAX = 7; // ogni tentativo butto via (log in base 2 di 100)

	
	private int segreto; // numero da indovinare
	// private int tentativi; // tentativi già fatti
	private IntegerProperty tentativi = new SimpleIntegerProperty(); // intero OSSERVABILE.. !!SIMPLE!!
	
	private boolean inGame = false; // c'è una partita in corso?

	public Model() {
		this.inGame = false; // inizializzazione
	}

	/**
	 * Avvia una nuova partita, generando un nuovo numero segreto.
	 */
	public void newGame() {
		
		this.segreto = (int) (Math.random()*NMAX +1);  // mi da un double compreso tra 0 e 100 (escluso) -> lo trasformo in int e aggiungo 1
	   	this.tentativi.set(0); // aggiorna tutti gli osservatori (le altre classi)
	    this.inGame = true; // è iniziata la partita
		
	}
	 
	/**
	 * Fai un tentativo di indovinare il numero segreto 
	 * @param t valore numerico del tentativo
	 * @return 0 se è indovinato, +1 se è troppo grande, -1 se è troppo piccolo
	 */
	public int tentativo(int t) {
		
		if(!inGame) {
			throw new IllegalStateException ("Partita non attiva");
		}
		
		if(!valoreValido(t)) {
			throw new InvalidParameterException("Tentativo fuori range");
		}
		
		this.tentativi.set(this.tentativi.get()+1);
		
		if(this.tentativi.get() == this.TMAX) 
			this.inGame = false;
		
		if(t == this.segreto) {
			this.inGame = false;
			return 0;
		}
		if( t< this.segreto)
			return -1;
		return +1;
			
	}
	
	/**
	 * Controlla se il tentativo fornito rispoetta le regole formali
	 * del gioco, cioè è nel range [1, NMAX]
	 * @param tentativo
	 * @return {@code true} se il tentatico è valido
	 */
	
	public boolean valoreValido(int tentativo) { // funzione apposita.. PIU' FUNZIONALE
		return tentativo>=1 && tentativo <= this.NMAX;
	}
	
	public boolean isInGame() {
		return inGame;
	}

	public int getNMAX() {
		return NMAX;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	public int getSegreto() {
		return this.segreto;
	}

	public final IntegerProperty tentativiProperty() { // restituisce la proprerty come oggetto
		return this.tentativi;
	}
	
	// get e set fanno si che la property dall'esterno sia invisibile, vedo solo un int tentativi.
	
	public final int getTentativi() {
		return this.tentativiProperty().get();
	}
	
	/*
	public final void setTentativi(final int tentativi) {
		this.tentativiProperty().set(tentativi);
	}
	* è una property di solo lettura
	*/
	

}
