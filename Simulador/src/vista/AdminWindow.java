package vista;

import java.awt.BorderLayout;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
		this.listaUsuarios = new LinkedList<Usuario>();
		initWindow();
	}
	
	private void initWindow(){
		this.setTitle("Ventana Administrador");		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(200,150,500,250);
		
		JTabbedPane panel = new JTabbedPane();
		
		JComponent panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		initUsuarios(panel1);
		
		JComponent panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		initPanelCertificados(panel2);		
		
		panel.addTab("Menú Usuarios", panel1);
		panel.addTab("Menú Certificados", panel2);
		
		this.add(panel);
		this.setVisible(true);
	}
	
	private void initUsuarios(JComponent p){
		//Panel superior del menú usuarios		
		JPanel panelInfo = new JPanel();
		panelInfo.add(new JLabel("Lista de usuarios del Sistema"));
		
		p.add(panelInfo,BorderLayout.NORTH);		
		
		//Panel central del menú usuarios
		Usuario[] usuariosCombo = llenaLista();
		
		if (usuariosCombo!=null) this.comboUsuarios = new JComboBox<Usuario>(usuariosCombo);
		else this.comboUsuarios = new JComboBox<Usuario>();
		
		//initUsuariosSistema(this.comboUsuarios);
		
		JPanel p1 = new JPanel();
		p1.add(new JLabel("Lista de Usuarios: "));
		p1.add(this.comboUsuarios);
		
		p.add(p1,BorderLayout.CENTER);
		
		//Debajo deberían ir las opciones
		
	}
	
	private Usuario[] llenaLista() {
		// TODO Auto-generated method stub
		List<Usuario> aux = this.control.dameListaUsuarios();
		if (aux != null){
			Usuario[] resul = new Usuario[aux.size()];
			Iterator<Usuario> it = aux.iterator();
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
		
		panelCertificados.add("Menú Examen Práctico", panel3);
		panelCertificados.add("Menú Examen Teórico", panel4);
		
		p.add(panelCertificados, BorderLayout.CENTER);
	}
	
	private Certificacion[] llenaCertificados() {
		// TODO Auto-generated method stub
		List<Certificacion> aux = this.control.getListaCertificados();
		if (aux != null){
			Certificacion[] resul = new Certificacion[aux.size()];
			Iterator<Certificacion> it = aux.iterator();
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
