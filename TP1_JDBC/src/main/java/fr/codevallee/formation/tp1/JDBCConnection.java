package fr.codevallee.formation.tp1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.codevallee.formation.tp1.model.Plat;

public class JDBCConnection {

	public static List<Plat> getPlatsByStmtConnection() throws SQLException {
		List<Plat> plats = new ArrayList<Plat>();

		Connection conn = null;

		try {
			// Step 1: Allocate a database 'Connection' object
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?useSSL=false", "root", "1234");
			// MySQL: "jdbc:mysql://hostname:port/databaseName", "username", "password"

			// Step 3: Execute a SQL SELECT query, the query result is returned in a 'ResultSet' object.
			String strSelect = "SELECT platName, platTarif FROM plat";

			System.out.println("The SQL query is: " + strSelect); // Echo For debugging
			System.out.println();
			
			// Step 2: Allocate a 'Statement' object in the Connection
			Statement stmt = conn.createStatement();
			
			ResultSet rset = stmt.executeQuery(strSelect);

			// Step 4: Process the ResultSet by scrolling the cursor forward via next().
			// For each row, retrieve the contents of the cells with getXxx(columnName).
			System.out.println("The records selected are:");

			int rowCount = 0;
			while (rset.next()) { // Move the cursor to the next row, return false if no more row
				String name = rset.getString("platName");
				int tarif = rset.getInt("platTarif");
				
				System.out.println(name + ", " + tarif);
				
				plats.add(new Plat(name, tarif));

				++rowCount;
			}

			System.out.println("Total number of records = " + rowCount);

		} catch (SQLException e) {
			e.getStackTrace();
		}

		return plats;
	}

	public static List<Plat> getPlatsByPreparedStmtConnection(String tarif) throws SQLException {
		List<Plat> plats = new ArrayList<Plat>();

		Connection conn = null;

		try {
			// Step 1: Allocate a database 'Connection' object
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?useSSL=false", "root", "1234");
			// MySQL: "jdbc:mysql://hostname:port/databaseName", "username", "password"

			String strSelect = "SELECT platName FROM plat WHERE platTarif = ?";

			// Step 2: Allocate a 'Statement' object in the Connection
			PreparedStatement namesStmt = conn.prepareStatement(strSelect);
			namesStmt.setInt(1, Integer.valueOf(tarif));

			System.out.println("The SQL query is: " + namesStmt.toString()); // Echo For debugging
			System.out.println();

			// Step 3: Execute a SQL SELECT query, the query result is returned in a 'ResultSet' object.
			ResultSet rset = namesStmt.executeQuery();

			// Step 4: Process the ResultSet by scrolling the cursor forward via next().
			// For each row, retrieve the contents of the cells with getXxx(columnName).
			System.out.println("The records with the price = \' " + tarif + "\' are:");

			int rowCount = 0;
			boolean encore = rset.next();
			while (encore) { // Move the cursor to the next row, return false if no more row
				System.out.println(rset.getString(1));

				plats.add(new Plat(rset.getString(1), Integer.valueOf(tarif)));
				rowCount ++; 
				encore = rset.next();
			}
			rset.close();
			System.out.println("Total number of records = " + rowCount);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return plats;
	}
}
