package client.view;

import java.io.IOException;
import java.util.ArrayList;
import client.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;



public class GetBillingController {
	
	private Main main; 
	ArrayList <String> billingLi = new ArrayList <String> (0);
	@FXML private TextField nameField;
	@FXML private TextArea billingDisplay;
	
	@SuppressWarnings("static-access")
	@FXML
	private void submitGetBilling() throws IOException {
		if(nameField.getText().equals("")) {
			//Error
		}
		else {
			
			billingLi.addAll(main.sendGetBilling(nameField.getText()));
			for (int i=0;i<billingLi.size();i++) {
					billingDisplay.appendText(billingLi.get(i)+" ");
			}
		}
	}
}
