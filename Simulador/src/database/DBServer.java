package database;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.codec.binary.Base64;

import logica.*;
import pdf.PDFReader;
import tools.Utilities;

public class DBServer implements DBInterface{
	
	
	private static final String URLPATH = "http://localhost:8888/Servidor/services/";
	
	public DBServer(){	}
	
	/**
	 * USUARIO
	 */
	
	public Usuario getUser(String dni,String pass){
		Usuario user = null;
		String uri = URLPATH+"user/get";
		try{
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Accept", "application/xml");
			
			StringBuffer queryParam = new StringBuffer();
	        queryParam.append("dni=");
	        queryParam.append(dni);
	        queryParam.append("&");
	        queryParam.append("pass=");
	        queryParam.append(pass);
	        
	        OutputStream output = connection.getOutputStream();
	        output.write(queryParam.toString().getBytes());
	        output.flush();
				 
			JAXBContext jc = JAXBContext.newInstance(Usuario.class);
			InputStream xml = connection.getInputStream();
			
			user = (Usuario) jc.createUnmarshaller().unmarshal(xml);
			
			connection.disconnect();
		}
		catch(IOException | JAXBException e){
			System.err.println("Los parámetros no son correctos");
		}
		return user;
	}

	@Override
	public List<Usuario> getUserList() {
		Usuarios users = null;
		String uri = URLPATH+"user/users";
		try{
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");
				 
			JAXBContext jc = JAXBContext.newInstance(Usuarios.class);
			InputStream xml = connection.getInputStream();
			
			users = (Usuarios) jc.createUnmarshaller().unmarshal(xml);
			
			connection.disconnect();
		}
		catch(IOException | JAXBException e){
			e.printStackTrace();
		}
		return users.getUsuarios();
	}
	
	@Override
	public int insertaUsuario(String name, String dni, String pass, Date f) {
		int code = 0;
		String uri = URLPATH+"user/add";
		try{
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			
			StringBuffer queryParam = new StringBuffer();
	        queryParam.append("nombre=");
	        queryParam.append(name);
	        queryParam.append("&");
	        queryParam.append("dni=");
	        queryParam.append(dni);
	        queryParam.append("&");
	        queryParam.append("pass=");
	        queryParam.append(pass);
	        
	        OutputStream output = connection.getOutputStream();
	        output.write(queryParam.toString().getBytes());
	        output.flush();
			
			code = (connection.getResponseCode()==200)?1:0;
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return code;
	}
	
	@Override
	public int deleteUsuario(String dni) {
		int resul = 0;
		String uri = URLPATH+"user/"+dni;
		try{
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("DELETE");
			
			resul = (connection.getResponseCode()==204)?1:0;
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return resul;
	}
	
	@Override
	public boolean existeUsuario(String dni) {
		boolean exist = false;
		String uri = URLPATH+"user/"+dni;
		try{
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			
			exist = (connection.getResponseCode()==200)?true:false;
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return exist;
	}
	
	@Override
	public int updateUsuario(Usuario user) {
		int resul = 0;
		String uri = URLPATH+"user/update/"+user.getDni();
		try{
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			
			StringBuffer queryParam = new StringBuffer();
	        queryParam.append("nombre=");
	        queryParam.append(user.getNombre_completo());
	        queryParam.append("&");
	        queryParam.append("pass=");
	        queryParam.append(user.getPass());
	        
	        OutputStream output = connection.getOutputStream();
	        output.write(queryParam.toString().getBytes());
	        output.flush();
			
			resul = (connection.getResponseCode()==204)?1:0;
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return resul;
	}
	
	
	/**
	 * CERTIFICACIONES
	 */

	@Override
	public List<Certificacion> getCertificados() {
		Certificaciones cert = null;
		String uri = URLPATH+"certificados";
		try{
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");
				 
			JAXBContext jc = JAXBContext.newInstance(Certificaciones.class);
			InputStream xml = connection.getInputStream();
			
			cert = (Certificaciones) jc.createUnmarshaller().unmarshal(xml);
			
			connection.disconnect();
		}
		catch(IOException | JAXBException e){
			e.printStackTrace();
		}
		return cert.getCertificados();
	}
	
	@Override
	public List<Integer> getCertificadosFromUser(String dni) {
		Certificaciones cert = null;
		String uri = URLPATH+"certificados/"+dni;
		try{
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");
				 
			JAXBContext jc = JAXBContext.newInstance(Certificaciones.class);
			InputStream xml = connection.getInputStream();
			
			cert = (Certificaciones) jc.createUnmarshaller().unmarshal(xml);
			
			connection.disconnect();
		}
		catch(JAXBException j){
			j.printStackTrace();
		}
		catch(IOException e){
			System.err.println("El usuario no tiene Certificaciones");
		}
		ArrayList<Integer> l = new ArrayList<Integer>();
		if (cert!=null){
			Iterator<Certificacion> it = cert.getCertificados().iterator();
			while(it.hasNext()){
				l.add(it.next().getNivel());
			}
		}		
		return l;
	}
	
	/**
	 * EXAMENES
	 */

	@Override
	public ExamenPractico getExamenPractico(int level) {
		ExamenPractico practico = null;
		String uri = URLPATH+"practico/"+level;
		try{
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");
				 
			JAXBContext jc = JAXBContext.newInstance(ExamenPractico.class);
			InputStream xml = connection.getInputStream();
			
			practico = (ExamenPractico) jc.createUnmarshaller().unmarshal(xml);
			
			connection.disconnect();
		}
		catch(JAXBException e){
			e.printStackTrace();
		}
		catch(IOException i){
			System.err.println("Esta certificación no tiene examen práctico");
		}
		return practico;
	}

	@Override
	public ExamenTeorico getExamenTeorico(int level) {
		ExamenTeorico teorico = null;
		String uri = URLPATH+"teorico/"+level;
		try{
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");
				 
			JAXBContext jc = JAXBContext.newInstance(ExamenTeorico.class);
			InputStream xml = connection.getInputStream();
			
			teorico = (ExamenTeorico) jc.createUnmarshaller().unmarshal(xml);
			
			connection.disconnect();
		}
		catch(IOException | JAXBException e){
			e.printStackTrace();
		}
		return teorico;
	}
	
	/**
	 * Devuelve un PDF Teorico desde el Web Service
	 */
	public String getPDFTeorico(int nivel, int id) {
		String uri = URLPATH+"teoria/pdf/"+nivel+"/"+id;
		String fileName = "pdf/c"+nivel+"/teoria"+id+".pdf";
        String filePath = null;
		try{
			filePath = PDFReader.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()
					+ fileName;
			
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			
			InputStream textBase64 = connection.getInputStream();

			OutputStream outputStream = new FileOutputStream(new File(filePath));
			IOUtils.copy(textBase64, outputStream);
			outputStream.close();
			
			connection.disconnect();
		}
		catch(URISyntaxException u){
			u.printStackTrace();
		}
		catch(IOException e){
			System.err.println("No existe el modulo en el Servidor");
		}
		return filePath;
	}
	
	@Override
	public int uploadPDFTeorico(int nivel, int idmodulo, byte[] files) {
		String uri = URLPATH+"teoria/upload/"+nivel+"/"+idmodulo;
		int resul = 0;
		try{
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			
			StringBuffer queryParam = new StringBuffer();
	        queryParam.append("file=");
	        queryParam.append(new String(Base64.encodeBase64(files)));
	        
	        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
	        connection.setRequestProperty("charset","UTF-8");
	        connection.setRequestProperty("Content-Length",Integer.toString(queryParam.toString().getBytes().length));
	        
	        OutputStream output = connection.getOutputStream();
	        output.write(queryParam.toString().getBytes());
	        output.flush();
			
			resul = (connection.getResponseCode()==200)?1:0;
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return resul;
	}	

	@Override
	public ModuloTeorico getModuloTeorico(int nivel, int id) {
		ModuloTeorico modulo = null;
		String uri = URLPATH+"teoria/"+nivel+"/"+id;
		try{
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");
			
			InputStream xml = connection.getInputStream();
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			IOUtils.copy(xml, baos);
			byte[] bytes = baos.toByteArray();

			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			
			modulo = new ModuloTeorico();
			modulo.setId_modulo(Integer.parseInt(Utilities.getAttributeXML(bais,"id_modulo")));
			bais = new ByteArrayInputStream(bytes);
			modulo.setNivel(Integer.parseInt(Utilities.getAttributeXML(bais, "nivel")));
			bais = new ByteArrayInputStream(bytes);
			String pdf = Utilities.getAttributeXML(bais, "pdf");
			if (!pdf.isEmpty())	modulo.setPdf(Base64.decodeBase64(pdf.getBytes()));
			else modulo.setPdf(null);
			
			connection.disconnect();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return modulo;
	}

	@Override
	public List<ModuloTeorico> getListModTeorico(int nivel) {
		ModulosTeoricos mods = null;
		String uri = URLPATH+"teoria/"+nivel;
		try{
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");
				 
			JAXBContext jc = JAXBContext.newInstance(ModulosTeoricos.class);
			InputStream xml = connection.getInputStream();
			
			mods = (ModulosTeoricos) jc.createUnmarshaller().unmarshal(xml);
			
			connection.disconnect();
		}
		catch(IOException | JAXBException e){
			e.printStackTrace();
		}
		return mods.getModulos();
	}

	@Override
	public List<Pregunta> getListaPreguntasFromExamen(int idExamen) {
		Preguntas preg = null;
		String uri = URLPATH+"pregunta/"+idExamen;
		try{
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");
				 
			JAXBContext jc = JAXBContext.newInstance(Preguntas.class);
			InputStream xml = connection.getInputStream();
			
			preg = (Preguntas) jc.createUnmarshaller().unmarshal(xml);
			
			connection.disconnect();
		}
		catch(IOException | JAXBException e){
			e.printStackTrace();
		}
		return preg.getPreguntas();
	}

	@Override
	public List<Respuesta> getListaRespuestasFromPregunta(int idPregunta) {
		Respuestas res = null;
		String uri = URLPATH+"respuesta/"+idPregunta;
		try{
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");
				 
			JAXBContext jc = JAXBContext.newInstance(Respuestas.class);
			InputStream xml = connection.getInputStream();
			
			res = (Respuestas) jc.createUnmarshaller().unmarshal(xml);
			
			connection.disconnect();
		}
		catch(IOException | JAXBException e){
			e.printStackTrace();
		}
		return res.getRespuestas();
	}

	@Override
	public int insertaAprobadoTeorico(String dni, int idExamenTeorico) {
		int code = 0;
		String uri = URLPATH+"teorico/aprueba";
		try{
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			
			StringBuffer queryParam = new StringBuffer();
	        queryParam.append("dni=");
	        queryParam.append(dni);
	        queryParam.append("&");
	        queryParam.append("id=");
	        queryParam.append(idExamenTeorico);
	        
	        OutputStream output = connection.getOutputStream();
	        output.write(queryParam.toString().getBytes());
	        output.flush();
			
			code = (connection.getResponseCode()==200)?1:0;
			
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return code;
	}
	
	@Override
	public int tieneAprobadoTeorico(String dni, int idExamen) {
		int code = 0;
		String uri = URLPATH+"user/tieneteorico";
		try{
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			
			StringBuffer queryParam = new StringBuffer();
	        queryParam.append("dni=");
	        queryParam.append(dni);
	        queryParam.append("&");
	        queryParam.append("id=");
	        queryParam.append(idExamen);
	        
	        OutputStream output = connection.getOutputStream();
	        output.write(queryParam.toString().getBytes());
	        output.flush();
			
			code = (connection.getResponseCode()==200)?1:0;
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return code;
	}

	@Override
	public int obtieneCertificacion(int level, String dni) {
		int code = 0;
		String uri = URLPATH+"certificados/obtiene";
		try{
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			
			StringBuffer queryParam = new StringBuffer();
	        queryParam.append("nivel=");
	        queryParam.append(level);
	        queryParam.append("&");
	        queryParam.append("dni=");
	        queryParam.append(dni);
	        
	        OutputStream output = connection.getOutputStream();
	        output.write(queryParam.toString().getBytes());
	        output.flush();
			
			code = (connection.getResponseCode()==200)?1:0;
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return code;
	}

	@Override
	public byte[] getImagenBytes(String tipo, int idImagen, int idExamen) {
		String uri = URLPATH+"imagen/"+tipo;
		byte[] bytes = null;
		try{			
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept", "image/png");
			connection.setDoOutput(true);
			
			StringBuffer queryParam = new StringBuffer();
	        queryParam.append("examen=");
	        queryParam.append(idExamen);
	        queryParam.append("&");
	        queryParam.append("id=");
	        queryParam.append(idImagen);
	        
	        OutputStream output = connection.getOutputStream();
	        output.write(queryParam.toString().getBytes());
	        output.flush();
			
			InputStream textBase64 = connection.getInputStream();
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			IOUtils.copy(textBase64, baos);
			bytes = baos.toByteArray();			
			
			connection.disconnect();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return bytes;
	}

	@Override
	public List<Imagen> getListImagesFromExam(int idExamen) {
		Imagenes imagenes = null;
		String uri = URLPATH+"imagen/imagenes/"+idExamen;
		try{
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");
				 
			JAXBContext jc = JAXBContext.newInstance(Imagenes.class);
			InputStream xml = connection.getInputStream();
			
			imagenes = (Imagenes) jc.createUnmarshaller().unmarshal(xml);
			
			connection.disconnect();
		}
		catch(IOException | JAXBException e){
			e.printStackTrace();
		}
		return imagenes.getImagenes();
	}

	@Override
	public ObjetoProhibido getObjetoProhibido(int idObjeto) {
		ObjetoProhibido prohibido = null;
		String uri = URLPATH+"prohibido/"+idObjeto;
		try{
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");
				 
			JAXBContext jc = JAXBContext.newInstance(ObjetoProhibido.class);
			InputStream xml = connection.getInputStream();
			
			prohibido = (ObjetoProhibido) jc.createUnmarshaller().unmarshal(xml);
			
			connection.disconnect();
		}
		catch(IOException | JAXBException e){
			e.printStackTrace();
		}
		return prohibido;
	}

	@Override
	public int insertaAprobadoPractico(String dni, int idExamenPractico) {
		int code = 0;
		String uri = URLPATH+"practico/aprueba";
		try{
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			
			StringBuffer queryParam = new StringBuffer();
	        queryParam.append("dni=");
	        queryParam.append(dni);
	        queryParam.append("&");
	        queryParam.append("id=");
	        queryParam.append(idExamenPractico);
	        
	        OutputStream output = connection.getOutputStream();
	        output.write(queryParam.toString().getBytes());
	        output.flush();
			
			code = (connection.getResponseCode()==200)?1:0;
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return code;
	}

	@Override
	public int insertaPregunta(String enunciado, int idExamen) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertaRespuesta(int idPregunta, String respuesta, int correcta) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
