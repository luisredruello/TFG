package logica;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {

	 private final StringProperty nombre;
	 private final StringProperty pass;
	 private final boolean esAdmin;
	
	
	    /**
	     * Default constructor.
	     */
	    public Usuario() {
	        this(null, null, false);
	    }

	    public Usuario(String nombre, String contraseña, boolean type) {
	        this.nombre = new SimpleStringProperty(nombre);
	        this.pass = new SimpleStringProperty(contraseña);
	        this.esAdmin = type;
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
	        return pass.get();
	    }

	    public void setContraseña(String contraseña) {
	        this.pass.set(contraseña);
	    }

	    public StringProperty contraseñaProperty() {
	        return pass;
	    }

		public boolean isEsAdmin() {
			return esAdmin;
		}
	    
	    




}
