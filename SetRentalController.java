package client.view;

import java.io.IOException;

import client.Main;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class SetRentalController {
	
	private Main main;
	@FXML
	private TextField rentalIDField;
	@FXML
	private TextField tenantIDField;
	@FXML
	private DatePicker startDate;
	@FXML
	private DatePicker endDate;	
	
	@SuppressWarnings("static-access")
	@FXML
	private void submitSetRental() throws IOException {
		if(rentalIDField.getText().equals("")) {
			//Error
		}
			
		else if(tenantIDField.getText().equals("")) {
			//Error
		}
			
		else if(startDate.getValue().equals(null)) {
			//Error
		}
			
		else if(endDate.getValue().equals(null)) {
			//Error
		}
		else
			main.sendSetRental(rentalIDField.getText(),startDate.getValue(),endDate.getValue(),tenantIDField.getText());
		
	}

}
