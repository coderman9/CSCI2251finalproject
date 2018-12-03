package client.view;

import java.io.IOException;

import client.Main;
import javafx.fxml.FXML;

public class MainViewController {
	
	private Main main;
	

	@SuppressWarnings("static-access")
	@FXML
	private void goAddRental() throws IOException {
		main.showAddRentalScene();
	}
	@SuppressWarnings("static-access")
	@FXML
	private void goAddTenant() throws IOException {
		main.showAddTenantScene();
	}
	@SuppressWarnings("static-access")
	@FXML
	private void goSetRental() throws IOException {
		main.showSetRentalScene();
	}
	@SuppressWarnings("static-access")
	@FXML
	private void goGetRentalByID() throws IOException {
		main.showGetRentalByIDScene();
	}
	@SuppressWarnings("static-access")
	@FXML
	private void goGetRentalByType() throws IOException {
		main.showGetRentalByTypeScene();
	}
	@SuppressWarnings("static-access")
	@FXML
	private void goGetTenant() throws IOException {
		main.showGetTenantScene();
	}
	@SuppressWarnings("static-access")
	@FXML
	private void goGetBilling() throws IOException {
		main.showGetBillingScene();
	}
	@SuppressWarnings("static-access")
	@FXML
	private void goSetBilling() throws IOException {
		main.showSetBillingScene();
	}


}
