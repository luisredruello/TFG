package database;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.Statement;

import logica.Certificacion;
import logica.ExamenPractico;
import logica.ExamenTeorico;
import logica.Imagen;
import logica.ModuloTeorico;
import logica.ObjetoProhibido;
import logica.Pregunta;
import logica.Respuesta;
import logica.TipoArma;
import logica.Usuario;

public class DBLocal implements DBInterface{
	
	private Connection connection;
	
	public DBLocal(){	}
	
	public Usuario getUser(String user,String pass){
		PreparedStatement pst = null;
		ResultSet rs = null;
		Usuario result	= null;
		String query = null;
		try{
			this.connection = DBConnection.getConnection();
			query = "select DNI,Fecha,Nombre_Completo,Tipo,Pass from USUARIO where DNI=? and PASS=?";
			pst = this.connection.prepareStatement(query);
			pst.setString(1, user);
			pst.setString(2, pass);
			rs = pst.executeQuery();
			if (rs.next()){
				String dni = rs.getString("DNI");
				String name = rs.getString("Nombre_Completo");
				String p = rs.getString("Pass");
				String f = rs.getDate("Fecha").toString();
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
	
	@Override
	public int insertaUsuario(String name, String dni, String pass, Date f) {
		PreparedStatement pst = null;
		ResultSet rs          = null;
		String query = null;
		int resul = 0;
		try{
			this.connection = DBConnection.getConnection();
			query = "insert into USUARIO (DNI,Fecha,Nombre_Completo,Tipo,Pass) values (?,?,?,?,?)";
			pst = this.connection.prepareStatement(query);
			pst.setString(1, dni);
			pst.setDate(2, f);
			pst.setString(3, name);
			pst.setInt(4, 0);
			pst.setString(5, pass);
			resul = pst.executeUpdate();			
		}
		catch(SQLException e){
			e.printStackTrace();
		} finally {
			try{
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (this.connection != null) this.connection.close();				
			} catch (Exception e) {}
		}
		return resul;
	}
	
	@Override
	public int deleteUsuario(String dni) {
		PreparedStatement pst = null;
		ResultSet rs          = null;
		String query = null;
		int resul = 0;
		try{
			this.connection = DBConnection.getConnection();
			query = "delete from usuario where dni=?";
			pst = this.connection.prepareStatement(query);
			pst.setString(1, dni);
			resul = pst.executeUpdate();			
		}
		catch(SQLException e){
			e.printStackTrace();
		} finally {
			try{
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (this.connection != null) this.connection.close();				
			} catch (Exception e) {}
		}
		return resul;
	}
	
	@Override
	public boolean existeUsuario(String dni) {
		PreparedStatement pst = null;
		ResultSet rs          = null;
		String query = null;
		boolean resul = false;
		try{
			this.connection = DBConnection.getConnection();
			query = "select nombre_completo from usuario where dni=?";
			pst = this.connection.prepareStatement(query);
			pst.setString(1, dni);
			rs = pst.executeQuery();
			resul = rs.next();
		}
		catch(SQLException e){
			e.printStackTrace();
		} finally {
			try{
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (this.connection != null) this.connection.close();				
			} catch (Exception e) {}
		}
		return resul;
	}
	
	@Override
	public int updateUsuario(Usuario user) {
		PreparedStatement pst = null;
		ResultSet rs          = null;
		String query = null;
		int resul = 0;
		try{
			this.connection = DBConnection.getConnection();
			query = "update usuario set nombre_completo=?,pass=? where dni=?";
			pst = this.connection.prepareStatement(query);
			pst.setString(1, user.getNombre_completo());
			pst.setString(2, user.getPass());
			pst.setString(3, user.getDni());
			resul = pst.executeUpdate();			
		}
		catch(SQLException e){
			e.printStackTrace();
		} finally {
			try{
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (this.connection != null) this.connection.close();				
			} catch (Exception e) {}
		}
		return resul;
	}
	
	@Override
	public List<Integer> getCertificadosFromUser(String dni) {
		Connection con        = null;
		PreparedStatement pst = null;
		ResultSet rs          = null;
		List<Integer> resul = new LinkedList<Integer>();
		try{
			con = DBConnection.getConnection();
			String sql = "select nivel from tiene where dni=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, dni);
			rs = pst.executeQuery();
			while (rs.next()) {
				int t = rs.getInt("Nivel");
				resul.add(t);
			}
			return resul;
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) {}
		}
		return null;
	}

	@Override
	public List<Usuario> getUserList() {
		Connection con        = null;
		PreparedStatement pst = null;
		ResultSet rs          = null;
		List<Usuario> resul = new LinkedList<Usuario>();
		try{
			con = DBConnection.getConnection();
			String sql = "SELECT * FROM usuario";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next() && rs.getInt("Tipo")==0) {
				String dni = rs.getString("DNI");
				String name = rs.getString("Nombre_Completo");
				String p = rs.getString("Pass");
				String f = rs.getDate("Fecha").toString();
				int tipo = rs.getInt("Tipo");
				resul.add(new Usuario(dni,f,name,p,tipo));
			}
			return resul;
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) {}
		}
		return null;
	}

	@Override
	public List<Certificacion> getCertificados() {
		Connection con        = null;
		PreparedStatement pst = null;
		ResultSet rs          = null;
		List<Certificacion> resul = new LinkedList<Certificacion>();
		try{
			con = DBConnection.getConnection();
			String sql = "SELECT * FROM certificacion";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				int t = rs.getInt("Nivel");
				resul.add(new Certificacion(t));
			}
			return resul;
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) {}
		}
		return null;
	}

	@Override
	public ExamenPractico getExamenPractico(int level) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ExamenPractico result	= null;
		String query = null;
		try{
			this.connection = DBConnection.getConnection();
			query = "select Id_Examen,num_imagenes,Tiempo_Examen from EXAMEN_PRACTICO where Nivel=?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, level);
			rs = pst.executeQuery();
			if (rs.next()){
				int id = rs.getInt("Id_Examen");
				int ima = rs.getInt("num_imagenes");
				int t = rs.getInt("Tiempo_Examen");
				result = new ExamenPractico(id,level,ima,t);
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

	@Override
	public ExamenTeorico getExamenTeorico(int level) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ExamenTeorico result	= null;
		String query = null;
		try{
			this.connection = DBConnection.getConnection();
			query = "select Id_Examen,Nombre,Descripcion,Tiempo_Examen,num_preguntas from EXAMEN_TEORICO where Nivel=?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, level);
			rs = pst.executeQuery();
			if (rs.next()){
				int id = rs.getInt("Id_Examen");
				String name = rs.getString("Nombre");
				String d = rs.getString("Descripcion");
				int tiempo = rs.getInt("Tiempo_Examen");
				int num = rs.getInt("num_preguntas");
				result = new ExamenTeorico(id,level,name,d,tiempo,num);
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

	
	@Override
	public int uploadPDFTeorico(int nivel, int idmodulo, byte[] files) {
		PreparedStatement pst = null;
		ResultSet rs          = null;
		String query = null;
		int resul = 0;
		Blob blob = null;
		try{
			this.connection = DBConnection.getConnection();
			query = "insert into modulo_teorico (Id_Modulo,Nivel,PDF) values (?,?,?)";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, idmodulo);
			pst.setInt(2, nivel);
			if (files!=null){
				blob = this.connection.createBlob();
				blob.setBytes(1, files);
				pst.setBlob(3, blob);
			}
			else pst.setBlob(3, blob);
			resul = pst.executeUpdate();		
		}
		catch(SQLException e){
			e.printStackTrace();
		} finally {
			try{
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (this.connection != null) this.connection.close();				
			} catch (Exception e) {}
		}
		return resul;
	}	

	@Override
	public ModuloTeorico getModuloTeorico(int nivel, int id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ModuloTeorico result	= null;
		String query = null;
		try{
			this.connection = DBConnection.getConnection();
			query = "select Id_Modulo,Nivel,PDF from modulo_teorico where Nivel=? and Id_Modulo=?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, nivel);
			pst.setInt(2, id);
			rs = pst.executeQuery();
			if (rs.next()){
				int idmod = rs.getInt("Id_Modulo");
				int l = rs.getInt("Nivel");
				Blob p = rs.getBlob("PDF");
				if (!rs.wasNull()){
					byte[] pd = p.getBytes(1, (int)p.length());
					result = new ModuloTeorico(idmod,l,pd);
				}
				else result = new ModuloTeorico(idmod,l);
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

	@Override
	public List<ModuloTeorico> getListModTeorico(int nivel) {
		Connection con        = null;
		PreparedStatement pst = null;
		ResultSet rs          = null;
		List<ModuloTeorico> resul = new LinkedList<ModuloTeorico>();
		try{
			con = DBConnection.getConnection();
			String sql = "select Id_Modulo,PDF from modulo_teorico where Nivel=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, nivel);
			rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("Id_Modulo");
				Blob b = rs.getBlob("PDF");
				if (!rs.wasNull()){
					byte[] pdf = b.getBytes(1, (int)b.length());
					resul.add(new ModuloTeorico(id,nivel,pdf));
				}
				else resul.add(new ModuloTeorico(id,nivel));
			}
			return resul;
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) {}
		}
		return null;
	}

	@Override
	public List<Pregunta> getListaPreguntasFromExamen(int idExamen) {
		Connection con        = null;
		PreparedStatement pst = null;
		ResultSet rs          = null;
		List<Pregunta> resul = new LinkedList<Pregunta>();
		try{
			con = DBConnection.getConnection();
			String sql = "select Id_Pregunta,Enunciado from pregunta where Id_Examen=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, idExamen);
			rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("Id_Pregunta");
				String e = rs.getString("Enunciado");
				resul.add(new Pregunta(id,e,idExamen));
			}
			return resul;
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) {}
		}
		return null;
	}

	@Override
	public List<Respuesta> getListaRespuestasFromPregunta(int idPregunta) {
		Connection con        = null;
		PreparedStatement pst = null;
		ResultSet rs          = null;
		List<Respuesta> resul = new LinkedList<Respuesta>();
		try{
			con = DBConnection.getConnection();
			String sql = "select Id_Respuesta,Es_Correcta,Enunciado from respuesta where Id_Pregunta=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, idPregunta);
			rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("Id_Respuesta");
				int c = rs.getInt("Es_Correcta");
				String e = rs.getString("Enunciado");
				resul.add(new Respuesta(id,c,e,idPregunta));
				
			}
			return resul;
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (con != null) con.close();
			} catch (Exception e) {}
		}
		return null;
	}

	@Override
	public int insertaAprobadoTeorico(String dni, int idExamenTeorico) {
		PreparedStatement pst = null;
		ResultSet rs          = null;
		String query = null;
		int resul = 0;
		try{
			this.connection = DBConnection.getConnection();
			query = "insert into aprueba_teorico (Id_Examen_Teorico,DNI,Fecha) values (?,?,?)";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, idExamenTeorico);
			pst.setString(2, dni);
			pst.setDate(3, null);
			resul = pst.executeUpdate();			
		}
		catch(SQLException e){
			e.printStackTrace();
		} finally {
			try{
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (this.connection != null) this.connection.close();				
			} catch (Exception e) {}
		}
		return resul;
	}
	
	@Override
	public int tieneAprobadoTeorico(String dni, int idExamenTeorico) {
		PreparedStatement pst = null;
		ResultSet rs          = null;
		String query = null;
		int resul = 0;
		try{
			this.connection = DBConnection.getConnection();
			query = "select dni from aprueba_teorico a join examen_teorico b "
					+ "where a.Id_Examen_Teorico=b.Id_Examen and a.Id_Examen_Teorico=? and a.DNI=?";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, idExamenTeorico);
			pst.setString(2, dni);
			rs = pst.executeQuery();
			if (rs.next()) resul=1;
		}
		catch(SQLException e){
			e.printStackTrace();
		} finally {
			try{
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (this.connection != null) this.connection.close();				
			} catch (Exception e) {}
		}
		return resul;
	}
	
	
	@Override
	public int tieneAprobadoPractico(String dni, int idExamenPractico) {
		return 0;
	}

	@Override
	public int obtieneCertificacion(int level, String dni) {
		PreparedStatement pst = null;
		ResultSet rs          = null;
		String query = null;
		int resul = 0;
		try{
			this.connection = DBConnection.getConnection();
			query = "insert into tiene (Nivel,DNI) values (?,?)";
			pst = this.connection.prepareStatement(query);
			pst.setInt(1, level);
			pst.setString(2, dni);
			resul = pst.executeUpdate();			
		}
		catch(SQLException e){
			e.printStackTrace();
		} finally {
			try{
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (this.connection != null) this.connection.close();				
			} catch (Exception e) {}
		}
		return resul;
	}

	@Override
	public byte[] getImagenBytes(String tipo, int idImagen, int idExamen) {
		return null;
	}

	@Override
	public List<Imagen> getListImagesFromExam(int idExamen) {
		return null;
	}

	@Override
	public ObjetoProhibido getObjetoProhibido(int idObjeto) {
		return null;
	}

	@Override
	public int insertaAprobadoPractico(String dni, int idExamenPractico) {
		return 0;
	}

	@Override
	public int insertaPregunta(String enunciado, int idExamen) {
		PreparedStatement pst = null;
		ResultSet rs          = null;
		String query = null;
		int resul = 0;
		int lastid = 0;
		try{
			this.connection = DBConnection.getConnection();
			query = "insert into pregunta (Enunciado,Id_Examen) values (?,?)";
			pst = this.connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, enunciado);
			pst.setInt(2, idExamen);
			resul = pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next() && resul>0){
				lastid = rs.getInt(1);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		} finally {
			try{
				if (rs != null) rs.close();
				if (pst != null) pst.close();
				if (this.connection != null) this.connection.close();				
			} catch (Exception e) {}
		}
		return lastid;
	}

	@Override
	public int insertaRespuesta(int idPregunta, String respuesta, int correcta) {
		return 0;
	}

	@Override
	public TipoArma getTipoArma(int idArma) {
		return null;
	}

	@Override
	public List<TipoArma> getListaTipoArma() {
		return null;
	}

	@Override
	public int insertaImagenLimpia(int idExamenPractico, byte[] normal, byte[] bn, byte[] organ, byte[] inorgan) {
		return 0;
	}

	@Override
	public int insertaImagenProhibido(int idPractico, byte[] normal, byte[] bn, byte[] organ, byte[] inorgan, int x,
			int y, int ancho, int alto, int idTipoArma) {
		return 0;
	}

	

}
