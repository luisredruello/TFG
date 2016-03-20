package logica;

import java.sql.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {

	private final StringProperty DNI;
	private final Date fecha;
	private final StringProperty nombre;
	 private final StringProperty pass;
	 private final boolean esAdmin;
	
	
	    /**
	     * Default constructor.
	     */
	    public Usuario() {
	        this(null, null, null, null, 0);
	    }

	    public Usuario(String dni, Date f,String nombre, String contraseña, int type) {
	        this.nombre = new SimpleStringProperty(nombre);
	        this.pass = new SimpleStringProperty(contraseña);
	        this.fecha = f;
	        this.DNI = new SimpleStringProperty(dni);
	        if (type==0){
	        	esAdmin=false;
	        }
	        else esAdmin=true;
	    }

	    public StringProperty getDNI() {
			return DNI;
		}

		public Date getFecha() {
			return fecha;
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
