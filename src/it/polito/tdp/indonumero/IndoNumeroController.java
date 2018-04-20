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
import javafx.util.converter.NumberStringConverter;

public class IndoNumeroController {
	
	private Model model; // funzione alla fine

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
    	
	    model.newGame();
	   	 
	 	btnNuova.setDisable(true); // non mi serve il bottone di nuovaPartita -> disabilito
	    boxGioco.setDisable(false); // abilito il box di gioco
	    // txtCur.setText(String.format("%d", model.getTentativi())); // metto a 0 il numero di tentativi .. Intero->Stringa
	    txtMax.setText(String.format("%d", model.getTMAX()));
	    
	    txtLog.clear();
	    txtTentativo.clear();
	    
	    txtLog.setText(String.format("Indovina un numero tra %d e %d\n",
	    			1, model.getNMAX()));

    }

    @FXML
    void handleProva(ActionEvent event) {
    	
    	// ACQUISIZIONE E VERIFICA DEI DATI
    	
	    	String numS = txtTentativo.getText(); // scritto dall'utente nella casella di testo
	    	
	    	
		if(numS.length() == 0){ // se non scrive nulla -> messaggio SCRIVIII
		 		txtLog.appendText("Devi inserire un numero\n");
		 		return;
		}
	   	
		// la conversione STRINGA->INTERO è compito del CONTROLLER, il MODELLO deve lavorare con oggetti non testi.
	    try {
	    		int num = Integer.parseInt(numS) ; // -> eccezione se metto stringa.. TRY per non fare bloccare il programma
	    		
	    		// il numero era effettivamente un intero
	    		if(!model.valoreValido(num)) {
	    			txtLog.appendText("Numero fuori dall'intervallo consentito!\n");
	    			return; // esco dal metodo senza incrementare i tentativi
	    		}
	    		
	    		int risultato =  model.tentativo(num);
	    		//txtCur.setText(String.format("%d", model.getTentativi()));
	    		
	    		if(risultato == 0) {
	    			// utente ha indovinato (termina partita e ne inizia una nuova)
	    			txtLog.appendText("Hai vinto!\n");
	    		} else if(risultato < 0) {
	    			//troppo basso
				txtLog.appendText("Troppo basso\n");
	    		} else {
	    			// troppo alto
				txtLog.appendText("Troppo alto\n");
			}
	    		
	    		if(!model.isInGame()) {
	    			// la parita è finita (vittoria o sconfitta)
	    			
	    			if( risultato != 0 ) {
	    				txtLog.appendText("Hai perso!\n");
	    			txtLog.appendText(
	    					String.format("Il numero segreto era: %d\n", 
	    							model.getSegreto()));
	    			}
	    			//chiudo partita
	    			btnNuova.setDisable(false);
	    			boxGioco.setDisable(true);
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
    
    public void setModel(Model model) {
		this.model = model;
		
		txtCur.textProperty().bindBidirectional(model.tentativiProperty(), new NumberStringConverter()); // chiedo il riferimento alla proprietà che determina un valore di testo(oggetto interfaccia).. e la lego (bind) ad un valore osservabile creato in model 
		// tutte le volte che il modello modificherà questa proprietà la vista verrà aggiornata modificando il valore contenuto 
		// bindBidirectional serve per convertire Stringa a Intero
	}
}


	

