package vista;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controlador.Controlador;
import logica.Usuario;

public class AdminWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame login;
	private Controlador control;
	private Usuario admin;

	public AdminWindow(JFrame v, Usuario us, Controlador control) {
		// TODO Auto-generated constructor stub
		this.control=control;
		this.login=v;
		this.admin=us;
		initWindow();
	}
	
	private void initWindow(){
		this.setTitle("Ventana Administrador");		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(200,150,500,250);
		
		JTabbedPane panel = new JTabbedPane();
		
		JComponent panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		//initSerie(panel1);
		
		panel.addTab("Menú Usuarios", panel1);
		
		this.add(panel);
		this.setVisible(true);
	}

}
