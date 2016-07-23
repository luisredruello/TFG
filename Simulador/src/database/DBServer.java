package database;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import logica.*;

public class DBServer implements DBInterface{
	
	public DBServer(){
		
	}
	
	public Usuario getUser(String name,String pass){
		Usuario user = null;
		String uri = 
			    "http://localhost:8888/Servidor/services/user/"+name+"-"+pass;
		try{
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept", "application/xml");
				 
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
			    "http://localhost:8888/Servidor/services/user/users";
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
		catch(IOException | JAXBException e){//MalformedURLException
			e.printStackTrace();
		}
		return users.getUsuarios();
	}

	@Override
	public List<Certificacion> getCertificados() {
		Certificaciones cert = null;
		String uri = "http://localhost:8888/Servidor/services/certificados";
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
	public ExamenPractico getExamenPractico(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExamenTeorico getExamenTeorico(int level) {
		// TODO Auto-generated method stub
		return null;
	}

}
