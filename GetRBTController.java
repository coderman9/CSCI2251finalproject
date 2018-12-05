package client.view;

import java.io.IOException;
import java.util.ArrayList;

import client.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

public class GetRBTController {
	
	ObservableList<String> rentalTypeList = FXCollections.observableArrayList("Vacation Rental","Apartment Rental","Single-Family Home");
	
	private Main main;
	@FXML
	private ChoiceBox<String> rentalTypeBox;
	@FXML
	private DatePicker startDate;
	@FXML
	private DatePicker endDate;
	@FXML
	private TextArea displayArea;
	
	@FXML
	private void initialize() {
		rentalTypeBox.setItems(rentalTypeList);
		rentalTypeBox.setValue("Vacation Rental");
	}
	
	
	@SuppressWarnings("static-access")
	@FXML
	private void submitGetRentalByType() throws IOException {
		if(startDate.getValue().equals(null)) {
			//Error
		}
		else if(startDate.getValue().equals(null)) {
			//Error
		}
		else if(rentalTypeBox.getValue().equals("")) {
			//Error
		}
		else {			
			ArrayList <String> rentalLi= new ArrayList<String>(0);
			rentalLi.addAll(main.sendGetRentalByType(rentalTypeBox.getValue(),startDate.getValue(),endDate.getValue()));
			for(int i=0; i<rentalLi.size();i++) {
				displayArea.appendText(rentalLi.get(i));
			}
		}
	}
}
