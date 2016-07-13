package database;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import logica.Usuario;

public class DBServer implements DBInterface{
	
	public DBServer(){
		
	}
	
	public Usuario getUser(String name,String pass){
		Usuario user = new Usuario();
		String uri = 
			    "http://localhost:8888/Servidor/services/user/"+name+"-"+pass;
		try{
			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
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

}
