package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import controlador.PDFReader;
import logica.Certificacion;
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
	private JComboBox<Certificacion> comboCertificados;
	private Certificacion certificado;
	private List<Certificacion> listaCertificados;

	public UserWindow(JFrame v, Usuario us, Controlador control) {
		this.login=v;
		this.user=us;
		this.control=control;
		initWindow();
	}
	
	private void initWindow(){
		this.setTitle("Bienvenido "+this.user.getNombre_completo());
		this.setLayout(new BorderLayout());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(120,50,700,400);
		
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
		
		//Certificados
		Certificacion[] cert = {new Certificacion(1), new Certificacion(2), new Certificacion(3)};
		this.comboCertificados = new JComboBox<Certificacion>(cert);
		
		JPanel bloqueCentral = new JPanel();
		bloqueCentral.add(comboCertificados);
		
		panelUsuario.add(bloqueSup,BorderLayout.NORTH);
		panelUsuario.add(bloqueCentral, BorderLayout.CENTER);
	}
	
	private void initTabePane() {
		cuadro = new JTabbedPane();
		
		//Cuadro de la parte teorica
		JComponent panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
				
		initTeoria(panel1);
				
		//Cuadro de la parta practica
		JComponent panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
				
		initPractica(panel2);

		cuadro.addTab("Teoría", panel1);
		cuadro.addTab("Práctica", panel2);
		
	}
	
	private void initTeoria(JComponent p1){
		p1.setLayout(new BorderLayout());
		
		//Panel izquierdo teoria
		JPanel iz = new JPanel();
		iz.setBorder(new TitledBorder("Zona Izquierda"));
		
		JButton leerTeoria = new JButton("Lee Teoría");
		leerTeoria.setBounds(80, 180, 87, 23);
		
		lanzaTeoria(leerTeoria);
		
		iz.add(leerTeoria);
		
		p1.add(iz,BorderLayout.WEST);
		
		//Panel derecho hacer examen
		JPanel der = new JPanel();
		der.setBorder(new TitledBorder("Zona Derecha"));
		
		JButton lanzaExamen = new JButton("Haz Examen");
		lanzaExamen.setBounds(80, 180, 90, 25);
		
		der.add(lanzaExamen);
		
		p1.add(der,BorderLayout.EAST);
	}
	
	private void initPractica(JComponent p2){
		
	}
	
	private void lanzaTeoria(JButton b){
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				PDFReader.readPDF("C:\\FAC029172.pdf");
				
			}
			
		});
	}
	
	
	private void cerrarSesion(JButton s,final JFrame p){
		s.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				p.dispose();
				login.setVisible(true);
			}
			
		});
	}

}
