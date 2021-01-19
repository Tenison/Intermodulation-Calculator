package application;
	
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import application.models.inputModel;
import application.views.rootPaneController;
import application.views.secondOrder;
import application.views.thirdOrder;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * @author Tenison
 *
 */
public class Main extends Application {
	private Stage primaryStage; 
	private BorderPane mainLayout;
	private rootPaneController controller;
	private secondOrder controllerTwo;
	private thirdOrder controllerThird;
	public static String upper;
	public static String lower;
	
	public final static String userHome = System.getProperty("user.home");
	BorderPane root = new BorderPane();
	public static String DB = userHome + "/freq.txt";
	
	//public static ObservableList<entryModel> shiftDataM;
	// ObservableList<entryModel> shiftDataN;
	public static ObservableList<inputModel> frequencies = FXCollections.observableArrayList();
	public static inputModel kInput = new inputModel();
	
    public static void readData() throws IOException{
    	if(new File(Main.DB).exists() && new File(Main.DB).length() > 0){
    		frequencies.clear();
    		Scanner s = new Scanner(new File(Main.DB));
    		kInput.setInput(s.next());
    		//System.out.println(kInput.getInput());
    		
    		String[] temp = kInput.getInput().split(",");
    		
    		for (String i : temp){
    			frequencies.add(new inputModel(i));
    		}
    	    
    	s.close();
    	}
    }
    
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("Frequency Computation");
			
			showMainView();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	
	private void showMainView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("views/rootView.fxml"));
		
		
        controller = loader.getController();
		mainLayout = loader.load();

		Scene scene = new Scene(mainLayout);
		this.primaryStage.setResizable(false);
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
	}

	
	/////////////////
	
	//Show new entry pane
	public void showSecond() throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/orderTwo.fxml"));
		
		try { 
			AnchorPane seen = fxmlLoader.load();
			Stage newsndStage = new Stage();
			newsndStage.setTitle("Compute");
			newsndStage.initModality(Modality.WINDOW_MODAL);
			newsndStage.initOwner(primaryStage);
			Scene scene = new Scene(seen);
			//scene.getStylesheets().add(getClass().getResource("css/entry.css").toExternalForm());
			newsndStage.setScene(scene);
			newsndStage.setResizable(false);
			
			// Set the root parent into the controller.
			 this.controllerTwo = fxmlLoader.getController();
            newsndStage.showAndWait();
	
		} catch (IOException e) {
            e.printStackTrace();
		}			
	}
	
	public void showThird() throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/orderThird.fxml"));
		
		try { 
			AnchorPane seen = fxmlLoader.load();
			Stage newsndStage = new Stage();
			newsndStage.setTitle("Compute");
			newsndStage.initModality(Modality.WINDOW_MODAL);
			newsndStage.initOwner(primaryStage);
			Scene scene = new Scene(seen);
			//scene.getStylesheets().add(getClass().getResource("css/entry.css").toExternalForm());
			newsndStage.setScene(scene);
			newsndStage.setResizable(false);
			
			// Set the root parent into the controller.
			 this.controllerThird = fxmlLoader.getController();
            	newsndStage.showAndWait();
		} catch (IOException e) {
            e.printStackTrace();
		}			
	}
	///////////////
	
	public void getLimits(){
		
	}
	
	///don't go here
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void alertSuccess(String title, String content){
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("VOR Internmodulation");
        alert.setHeaderText(title);
        alert.setContentText(content);       
        alert.showAndWait();
	}

	public static void alertError(String title, String content){
		Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("VOR Computation");
        alert.setHeaderText(title);
        alert.setContentText(content);       
        alert.showAndWait();
	}
	
	public static void alertWarning(String title, String content){
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("VOR Computation");
        alert.setHeaderText(title);
        alert.setContentText(content);       
        alert.showAndWait();
	}
}
