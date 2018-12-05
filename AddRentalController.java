package client.view;

import java.io.IOException;

import client.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddRentalController {
	
	ObservableList<String> rentalTypeList = FXCollections.observableArrayList("Vacation Rental","Apartment Rental","Single-Family Home");
	
	private Main main;
	@FXML
	private TextField addressField;
	@FXML
	private TextArea descriptionField;
	@FXML
	private TextField priceField;
	@FXML
	private ChoiceBox<String> rentalTypeBox;
	
	@FXML
	private void initialize() {
		rentalTypeBox.setItems(rentalTypeList);
		rentalTypeBox.setValue("Vacation Rental");
	}
	
	
	@SuppressWarnings("static-access")
	@FXML
	private void submitAddRental() throws IOException {
		if(addressField.getText().equals("")) {
			//Error
		}
			
		else if(descriptionField.getText().equals("")) {
			//Error
		}
			
		else if(priceField.getText().equals("")) {
			//Error
		}
			
		else if(rentalTypeBox.getValue().equals("")) {
			//Error
		}
		else
			main.sendAddRental(addressField.getText(),descriptionField.getText(),priceField.getText(),rentalTypeBox.getValue());
		
	}

}
