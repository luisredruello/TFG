package controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import database.DBConnection;
import logica.Usuario;


public class Controlador {
	
	private Connection connection;

	public Controlador(DataSource ds){ //acceso a base de datos/servidor web
		
	}
	
	public Controlador(Connection c){
		this.connection=c;
	}
	
	public Controlador(){
		
	}
	
	public Usuario validaUsuario(String user,String pass){
		PreparedStatement pst = null;
		ResultSet rs = null;
		Usuario result	= null;
		String query = null;
		try{
			this.connection = DBConnection.getConnection();
			query = "select DNI,Fecha,Nombre_Completo,Tipo,Pass from USUARIO where DNI=? and Pass=?";
			pst = this.connection.prepareStatement(query);
			pst.setObject(1, user);
			pst.setObject(2, pass);
			rs = pst.executeQuery();
			if (rs.next()){
				String dni = rs.getString("DNI");
				String name = rs.getString("Nombre_Completo");
				String p = rs.getString("Pass");
				Date f = rs.getDate("Fecha");
				int tipo = rs.getInt("Tipo");
				result = new Usuario(dni,f,name,p,tipo);
			}			
		}
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (this.connection != null) this.connection.close();
			} catch (Exception e) {}
		}
		return result;
	}
	
}
