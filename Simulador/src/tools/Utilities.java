package tools;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Utilities {
	
	/**
	 * Crea una nueva fecha a partir de la fecha del sistema
	 * @return
	 */
	public static Date getSystemDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String fecha = dateFormat.format(cal.getTime());
		java.util.Date date = null;
		
		try{
			date = dateFormat.parse(fecha);			
		}
		catch(ParseException p){
			p.printStackTrace();
		}
		return new Date(date.getTime());
	}
	
	/**
	 * Cifra un password con el Algoritmo MD5
	 * @param cadena
	 * @return
	 */
	public static String md5(String cadena){
        String encrypt = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(cadena.getBytes(Charset.forName("UTF-8")));
            encrypt = String.format(Locale.ROOT, "%032x", new BigInteger(1, md.digest()));
            return encrypt;
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return "";
    }
	
	/**
	 * Devuelve el atributo de un xml pasado por parametro
	 * @param xml InputStream que contiene al xml a parsear
	 * @param att Strig que representa el nodo que queremos que nos devuelva el metod
	 * @return El atributo del xml pasado por parámetro
	 */
	public static String getAttributeXML(InputStream xml,String att){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        NodeList list = null;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xml);
            list = doc.getElementsByTagName(att);
            System.out.println(list.item(0).getTextContent());
        } catch (ParserConfigurationException e) {
        	e.printStackTrace();
        } catch (SAXException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
		return list.item(0).getTextContent();
	}

}
