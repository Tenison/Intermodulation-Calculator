package application.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TaskModel{
	private StringProperty order;
	private StringProperty formula;
	private StringProperty output;

	public TaskModel(String order, String formula, String output){
		this.order = new SimpleStringProperty(order);
		this.formula = new SimpleStringProperty(formula);
		this.output = new SimpleStringProperty(output);
	}
	/**
	 * @return the order
	 */
	public String getOrder() {
		return order.get();
	}

	/**
	 * @param taskName the order to set
	 */
	public void setOrder(String order) {
		this.order.set(order);
	}

	/**
	 * @param order to get
	 */
	public StringProperty orderProperty() {
		return order;
	}
	
	/**
	 * @return the formula
	 */
	public String getFormula() {
		return formula.get();
	}

	/**
	 * @param formula the formula to set
	 */
	public void setFormula(String formula) {
		this.formula.set(formula);
	}

	/**
	 * @param formula to get
	 */
	public StringProperty formulaProperty() {
		return formula;
	}
	
	/**
	 * @return the output
	 */
	public String getoutput() {
		return output.get();
	}

	/**
	 * @param output the output to set
	 */
	public void setOutput(String output) {
		this.output.set(output);
	}

	/**
	 * @param output to get
	 */
	public StringProperty outputProperty() {
		return output;
	}
	
}
