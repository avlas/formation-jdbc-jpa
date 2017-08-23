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
	private static List<Plat> plats = new ArrayList<Plat>();

	private static String selectStmt = "SELECT platName, platTarif FROM plat";
	private static String selectPreparedStmt = "SELECT platName FROM plat WHERE platTarif = ?";
	private static ResultSet rset;

	public static Connection getInstance() {
		// Step 1: Allocate a database 'Connection' object
		// MySQL: "jdbc:mysql://hostname:port/databaseName", "username", "password"
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?useSSL=false", "root", "1234");
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return conn;
	}

	public static List<Plat> getPlatsByStmt() {
		try {
			// System.out.println("The SQL query is: " + selectStmt);
			// System.out.println();

			// Step 2: Allocate a 'Statement' object in the Connection
			Statement stmt = JDBCConnection.getInstance().createStatement();

			// Step 3: Execute a SQL SELECT query, the query result is returned in a 'ResultSet' object.
			ResultSet rset = stmt.executeQuery(selectStmt);

			// System.out.println("The records selected are:");

			// Step 4: Process the ResultSet by scrolling the cursor forward via next().
			// For each row, retrieve the contents of the cells with getXxx(columnName).
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
		} finally {
			try {
				JDBCConnection.getInstance().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return plats;
	}

	public static List<Plat> getPlatsByPreparedStmt(String tarif) {
		try {
			// Step 2: Allocate a 'Statement' object in the Connection
			PreparedStatement namesPreparedStmt = JDBCConnection.getInstance().prepareStatement(selectPreparedStmt);
			namesPreparedStmt.setInt(1, Integer.valueOf(tarif));

			rset = namesPreparedStmt.executeQuery();

			int rowCount = 0;
			while (rset.next()) { 
				System.out.println(rset.getString(1));
				 
				plats.add(new Plat(rset.getString(1), Integer.valueOf(tarif)));
				++rowCount;
			}
			System.out.println("Total number of records = " + rowCount);
		} catch (SQLException e) {
			e.getStackTrace();
		} finally {
			try {
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return plats;
	}
}
