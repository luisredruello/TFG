package logica;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {

	 private final StringProperty nombre;
	 private final StringProperty contraseña;
	
	
	    /**
	     * Default constructor.
	     */
	    public Usuario() {
	        this(null, null);
	    }

	    public Usuario(String nombre, String contraseña) {
	        this.nombre = new SimpleStringProperty(nombre);
	        this.contraseña = new SimpleStringProperty(contraseña);
	        
	        //habría que añadir el tipo de usuario? --- de momento lo distingo en dos clases distintas
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

	    public String getContraseña() {
	        return contraseña.get();
	    }

	    public void setContraseña(String contraseña) {
	        this.contraseña.set(contraseña);
	    }

	    public StringProperty contraseñaProperty() {
	        return contraseña;
	    }




}
