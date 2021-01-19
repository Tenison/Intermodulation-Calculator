package application.views;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import application.Main;
import application.models.inputModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class secondOrder {
	NumberFormat formatter = new DecimalFormat("#0.0"); 
    @FXML ChoiceBox<String> freqOne;
    @FXML ChoiceBox<String> freqTwo;
    @FXML Button ok;
    @FXML TextArea results;
    
    ArrayList<String> data = new ArrayList<>();
    
    
    private double f1 = 0.0;
    private double f2 = 0.0;
    private double result;
    
    
    private ArrayList<String> getData(){
    	this.data.clear();
    	for(inputModel i: Main.frequencies){
    		data.add(i.getInput());
    	}
    	return data;
    }
    
    private void setData(){
    	this.f1 = Double.parseDouble(this.freqOne.getValue());
    	this.f2 = Double.parseDouble(this.freqTwo.getValue());
    	this.results.setText("(2 * "+this.f1 + ") - " + this.f2);
    }
    
    public void doCompute(){
    	this.result = (2*f1) - f2 ;
    	this.results.appendText("\n--------------------------------");
    	this.results.appendText("\nResult of Computation is " + this.formatter.format(this.result));
    }
    
    @FXML
    public void compute(){
    	setData();
    	doCompute();
    }
    
	@FXML
	private void initialize() {
		this.freqOne.setItems(FXCollections.observableArrayList(this.getData()));
		this.freqOne.getSelectionModel().selectFirst();
		this.freqTwo.setItems(FXCollections.observableArrayList(this.getData()));
		this.freqTwo.getSelectionModel().selectFirst();
		
	}
}
