package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import logica.Usuario;

public class UserWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelUsuario;
	private JTabbedPane cuadro;
	private JFrame login;
	private Usuario user;
	private Controlador control;

	public UserWindow(JFrame v, Usuario us, Controlador control) {
		// TODO Auto-generated constructor stub
		this.login=v;
		this.user=us;
		this.control=control;
		initWindow();
	}
	
	private void initWindow(){
		this.setTitle("Bienvenido "+this.user.getNombre_completo());
		this.setLayout(new BorderLayout());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(120,50,700,600);
		
		initUser();		
		initTabePane();
		
		JPanel salir = new JPanel();
		JButton bSalir = new JButton("Cerrar Sesión");		
		cerrarSesion(bSalir,this);
		salir.add(bSalir);
		
		this.add(panelUsuario,BorderLayout.NORTH);
		this.add(cuadro,BorderLayout.CENTER);
		this.add(salir,BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	private void initTabePane() {
		// TODO Auto-generated method stub
		cuadro = new JTabbedPane();
		
		//Cuadro de busqueda de series
		JComponent panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
				
		//initBusquedaSerie(panel1);
				
		//Cuadro de series que sigue el usuario
		JComponent panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
				
		//initMisSeries(panel2);

		cuadro.addTab("Panel 1", panel1);
		cuadro.addTab("Panel 2", panel2);
		
	}

	private void initUser(){
		panelUsuario = new JPanel();
		panelUsuario.setBorder(new TitledBorder("Datos Usuario"));
		panelUsuario.setLayout(new BorderLayout());
		
		JPanel bloqueSup = new JPanel();				
		JLabel nick = new JLabel("Nombre: " + this.user.getNombre_completo());
		String dni = this.user.getDni();
		JLabel carnet = new JLabel("DNI: "+dni);
		
		bloqueSup.add(nick);
		bloqueSup.add(carnet);
		
		panelUsuario.add(bloqueSup,BorderLayout.NORTH);
	}
	
	private void cerrarSesion(JButton s,final JFrame p){
		s.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				p.dispose();
				login.setVisible(true);
			}
			
		});
	}

}
