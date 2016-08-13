package vista;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import logica.Usuario;
import vista.admin.AdminWindow;
import vista.alumno.UserWindow;

public class LoginWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controlador control;
	private JPanel login;
	private JButton aceptar;
	private UserWindow ventanaAlumno;
	private AdminWindow ventanaAdmin;
	
	public LoginWindow(Controlador c){
		this.control = c;
		this.iniciaVentana();
	}
	
	private void iniciaVentana(){
		this.setTitle("Simulador");
		this.setLayout(new BorderLayout());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(300,250,500,300);
		
		aceptar = new JButton("Login");
		
		//Login
		login = new JPanel();
		login.setBorder(new TitledBorder("Login"));
		login.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel u1 = new JLabel("Usuario");
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		login.add(u1,c);
		
		JTextField user = new JTextField(10);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		login.add(user,c);
		
		JLabel p1 = new JLabel("Password");
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		login.add(p1,c);
		
		JPasswordField pass = new JPasswordField(10);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		login.add(pass,c);
		
		this.loginUsuario(this, aceptar, user, pass);
		
		//Panel de botones inferiores
		JPanel boton = new JPanel();
		boton.setBorder(new TitledBorder(""));
		boton.add(aceptar);		
		
		this.add(login,BorderLayout.CENTER);
		this.add(boton,BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	private void loginUsuario(final JFrame v,final JButton log,final JTextField user,final JPasswordField password){
		log.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String idUser = user.getText();
				char[] pass = password.getPassword();
				Usuario us = null;				
				if (!idUser.isEmpty() && pass.length > 0){
					//ver si existe el usuario en la BD
					//si existe abrir la ventana principal
					us = control.validaUsuario(idUser, new String(pass));
					if (us!=null) {						
						v.setVisible(false);
						//Miramos si el usuario es de tipo Administrador o Alumno
						if (us.getTipo()==0){
							ventanaAlumno = new UserWindow(v,us,control);
						}
						else{
							ventanaAdmin = new AdminWindow(v,us,control);
						}
						borraPass(pass);
						user.setText(null);
						password.setText(null);
					}
					else JOptionPane.showMessageDialog(log, "No existe el usuario");
				}
				else JOptionPane.showMessageDialog(log, "Datos Vacíos");
			}

			private void borraPass(char[] p) {
				for (int i=0;i<p.length;i++){
					p[i]=0;
				}
			}

		});
	}

}
