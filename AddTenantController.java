package client.view;

import java.io.IOException;
import java.util.ArrayList;
import client.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;


public class AddTenantController {
	
	ObservableList<String> personList = FXCollections.observableArrayList();
	ArrayList<String> personIDs = new ArrayList<String> (10);
	private Main main; 
	@FXML
	private TextField nameField;
	@FXML
	private TextField dobField;
	@FXML
	private TextField addressField;
	@FXML
	private TextField phoneNumberField;
	@FXML
	private ChoiceBox<String> billableMemberBox;
	@FXML
	private void initialize() {
	}
	
	@SuppressWarnings("static-access")
	@FXML
	private void submitAddPerson() throws IOException {
		if(nameField.getText().equals("")) {
			//Error
		}			
		
		else if(dobField.getText().equals("")) {
			//Error
		}			
		
		else if(addressField.getText().equals("")) {
			//Error
		}
		
		else if(phoneNumberField.getText().equals("")) {
			//Error
		}
		
		else {
			personIDs.add(main.sendAddPerson(nameField.getText(),dobField.getText(),addressField.getText(),phoneNumberField.getText()));
			personList.add(nameField.getText());
			billableMemberBox.setItems(personList);
		}
	}
	@SuppressWarnings("static-access")
	@FXML
	private void submitAddTenant() throws IOException {
		int k=-1;
		int i;
		if (billableMemberBox.getValue().equals("")) {
			//error
		}
		else {
			for (i=0;i<personIDs.size();i++) {
				if (personList.get(i).equals(billableMemberBox.getValue())) {
					k=i;
				}
			}
				
			main.sendAddTenant(personIDs.get(k),personIDs);
			personList.clear();
			personIDs.clear();
			billableMemberBox.setItems(personList);
		}
			
	}

}
