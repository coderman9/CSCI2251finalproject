package client.view;

import java.io.IOException;
import java.util.ArrayList;

import client.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GetRBIController {
	
	private Main main;
	@FXML
	private TextField rentalIDField;
	@FXML
	private TextArea rentalDisplay;
	

	@SuppressWarnings("static-access")
	@FXML
	private void submitGetRentalByID() throws IOException {
		if(rentalIDField.getText().equals("")) {
			//Error
		}
		
		else {
			ArrayList <String> rentalLi= new ArrayList<String>(0);
			rentalLi.addAll(main.sendGetRentalByID(rentalIDField.getText()));
			for(int i=0; i<rentalLi.size();i++) {
				rentalDisplay.appendText(rentalLi.get(i));
			}
		}
	}
}
