package it.polito.tdp.indonumero;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("IndoNumero.fxml"));
			// FXMLLoader crea tutti i nodi della scena, noi creiamo l'oggetto scena e lo colleghiamo ai nodi
			BorderPane root = (BorderPane)loader.load(); // legge l'FXML
			Scene scene = new Scene(root); // root: NODO RADICE che è stato appena restituito dall' FXMLLoader
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Model model = new Model(); // prima di far lartire la scena
			((IndoNumeroController)loader.getController()).setModel(model); // la classe loader è generica, quindi devo specificare
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
