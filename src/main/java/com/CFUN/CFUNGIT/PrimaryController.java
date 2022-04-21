package com.CFUN.CFUNGIT;

import java.io.IOException;
import java.sql.SQLException;
import com.CFUN.sqlite.Database;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class PrimaryController extends Database{

	//Operation page
	private char operation;
	private char typeDeSport;
	public JFXButton operationButton = null;
	public JFXButton sortieButton = null;
	public JFXButton muscuButton = null;
	public JFXButton fitButton = null;
	public AnchorPane LoginSelection = null;
	public AnchorPane Connected = null;
	public AnchorPane TypeOperation = null;
	public JFXButton valideButton = null;
	
	//Password Page
	public AnchorPane PasswordAnchor = null;
	public PasswordField PasswordFielde = null;
	public Text CheckPasswordText = null;
	
	//TableView Equipement
	public Text COLUMN11 = null;
	public Text COLUMN12 = null;
	public Text COLUMN13 = null;
	public Text COLUMN14 = null;
	public Text COLUMN21 = null;
	public Text COLUMN22 = null;
	public Text COLUMN23 = null;
	public Text COLUMN24 = null;
	public Text COLUMN31 = null;
	public Text COLUMN32 = null;
	public Text COLUMN33 = null;
	public Text COLUMN34 = null;
	
	@FXML
	private void BackToMainPage() {
		PasswordFielde.setText(null);
		valideButton.setDisable(true);
		muscuButton.setDisable(true);
		fitButton.setDisable(true);
		operationButton.setStyle("-fx-background-color: grey");
		sortieButton.setStyle("-fx-background-color: grey");
		muscuButton.setStyle("-fx-background-color: grey");
		fitButton.setStyle("-fx-background-color: grey");
		PasswordAnchor.setVisible(false);
		TypeOperation.setVisible(false);
		LoginSelection.setVisible(true);
		CheckPasswordText.setVisible(false);
	}
	
	@FXML
	private void Disconnect() {
		PasswordFielde.setText(null);
		CheckPasswordText.setVisible(false);
		Connected.setVisible(false);
		PasswordAnchor.setVisible(true);
	}
	
	//	Connection via database BDD SQLite, table gestionnaire
	@FXML 
	private void TryLogin() throws SQLException {
		String[] psd = getPassword();
		for (int i = 0; i < psd.length; i++) {
			if(PasswordFielde.getText().equals(psd[i])) {
				Connected.setVisible(true);
				PasswordAnchor.setVisible(false);
				// Blank et Empty = champs vide				
			} else if (PasswordFielde.getText().isEmpty() || PasswordFielde.getText().isBlank() || PasswordFielde.getText().equals(psd[i]) == false || PasswordFielde.getText().equals("")){
				CheckPasswordText.setVisible(true);
				CheckPasswordText.setText("Mot de passe Incorrect");
				CheckPasswordText.setStyle("-fx-fill: red;");
			}
		}
	}
	
	//	Connection via database BDD SQLite, table equipement
	private void equipementValue() throws SQLException {
		String[] nom = getEquipementNom();
		String[] type = getEquipementType();
		String[] qte = getEquipementQte();
		
		// COLUMN 1 : Nom
		COLUMN11.setText(nom[0]);
		COLUMN12.setText(nom[1]);
		COLUMN13.setText(nom[2]);
		COLUMN14.setText(nom[3]);
		
		// COLUMN 2 : Type
		COLUMN21.setText(type[0]);
		COLUMN22.setText(type[1]);
		COLUMN23.setText(type[2]);
		COLUMN24.setText(type[3]);
		
		// COLUMN 3 : Qte
		COLUMN31.setText(qte[0]);
		COLUMN32.setText(qte[1]);
		COLUMN33.setText(qte[2]);
		COLUMN34.setText(qte[3]);
	}
	
	@FXML 
	private void ShowPasswordAnchor() throws SQLException {
		equipementValue();
		PasswordAnchor.setVisible(true);
		LoginSelection.setVisible(false);
	}
	
	@FXML
	private void ShowTypeSelection() {
		TypeOperation.setVisible(true);
		LoginSelection.setVisible(false);
	}
	
	@FXML
	private void EntreeButton() throws IOException {
		// App.setRoot("secondary");
		operation = 'E';
		operationButton.setStyle("-fx-background-color: #FF6F16");
		sortieButton.setStyle("-fx-background-color: grey");
		muscuButton.setDisable(false);
		fitButton.setDisable(false);
		valideButton.setDisable(true);
		fitButton.setStyle("-fx-background-color: grey");
		muscuButton.setStyle("-fx-background-color: grey");
	}

	@FXML
	private void SortieButton() throws IOException {
		// App.setRoot("secondary");
		operation = 'S';
		operationButton.setStyle("-fx-background-color: grey");
		muscuButton.setStyle("-fx-background-color: grey");
		fitButton.setStyle("-fx-background-color: grey");
		sortieButton.setStyle("-fx-background-color: #FF6F16");
		muscuButton.setDisable(true);
		fitButton.setDisable(true);
		valideButton.setDisable(false);
	}

	@FXML
	private void muscuButton() throws IOException {
		// App.setRoot("secondary");
		typeDeSport = 'M';
		muscuButton.setStyle("-fx-background-color: #FF6F16");
		fitButton.setStyle("-fx-background-color: grey");
		valideButton.setDisable(false);
		
	}

	@FXML
	private void fitButton() throws IOException {
		// App.setRoot("secondary");
		typeDeSport = 'F';
		muscuButton.setStyle("-fx-background-color: grey");
		fitButton.setStyle("-fx-background-color: #FF6F16");
		valideButton.setDisable(false);
	}

	@FXML
	private void valideButton() throws IOException {
		
		//	System.out.println("Operation :" + operation + " type de sport :" + typeDeSport);
		App.setOperation(operation);
		App.settypeDeSport(typeDeSport);
		App.setRoot("secondary");
			
	}	

}
