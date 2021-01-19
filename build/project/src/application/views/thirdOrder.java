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

public class thirdOrder {
	NumberFormat formatter = new DecimalFormat("#0.0"); 
    @FXML ChoiceBox<String> freqOne;
    @FXML ChoiceBox<String> freqTwo;
    @FXML ChoiceBox<String> freqThree;
    @FXML TextArea results; 
    @FXML Button ok;
    ArrayList<String> data = new ArrayList<>();
    
    private double f1 = 0.0;
    private double f2 = 0.0;
    private double f3 = 0.0;
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
    	this.f3 = Double.parseDouble(this.freqThree.getValue());
    	this.results.setText("("+this.f1+" + "+this.f2 + ") - " + this.f3);
    }
    
    public void doCompute(){
    	this.result = (f1 + f2) - f3;
    	this.results.appendText("\n--------------------------------");
    	this.results.appendText("\nResult of Computation is " + this.formatter.format(this.result));
    	System.out.println("heelo");
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
		this.freqThree.setItems(FXCollections.observableArrayList(this.getData()));
		this.freqThree.getSelectionModel().selectFirst();
	}

}
