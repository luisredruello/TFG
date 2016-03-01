package controlador;

//import java.beans.PropertyVetoException;
import java.sql.*;
//import com.mchange.v2.c3p0.ComboPooledDataSource;
//import bd.Controlador;
//import gui.AdminWindow;

public class AdminMain {

	public static void main(String[] args) {
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
	        String url = "jdbc:oracle:thin:@localhost:1521/XE";

		    Connection conn = DriverManager.getConnection(url,"simulador","simulador");
		    
		    Controlador controla = new Controlador(conn);
 			//AdminWindow ventana = new AdminWindow(controla);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		catch(ClassNotFoundException c){
			c.printStackTrace();
		}

	  }
//
//	}

	

}
