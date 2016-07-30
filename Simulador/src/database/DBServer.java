package database;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;

import logica.*;
import pdf.PDFReader;

public class DBServer implements DBInterface{
	
	
	private static final String URLPATH = "http://localhost:8888/Servidor/services/";
	
	public DBServer(){
		
	}
	
	/**
	 * USUARIO
	 */
	
	public Usuario getUser(String dni,String pass){
		Usuario user = null;
		String uri = 
				URLPATH+"user/get";
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
		catch(IOException | JAXBException e){//MalformedURLException
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<Usuario> getUserList() {
		Usuarios users = null;
		String uri = 
				URLPATH+"user/users";
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
		String uri = 
				URLPATH+"user/add";
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
	
	/**
	 * EXAMENES
	 */

	@Override
	public ExamenPractico getExamenPractico(int level) {
		ExamenPractico practico = null;
		String uri = URLPATH+"examen/practico/"+level;
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
		String uri = URLPATH+"examen/teorico/"+level;
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
	 * Devuelve un modulo teórico desde el Web Service
	 */

	@Override
	public String getModuloTeorico(int nivel, int id) {
		String uri = URLPATH+"teoria/"+nivel+"/"+id;
		String fileName = "pdf/c"+nivel+"/teoria"+id+".pdf";
        String filePath = null;
        byte[] fileBytes = null;
		try{
			filePath = PDFReader.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()
					+ fileName;
			
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			
			InputStream textBase64 = connection.getInputStream();
			
			fileBytes = IOUtils.toByteArray(textBase64);
			
			FileOutputStream fos = new FileOutputStream(filePath);
            BufferedOutputStream outputStream = new BufferedOutputStream(fos);
            outputStream.write(fileBytes);
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

	

}
