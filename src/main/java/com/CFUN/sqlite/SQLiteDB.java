package com.CFUN.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLiteDB {
	public static void main(String[] args) throws Exception {
	
        Class.forName("org.sqlite.JDBC");
        Connection conn = DriverManager.getConnection("jdbc:sqlite:equipement.db");
        Statement stat = conn.createStatement();
        stat.executeUpdate("drop table if exists equipement;");
        stat.executeUpdate("create table equipement (nom, type, quantite);");
        PreparedStatement prep = conn.prepareStatement(
            "insert into equipement values (?, ?, ?);");

        prep.setString(1, "velo");
        prep.setString(2, "fit");
        prep.setString(3, "14");
        prep.addBatch();
        prep.setString(1, "haltere");
        prep.setString(2, "muscu");
        prep.setString(3, "11");
        prep.addBatch();
        prep.setString(1, "tapis");
        prep.setString(2, "fit");
        prep.setString(3, "7");
        prep.addBatch();

        conn.setAutoCommit(false);
        prep.executeBatch();
        conn.setAutoCommit(true);

        //System.out.println("test =" + prep.getString("nom"));
        
        ResultSet rs = stat.executeQuery("select * from equipement;");
        while (rs.next()) {
            System.out.println("nom = " + rs.getString("nom"));
            System.out.println("type = " + rs.getString("type"));
            System.out.println("quantite = " + rs.getString("quantite"));
        }
        
        
        //Récupérer un exemple dans le tableau
        int [] tabDB = {  };
        for(int i = 0; i < tabDB.length; i++) {
        	//System.out.println(tabDB[i]);
        }
        rs.close();
        conn.close();
    }
}
