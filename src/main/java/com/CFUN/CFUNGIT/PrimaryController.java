package com.CFUN.CFUNGIT;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.CFUN.sqlite.Database;
import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class PrimaryController extends Database implements Initializable {
	
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
	@FXML
	private TableView<Equipement> table;
	@FXML
	private TableColumn<Equipement, String> nom;
	@FXML
	private TableColumn<Equipement, String> type;
	@FXML
	private TableColumn<Equipement, String> qte;
	
	public ObservableList<Equipement> ListTableEquipement() throws SQLException {
        String[] nomTable = getEquipementNom();
        String[] typeTable = getEquipementType();
        String[] qteTable = getEquipementQte();

        ObservableList<Equipement> list = FXCollections.observableArrayList(
            new Equipement(nomTable[0], typeTable[0], qteTable[0]),
            new Equipement(nomTable[1], typeTable[1], qteTable[1]),
            new Equipement(nomTable[2], typeTable[2], qteTable[2]),
            new Equipement(nomTable[3], typeTable[3], qteTable[3]),
            new Equipement(nomTable[4], typeTable[4], qteTable[4]),
            new Equipement(nomTable[5], typeTable[5], qteTable[5]),
            new Equipement(nomTable[6], typeTable[6], qteTable[6]),
            new Equipement(nomTable[7], typeTable[7], qteTable[7]),
            new Equipement(nomTable[8], typeTable[8], qteTable[8]),
            new Equipement(nomTable[9], typeTable[9], qteTable[9]),
            new Equipement(nomTable[10], typeTable[10], qteTable[10]),
            new Equipement(nomTable[11], typeTable[11], qteTable[11]),
            new Equipement(nomTable[12], typeTable[12], qteTable[12]),
            new Equipement(nomTable[13], typeTable[13], qteTable[13])
        );

        return list;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nom.setCellValueFactory(new PropertyValueFactory<Equipement, String>("nom"));
        type.setCellValueFactory(new PropertyValueFactory<Equipement, String>("type"));
        qte.setCellValueFactory(new PropertyValueFactory<Equipement, String>("qte"));
        try {
            table.setItems(ListTableEquipement());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    };
	
	@FXML 
	private void ShowPasswordAnchor() throws SQLException {
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
