package application.views;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;

import application.Main;
import application.models.TaskModel;
import application.models.inputModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class rootPaneController {
    @FXML Button add;
    @FXML Button remove;
    @FXML Button clear;
    @FXML TextField freq_input;
    @FXML TableView<inputModel> table;
    @FXML TableColumn<inputModel, String> id;
    @FXML TableColumn<inputModel, String> freq;
    
    @FXML Button compute_s;
    @FXML Button compute_t;
    @FXML TextField f1;
    @FXML TextField f2;
    
    
    
    private String freqOne;
    private String freqTwo;
    ArrayList<TaskModel> results = new ArrayList<TaskModel>();
    ObservableList<TaskModel> listModData = FXCollections.observableArrayList();
    @FXML TableView<TaskModel> intermodulationTable;
    @FXML TableColumn<TaskModel, String> orderCol;
    @FXML TableColumn<TaskModel, String> formula;
    @FXML TableColumn<TaskModel, String> outputCol;
    
    @FXML TextField l_limit;
    @FXML TextField u_limit;
    
    inputModel kbInput = new inputModel();

 
    
	public rootPaneController(){
	}  
	
	

//	@FXML
//	public void viewSecond() throws IOException{
//		new Main().showSecond();
//	}
//	
//	@FXML
//	public void viewThird() throws IOException{
//		new Main().showThird();
//	}
    
	@FXML 
	public void clear(){
		this.listModData.clear();
	}
	
    public String getInput(){
    	System.out.println("start");
    	 return this.freq_input.getText();
    }
	
    @FXML 
    public void input(){
    	try {
			if(isInputValid()){
				this.kbInput.setInput(getInput());
				saveDataDB();
				Main.readData();
				this.freq_input.clear();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Main.alertError("error, input correctly", "");
		}
    }
    
    public void saveDataDB() throws IOException{
    		BufferedWriter bw = null;
    		FileWriter fw = null;

    		try {
    			File file = new File(Main.DB);

    			// if file doesnt exists, then create it
    			if (!file.exists())
    				file.createNewFile();
    			// true = append file
    			fw = new FileWriter(file.getAbsoluteFile(), true);
    			bw = new BufferedWriter(fw);

    			bw.write(this.getInput() + ",");
    		} catch (IOException e) {
    			e.printStackTrace();
    		} finally {
    			try {
    				if (bw != null)
    					bw.close();
    				if (fw != null)
    					fw.close();
    			} catch (IOException ex) {
    				ex.printStackTrace();
    			}
    		}
    	}
    

	/**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    
    
	
	private void setShiftData(){
		this.table.setItems(Main.frequencies);
		{
			this.id.setCellValueFactory(new  PropertyValueFactory<inputModel, String>("select"));
			this.freq.setCellValueFactory((cellData) -> cellData.getValue().getInputPorperty());
		}
	}
	
		@FXML
		public void DeleteSelectedEntries() throws SQLException, IOException{
			getSelectedEntriesId();
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Main.DB), "utf-8"))) {
				writer.write(deleteFileData());
			}
			//Main.readData();
		}
		
		private void getSelectedEntriesId() throws IOException{
			delListData(Main.frequencies);
		}
		
		private void delListData(ObservableList<inputModel> shift) throws IOException{
			ArrayList<inputModel> list = new ArrayList<>();
			for(inputModel temp : shift){
				if(temp.getSelect().isSelected())
					list.add(temp);
			}
			Main.frequencies.removeAll(list);
		}
		
		public String deleteFileData(){
			ArrayList<String> list = new ArrayList<>();
			for(inputModel i : Main.frequencies)
				list.add(i.getInput());
			 String tem = String.join(",", list);
			 if(!tem.isEmpty())
				 return tem + ",";
			 else
				 return tem;
		}
		private void setData(){			
			this.intermodulationTable.setItems(this.listModData);
			{
				this.orderCol.setCellValueFactory((cellData)-> cellData.getValue().orderProperty());
				this.formula.setCellValueFactory((cellData)-> cellData.getValue().formulaProperty());
				this.outputCol.setCellValueFactory((cellData)-> cellData.getValue().outputProperty());
			}
		}
	@FXML
	private void initialize() throws IOException{
		this.setData();
		this.outputCol.setCellFactory(column -> {
		    return new TableCell<TaskModel, String>() {
		        @Override
		        protected void updateItem(String item, boolean empty) {
		            super.updateItem(item, empty); //This is mandatory

		            if (item == null || empty) { //If the cell is empty
		                setText(null);
		                setStyle("");
		            } else { //If the cell is not empty

		                setText(item); //Put the String data in the cell

		                //We get here all the info of the Person of this row
		                TaskModel aux = getTableView().getItems().get(getIndex());


//		                if(isNumeric(reduceString(aux.getoutput(), 4))){
//		                	System.out.println(Double.parseDouble(reduceString(aux.getoutput(), 4)) + "<" + Double.parseDouble(Main.lower));
//			                System.out.println((Double.parseDouble(reduceString(aux.getoutput(), 4))  + ">" + Double.parseDouble(Main.upper)));
//		                	if ((Double.parseDouble(reduceString(aux.getoutput(), 4)) <= Double.parseDouble(Main.lower))
//			                		|| (Double.parseDouble(reduceString(aux.getoutput(), 4)) >= Double.parseDouble(Main.upper))) {
//			                    //setTextFill(Color.RED); //The text in red
//			                    setStyle("-fx-background-color: yellow"); //The background of the cell in yellow
//			                } 
//		                }
		            }
		        }
		    };
		});
		//Main.readData();
		//this.setShiftData();
	}
	
	public String reduceString(String str, int numberOfCharaters) {
	    if (str != null && str.length() > 0) {
	        str = str.substring(0, str.length() - numberOfCharaters);
	    }
	    return str;
	}
	
	
	////new vor methods
	@FXML
	public void computeIntermodulation() throws IOException{
		if(isInputValid()){
			Main.lower = this.l_limit.getText();
			Main.upper = this.u_limit.getText();
			getFreqInput();
			computeVariousIntermodulation();
			insertIntoTable();
		}else{
			Main.alertError("", "Please Input the correct Frequencies");
		}
		
		
		
	}
	
	private void getFreqInput(){
		this.freqOne = this.f1.getText();
		this.freqTwo = this.f2.getText();
	}
	
	private boolean isInputValid() throws IOException{
        String errorMessage = "";

        if ((f1.getText() == null || f1.getText().length() == 0 || !isDouble(f1.getText()))
        		|| (f2.getText() == null || f2.getText().length() == 0 || !isDouble(f2.getText()))) {
            errorMessage += "Not a valid Title!\n"; 
        }

        if (errorMessage.length() == 0) {
        	return true;
        } else {
        	//Main.alertError("Error!!! input ", "");
            return false;
        }
     }
	
	public static boolean isDouble(String str) {
	    try {
	        Double.parseDouble(str);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
	private static boolean isNumeric(String str)
	{
	  NumberFormat formatter = NumberFormat.getInstance();
	  ParsePosition pos = new ParsePosition(0);
	  formatter.parse(str, pos);
	  return str.length() == pos.getIndex();
	}
	
	
	private void computeVariousIntermodulation(){
		double One = new Double(this.freqOne);
		double two = new Double(this.freqTwo);
		
		DecimalFormat df = new DecimalFormat("#.##"); 
		
		Double result = (2*One) - two;
		results.add(new TaskModel("2", "(2 * " + One +") - " + two + "" , String.valueOf(df.format(result)) + " MHz" ));
		
		
		
		//results.add(new TaskModel("--------","-------------------------","-----------------"));
		
		
		
		System.out.println(results.get(0).getFormula());
		
	}
	
	private void insertIntoTable(){
		for (TaskModel list : this.results) {
		    this.listModData.add(list);
		}
		results.clear();
	}
	
	
}
