package client;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	private Stage primaryStage;
	private static BorderPane mainLayout;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Client Application");
		showMainView();
		
	}
	
	private void showMainView() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/MainView.fxml"));
		mainLayout=loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void sendAddRental(String address, String description, String price, String type) {
		ArrayList<String> addRentalLi = new ArrayList<String>(5);
		Collections.addAll(addRentalLi,"addRental",address,description,price,type);
		//cesarsFunction(addRentalLi);
	}

	public static void showAddRentalScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/AddRView.fxml"));
		BorderPane addRental = loader.load();
		mainLayout.setCenter(addRental);
	}
	public static void showAddTenantScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/AddTView.fxml"));
		BorderPane addTenant = loader.load();
		mainLayout.setCenter(addTenant);
	}
	public static void showSetRentalScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/SetRView.fxml"));
		BorderPane setRental = loader.load();
		mainLayout.setCenter(setRental);
	}
	public static void showGetRentalByIDScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/GetRBIView.fxml"));
		BorderPane getRentalByID = loader.load();
		mainLayout.setCenter(getRentalByID);
	}
	public static void showGetRentalByTypeScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/GetRBTView.fxml"));
		BorderPane getRentalByType = loader.load();
		mainLayout.setCenter(getRentalByType);
	}
	public static void showGetTenantScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/GetTView.fxml"));
		BorderPane getTenant = loader.load();
		mainLayout.setCenter(getTenant);
	}
	public static void showGetBillingScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/GetBillingView.fxml"));
		BorderPane getBilling = loader.load();
		mainLayout.setCenter(getBilling);
	}
	public static void showSetBillingScene() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/SetBillingView.fxml"));
		BorderPane setBilling = loader.load();
		mainLayout.setCenter(setBilling);
	}
	public static void main(String[] args) {
		launch(args);
	}

	public static String sendAddPerson(String name, String dob, String address, String phone) {
		ArrayList<String> personLi = new ArrayList<String>(5);
		Collections.addAll(personLi,"addPerson",name,dob,address,phone);
		return "RandoID";//cesarsFunction(personLi);
	}

	public static void sendAddTenant(String billableMemberID, ArrayList<String> personIDs) {
		ArrayList<String> tenantLi = new ArrayList<String>(12);
		Collections.addAll(tenantLi,"addPerson",billableMemberID);
		for (int i=0;i<personIDs.size();i++) {
			Collections.addAll(tenantLi,personIDs.get(i));
		}
		//cesarsFunction(tenantLi);
		
	}

	public static void sendSetRental(String rentalID, LocalDate startDate, LocalDate endDate, String tenantID) {
		ArrayList<String> setRentalLi = new ArrayList<String>(5);
		String start;
		String end;
		start = String.format("%02d",startDate.getMonthValue())+"/"+String.format("%02d",startDate.getDayOfMonth())+"/"+startDate.getYear();
		end = String.format("%02d",endDate.getMonthValue())+"/"+String.format("%02d",endDate.getDayOfMonth())+"/"+endDate.getYear();
		Collections.addAll(setRentalLi, "setRental", rentalID,start,end,tenantID);
		//cesarsFunction(setRentalLi);
		
	}

	public static ArrayList<String> sendGetRentalByID(String rentalID) {
		ArrayList<String> rentalReserves = new ArrayList <String>(6);
		ArrayList<String> getRentalByIDLi = new ArrayList <String>(2);
		Collections.addAll(getRentalByIDLi, "getRentalByID",rentalID);
		//Collections.addAll(rentalReserves,CesarsFunction(getRentalByIDLi);
		rentalReserves.add("Completed");
		return rentalReserves;
		
	}

	public static ArrayList<String> sendGetRentalByType(String rentalType, LocalDate startDate, LocalDate endDate) {
		ArrayList<String> rentalsOpen = new ArrayList <String>(0);
		ArrayList<String> getRentalByTypeLi = new ArrayList <String>(4);
		String start;
		String end;
		start = endDate.toString();
		end = endDate.toString();
		Collections.addAll(getRentalByTypeLi,"getRentalByType",rentalType,start,end);
		//Collections.addAll(rentalsOpen,CesarsFunction(getRentalByTypeLi));
		rentalsOpen.add("Completed");
		return rentalsOpen;

		
	}

	public static ArrayList<String> sendGetTenant(String tenantName) {
		ArrayList<String> TenantLi = new ArrayList <String> (0);
		ArrayList<String> getTenantLi = new ArrayList <String> (2);
		getTenantLi.add("getTenant");
		getTenantLi.add(tenantName);
		//Collections.addAll(getTenantLi,CesarsFunction(getTenantLi));
		TenantLi.add("Completed");
		TenantLi.add("RandoID");
		return TenantLi;
	}

	public static ArrayList<String> sendGetBilling(String name) {
		Date now = new Date();
		LocalDate today = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		String todayString;
		todayString = today.toString();
		ArrayList <String> getBilling = new ArrayList <String>(0);
		ArrayList <String> billing = new ArrayList <String>(0);
		getBilling.add("getBilling");
		getBilling.add(name);
		getBilling.add(todayString);
		//Collections.add(billing,CesarsFunction(getBilling));
		billing.add("All Done");
		billing.add("Nothing more Owed");
		return billing;
	}

	public static String sendSetBilling(ArrayList<String> setBillingLi) {
		String balanceRemaining;
		//balanceRemaining=CesarsFunction(setBillingLi).getIndex(1);
		balanceRemaining = "850.56";
		return balanceRemaining;
	}
}
