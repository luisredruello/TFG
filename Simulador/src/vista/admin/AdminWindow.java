package vista.admin;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controlador.Controlador;
import logica.*;

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

	public AdminWindow(JFrame v, Usuario us, Controlador control) {
		this.control=control;
		this.login=v;
		this.admin=us;
		this.listaUsuarios = null;
		initWindow();
	}
	
	private void initWindow(){
		this.setTitle("Bienvenido Administrador "+this.admin.getNombre_completo());		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(200,150,600,300);
		
		JTabbedPane panelTabbed = new JTabbedPane();
		
		JComponent panel1 = new JPanel();
		panel1.setLayout(new GridLayout(3,1));
		initUsuarios(panel1);
		
		JComponent panel2 = new PanelCertificadosAdmin(this.control);
		
		panelTabbed.addTab("Usuarios", panel1);
		panelTabbed.addTab("Certificados", panel2);
		
		this.add(panelTabbed);
		this.setVisible(true);
	}
	
	private void initUsuarios(JComponent p){
		//Panel superior del menú usuarios		
		JPanel panelInfo = new JPanel();
		panelInfo.add(new JLabel("Lista de usuarios del Sistema"));
		
		p.add(panelInfo);		
		
		//Panel central del menú usuarios
		Usuario[] usuariosCombo = llenaLista();
		
		if (usuariosCombo!=null) this.comboUsuarios = new JComboBox<Usuario>(usuariosCombo);
		else this.comboUsuarios = new JComboBox<Usuario>();
		
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
	
	private void updateUsuario(JButton botonUpdateUser, final JComboBox<Usuario> comboUsuarios2) {
		botonUpdateUser.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int ind = comboUsuarios2.getSelectedIndex();
				if (ind!=-1){
					new VentanaEditaUsuario(control,comboUsuarios2.getItemAt(ind));
				}
			}
			
		});
		
	}

	private void borraUsuario(final JButton botonDeleteUser, final JComboBox<Usuario> comboUsuarios2) {
		botonDeleteUser.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int ind = comboUsuarios2.getSelectedIndex();
				if (ind!=-1){
					int choice = JOptionPane.showConfirmDialog(login
							,"¿Realmente quiere eliminar al usuario "
									+comboUsuarios2.getItemAt(ind).getNombre_completo()+"?"
							,"Eliminar Usuario"
							,JOptionPane.YES_NO_OPTION);
					if (choice == JOptionPane.YES_OPTION) {
	                    int resul = control.borraUsuario(comboUsuarios2.getItemAt(ind).getDni());
	                    if(resul!=0) {
	                    	JOptionPane.showMessageDialog(botonDeleteUser, "Usuario eliminado");
	                    	comboUsuarios.removeAllItems();
							List<Usuario> aux = control.dameListaUsuarios(); 
							if (!aux.isEmpty()){
								Iterator<Usuario> it = aux.iterator();
								while (it.hasNext()){
									comboUsuarios.addItem(it.next());
								}
							}
	                    }
	                    else JOptionPane.showMessageDialog(botonDeleteUser, "Ha habido un error en tu petición");
	                } else if (choice == JOptionPane.NO_OPTION) {
	                	JOptionPane.showMessageDialog(botonDeleteUser, "Petición Cancelada");
	                } else {
	                	JOptionPane.showMessageDialog(botonDeleteUser, "Cerrando Ventana");
	                }
				}
			}
		});
		
	}

	private void agregaUsuario(JButton botonAddUser) {
		botonAddUser.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaNuevoUsuario(control,comboUsuarios);
				
			}
			
		});
		
	}

	private Usuario[] llenaLista() {
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

}
