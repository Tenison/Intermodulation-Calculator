package application.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.CheckBox;


/**
 * @author Tenison
 *
 */
public class inputModel {
	private SimpleStringProperty input;
	private CheckBox select; 

	public inputModel() {
	    this.input = new SimpleStringProperty();  
	    this.select = new CheckBox();  
    }

	public inputModel(String input) {
	    this.input = new SimpleStringProperty(input);
	    this.select = new CheckBox(); 
    }
	
	public String getInput() {
		return input.get();
	}

	public void setInput(String input) {
		this.input.set(input);
	}
	
	public CheckBox getSelect() {
		return select;
	}

	public void setSelect(CheckBox select) {
		this.select = select;
	}

	public StringProperty getInputPorperty() {
		return this.input;
	}

	}
