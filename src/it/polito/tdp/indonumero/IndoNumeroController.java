/**
 * Sample Skeleton for 'IndoNumero.fxml' Controller Class
 */

package it.polito.tdp.indonumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class IndoNumeroController {
	
	private int NMAX = 100;
	private int TMAX = 7; // ogni tentativo butto via (log in base 2 di 100)

	private int segreto; // numero da indovinare
	private int tentativi; // tentativi già fatti

	private boolean inGame = false; // c'è una partita in corso?

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnNuova"
    private Button btnNuova; // Value injected by FXMLLoader

    @FXML // fx:id="txtCur"
    private TextField txtCur; // Value injected by FXMLLoader

    @FXML // fx:id="txtMax"
    private TextField txtMax; // Value injected by FXMLLoader

    @FXML // fx:id="boxGioco"
    private HBox boxGioco; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativo"
    private TextField txtTentativo; // Value injected by FXMLLoader

    @FXML // fx:id="txtLog"
    private TextArea txtLog; // Value injected by FXMLLoader

    @FXML
    void handleNuova(ActionEvent event) {
    	
	    	this.segreto = (int) (Math.random()*NMAX +1);  // mi da un double compreso tra 0 e 100 (escluso) -> lo trasformo in int e aggiungo 1
	   	this.tentativi = 0;
	    this.inGame = true; // è iniziata la partita
	   	 
	 	btnNuova.setDisable(true); // non mi serve il bottone di nuovaPartita -> disabilito
	    boxGioco.setDisable(false); // abilito il box di gioco
	    txtCur.setText(String.format("%d", this.tentativi)); // metto a 0 il numero di tentativi .. Intero->Stringa
	    txtMax.setText(String.format("%d", this.TMAX));
	    
	    txtLog.clear();
	    txtTentativo.clear();
	    
	    txtLog.setText(String.format("Indovina un numero tra %d e %d\n",
	    			1, NMAX));

    }

    @FXML
    void handleProva(ActionEvent event) {
    	
	    	String numS = txtTentativo.getText(); // scritto dall'utente nella casella di testo
	    	
	    	
		if(numS.length() == 0){ // se non scrive nulla -> messaggio SCRIVIII
		 		txtLog.appendText("Devi inserire un numero\n");
		 		return;
		}
	   	
	    try {
	    		int num = Integer.parseInt(numS) ; // -> eccezione se metto stringa.. TRY per non fare bloccare il programma
	    		
	    		// il numero era effettivamente un intero
	    		if(num < 1 || num > 100) {
	    			txtLog.appendText("Numero fuori dall'intervallo consentito!\n");
	    			return; // esco dal metodo senza incrementare i tentativi
	    		}
	    		
	    		if(num == this.segreto) {
	    			// utente ha indovinato (termina partita e ne inizia una nuova)
	    			
	    			txtLog.appendText("Hai vinto!\n");
	    			
	    			//chiudo partita
	    			btnNuova.setDisable(false);
	    			boxGioco.setDisable(true);
	    			this.inGame = false;
	    			
	    			
	    		} else {
	    			// tentativo errato
	    			this.tentativi++;
	    			txtCur.setText(String.format("%d", this.tentativi)); // modifica l'interfaccia
	    			
	    			// 1. era l'ultimo tentativo
	    			if(this.tentativi == this.TMAX) {
	    				// ha perso
	    				txtLog.appendText(
	    						String.format("Hai perso! Il numero era: %d\n", 
	    								this.segreto));
	    				//chiudo partita
		    			btnNuova.setDisable(false);
		    			boxGioco.setDisable(true);
		    			this.inGame = false;
	    			} else { //2.
	    				// sono ancora in gioco
	    				if(num<segreto) {
	    					//troppo basso
	    					txtLog.appendText("Troppo basso\n");
	    				} else {
	    					// troppo alto
	    					txtLog.appendText("Troppo alto\n");
	    				}
	    			}
	    			
	    		}
	   	 
	    
	    } catch(NumberFormatException ex) {
	   		txtLog.appendText("Il dato inserito non è numerico\n");
	   		return;
	   	}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtCur != null : "fx:id=\"txtCur\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtMax != null : "fx:id=\"txtMax\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert boxGioco != null : "fx:id=\"boxGioco\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtLog != null : "fx:id=\"txtLog\" was not injected: check your FXML file 'IndoNumero.fxml'.";

    }
}


	

