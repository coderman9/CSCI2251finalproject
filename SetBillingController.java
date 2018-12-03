package client.view;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import client.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;



public class SetBillingController {
	
	private Main main; 
	ArrayList <String> setBillingLi = new ArrayList <String> (0);
	@FXML private TextField rentalIDField;
	@FXML private TextField paymentField;
	@FXML private TextField chargeField;
	@SuppressWarnings("static-access")
	@FXML
	private void submitSetBilling() throws IOException {
		if(rentalIDField.getText().equals("")) {
			//Error
		}
		else if (paymentField.getText().equals("")) {
			//Error
		}
		else if (chargeField.getText().equals("")) {
			//Error
		}
		else {
			try {
				BigDecimal bdpayment = new BigDecimal(paymentField.getText());
				BigDecimal bdcharge = new BigDecimal(chargeField.getText());
				BigDecimal bdtotal = new BigDecimal(0.00);
				bdtotal.subtract(bdpayment);
				bdtotal.add(bdcharge);
				System.out.println(bdtotal.toString());
				setBillingLi.add("setBilling");
				setBillingLi.add(rentalIDField.getText());
				setBillingLi.add(bdtotal.toString());
				BigDecimal bdbalance = new BigDecimal(main.sendSetBilling(setBillingLi));
				System.out.println(bdbalance.toString());
			}
			catch (NumberFormatException e) {
				return;
			}


		}
	}
}
