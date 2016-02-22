package logica;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {

	 private final StringProperty nombre;
	 private final StringProperty contrase�a;
	
	
	    /**
	     * Default constructor.
	     */
	    public Usuario() {
	        this(null, null);
	    }

	    public Usuario(String nombre, String contrase�a) {
	        this.nombre = new SimpleStringProperty(nombre);
	        this.contrase�a = new SimpleStringProperty(contrase�a);
	        
	        //habr�a que a�adir el tipo de usuario? --- de momento lo distingo en dos clases distintas
	    }

	    public String getNombre() {
	        return nombre.get();
	    }

	    public void setNombre(String nombre) {
	        this.nombre.set(nombre);
	    }

	    public StringProperty nombreProperty() {
	        return nombre;
	    }

	    public String getContrase�a() {
	        return contrase�a.get();
	    }

	    public void setContrase�a(String contrase�a) {
	        this.contrase�a.set(contrase�a);
	    }

	    public StringProperty contrase�aProperty() {
	        return contrase�a;
	    }




}
