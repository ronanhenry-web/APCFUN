package com.CFUN.sqlite;

import java.net.URL;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.ResourceBundle;

//table gestionnaire avec nom, prenom et password
//table equipement avec nom, type et quantité

public class Database {
	//	Getter pour récup les données
    public static void main(String[] args) throws Exception {
        getPassword();
        getEquipementNom();
        getEquipementType();
        getEquipementQte();
    }
    
    //	Récupérer les mdp dans la table gestionnaire    
    public static String[] password = new String[999];
    public static String[] getPassword() throws SQLException {
        String jdbcUrl = "jdbc:sqlite:cfun.db";
        Connection connection = DriverManager.getConnection(jdbcUrl);

        String req = "select * from gestionnaire";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(req);

        //	System.out.println("Table gestionnaire mdp :");
        int n=0;
        while(result.next()) {
        	password[n] = result.getString("password");
        	//	System.out.println(password[n]);
        	n++;
        }
        
        return (String[]) password;
    }
    
    
	public static String[] equipementNom = new String[999];
    public static String[] equipementType = new String[999];
    public static String[] equipementQte = new String[999];
    
    //	Récupérer tout les noms dans la table équipement
    public static String[] getEquipementNom() throws SQLException {
    	String jdbcUrl = "jdbc:sqlite:cfun.db";
        Connection connection = DriverManager.getConnection(jdbcUrl);
    	
        String req = "select * from equipement";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(req);
        
        //	System.out.println("------------------------------------");
        //	System.out.println("Nom équipement :");
        int n=0;
        while(result.next()) {
        	equipementNom[n] = result.getString("nom");
        	//	System.out.println(equipementNom[n]);
        	n++;
        }
        
		return equipementNom;
    }
    
    //	Récupérer tout les types dans la table équipement
    public static String[] getEquipementType() throws SQLException {
    	String jdbcUrl = "jdbc:sqlite:cfun.db";
        Connection connection = DriverManager.getConnection(jdbcUrl);
    	
        String req = "select * from equipement";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(req);
        
        //	System.out.println("------------------------------------");
        //	System.out.println("Type équipement :");
        int n=0;
        while(result.next()) {
        	if (result.getString("type").contains("F")) {
        		equipementType[n] = "Fitness";
        	} else {
        		equipementType[n] = "Musculation";
        	}
        	//	System.out.println(equipementType[n]);
        	n++;
        }
        
		return equipementType;
    }
    
    //	Récupérer toutes les quantitées dans la table équipement
    public static String[] getEquipementQte() throws SQLException {
    	String jdbcUrl = "jdbc:sqlite:cfun.db";
        Connection connection = DriverManager.getConnection(jdbcUrl);
    	
        String req = "select * from equipement";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(req);
        
        //	System.out.println("------------------------------------");
        //	System.out.println("Quantitée équipement :");
        int n=0;
        while(result.next()) {
        	equipementQte[n] = result.getString("quantite");   
        	//	System.out.println(equipementQte[n]);
        	n++;
        }
        
		return equipementQte;
    }
}