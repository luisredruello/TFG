package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controlador.Controlador;
import logica.Certificacion;
import logica.Usuario;

public class AdminWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame login;
	private Controlador control;
	private Usuario admin;
	private List<Usuario> listaUsuarios;
	private JComboBox<Usuario> comboUsuarios;
	private List<Certificacion> listaCertificados;
	private JComboBox<Certificacion> comboCertificados;

	public AdminWindow(JFrame v, Usuario us, Controlador control) {
		// TODO Auto-generated constructor stub
		this.control=control;
		this.login=v;
		this.admin=us;
		this.listaUsuarios = null;
		this.listaCertificados = null;
		initWindow();
	}
	
	private void initWindow(){
		this.setTitle("Bienvenido Administrador "+this.admin.getNombre_completo());		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(200,150,500,250);
		
		JTabbedPane panel = new JTabbedPane();
		
		JComponent panel1 = new JPanel();
		panel1.setLayout(new GridLayout(3,1));
		initUsuarios(panel1);
		
		JComponent panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		initPanelCertificados(panel2);		
		
		panel.addTab("Men� Usuarios", panel1);
		panel.addTab("Men� Certificados", panel2);
		
		this.add(panel);
		this.setVisible(true);
	}
	
	private void initUsuarios(JComponent p){
		//Panel superior del men� usuarios		
		JPanel panelInfo = new JPanel();
		panelInfo.add(new JLabel("Lista de usuarios del Sistema"));
		
		p.add(panelInfo);		
		
		//Panel central del men� usuarios
		Usuario[] usuariosCombo = llenaLista();
		
		if (usuariosCombo!=null) this.comboUsuarios = new JComboBox<Usuario>(usuariosCombo);
		else this.comboUsuarios = new JComboBox<Usuario>();
		
		//initUsuariosSistema(this.comboUsuarios);
		
		JPanel p1 = new JPanel();
		p1.add(new JLabel("Lista de Usuarios: "));
		p1.add(this.comboUsuarios);
		
		p.add(p1);
		
		//Opciones de usuario
		JPanel panelOpcionesUsuario = new JPanel();
		
		//Agregar usuario
		JButton botonAddUser = new JButton("Agregar");
		agregaUsuario(botonAddUser);
		
		panelOpcionesUsuario.add(botonAddUser);
		
		//Borrar usuario
		JButton botonDeleteUser = new JButton("Borrar");
		borraUsuario(botonDeleteUser,this.comboUsuarios);
		
		panelOpcionesUsuario.add(botonDeleteUser);
		
		//Actualizar usuario
		JButton botonUpdateUser = new JButton("Actualizar");
		updateUsuario(botonUpdateUser,this.comboUsuarios);
		
		panelOpcionesUsuario.add(botonUpdateUser);
		
		p.add(panelOpcionesUsuario);
		
	}
	
	private void updateUsuario(JButton botonUpdateUser, JComboBox<Usuario> comboUsuarios2) {
		// TODO Auto-generated method stub
		
	}

	private void borraUsuario(JButton botonDeleteUser, JComboBox<Usuario> comboUsuarios2) {
		// TODO Auto-generated method stub
		
	}

	private void agregaUsuario(JButton botonAddUser) {
		// TODO Auto-generated method stub
		
	}

	private Usuario[] llenaLista() {
		// TODO Auto-generated method stub
		this.listaUsuarios = this.control.dameListaUsuarios();
		if (listaUsuarios != null){
			Usuario[] resul = new Usuario[listaUsuarios.size()];
			Iterator<Usuario> it = listaUsuarios.iterator();
			int i=0;
			while(it.hasNext()){
				resul[i] = it.next();
				i++;
			}
			return resul;
		}
		else return null;
	}
	
	
	private void initPanelCertificados(JComponent p){
		//Panel superior
		Certificacion[] certificadosCombo = llenaCertificados();
		
		if (certificadosCombo!=null) this.comboCertificados = new JComboBox<Certificacion>(certificadosCombo); 
		else this.comboCertificados = new JComboBox<Certificacion>();
		
		JPanel panelInfo = new JPanel();
		panelInfo.add(new JLabel("Certificados: "));
		panelInfo.add(comboCertificados);
		
		
		p.add(panelInfo,BorderLayout.NORTH);
		
		JTabbedPane panelCertificados = new JTabbedPane();
		
		JComponent panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		//initExamenPractioc(panel3);
		
		JComponent panel4 = new JPanel();
		panel4.setLayout(new BorderLayout());
		//initExamenTeorico(panel4);
		
		panelCertificados.add("Men� Examen Pr�ctico", panel3);
		panelCertificados.add("Men� Examen Te�rico", panel4);
		
		p.add(panelCertificados, BorderLayout.CENTER);
	}
	
	private Certificacion[] llenaCertificados() {
		// TODO Auto-generated method stub
		this.listaCertificados = this.control.getListaCertificados();
		if (listaCertificados != null){
			Certificacion[] resul = new Certificacion[listaCertificados.size()];
			Iterator<Certificacion> it = listaCertificados.iterator();
			int i=0;
			while(it.hasNext()){
				resul[i] = it.next();
				i++;
			}
			return resul;
		}
		else return null;
	}

}
