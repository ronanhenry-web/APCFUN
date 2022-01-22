package com.CFUN.sqlite;

import java.sql.Connection;
import java.sql.SQLException;

import org.sqlite.SQLiteDataSource;

public class ConnectDB {
	//Test pour générer et ouvrir la database
	public static void main(String[] args) {
		SQLiteDataSource ds = null;

		try {
			ds = new SQLiteDataSource();
			// Nom fichier
			ds.setUrl("jdbc:sqlite:database.db");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("Opened database successfully");

		try (Connection conn = ds.getConnection()) {
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("Created database successfully");
	}
}
