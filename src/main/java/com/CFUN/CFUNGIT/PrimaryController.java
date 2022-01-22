package com.CFUN.CFUNGIT;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.paint.Color; 

public class PrimaryController {

	private char operation;
	private char typeDeSport;
	public JFXButton operationButton = null;
	public JFXButton sortieButton = null;
	public JFXButton muscuButton = null;
	public JFXButton fitButton = null;
	public AnchorPane LoginSelection = null;
	public AnchorPane TypeOperation = null;
	public JFXButton valideButton = null;
	
	//Password Page
	public AnchorPane PasswordAnchor = null;
	public PasswordField PasswordFielde = null;
	public Text WrongPasswordText = null;
	
	
	@FXML
	private void BackToMainPage() {
		PasswordAnchor.setVisible(false);
		TypeOperation.setVisible(false);
		LoginSelection.setVisible(true);
	}
	
	@FXML 
	private void TryLogin() {
		if(PasswordFielde.getText().equals("test")) {
			System.out.println("Good password");
			WrongPasswordText.setText("Good Password");
			WrongPasswordText.setStyle("-fx-fill: green;");
			WrongPasswordText.setVisible(true);
			
		}else {
			WrongPasswordText.setVisible(true);
			System.out.println("Bad Password");
			System.out.println(PasswordFielde);
			
		}
	}
	@FXML 
	private void ShowPasswordAnchor() {
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
		
	}

	@FXML
	private void SortieButton() throws IOException {
		// App.setRoot("secondary");
		operation = 'S';
		operationButton.setStyle("-fx-background-color: grey");
		sortieButton.setStyle("-fx-background-color: #FF6F16");
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
		
		System.out.println("Operation :" + operation + " type de sport :" + typeDeSport);
		App.setOperation(operation);
		App.settypeDeSport(typeDeSport);
		App.setRoot("secondary");
		
		
	}
	
	

}
