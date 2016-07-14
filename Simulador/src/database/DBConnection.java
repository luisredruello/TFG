package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	
	public static Connection getConnection() {
		Properties props = new Properties();
		FileInputStream fis = null;
		Connection con = null;
		try {
			fis = new FileInputStream("db.properties");
			props.load(fis);

			// load the Driver Class
			Class.forName(props.getProperty("ORACLE_DB_DRIVER_CLASS"));

			// create the connection now
			con = DriverManager.getConnection(props.getProperty("ORACLE_DB_URL"),
					props.getProperty("ORACLE_DB_USERNAME"),
					props.getProperty("ORACLE_DB_PASSWORD"));
		} catch (IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

}
