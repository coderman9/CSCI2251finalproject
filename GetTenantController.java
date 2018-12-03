package client.view;

import java.io.IOException;
import client.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;



public class GetTenantController {
	
	ObservableList<String> tenantList = FXCollections.observableArrayList();
	private Main main; 
	@FXML private TextField nameField;
	@FXML private ListView <String> displayList;
	
	@SuppressWarnings("static-access")
	@FXML
	private void submitGetTenant() throws IOException {
		if(nameField.getText().equals("")) {
			//Error
		}
		else {
			tenantList.addAll(main.sendGetTenant(nameField.getText()));
			displayList.setItems(tenantList);
		}
			
			
			
		
			
		
	}
}
