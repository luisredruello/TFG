package vista.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import logica.Usuario;

public class VentanaNuevoUsuario extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controlador control;
	private JComboBox<Usuario> comboUsuarios;
	
	public VentanaNuevoUsuario(Controlador c, JComboBox<Usuario> com) {
		this.control = c;
		this.comboUsuarios=com;
		initVentana();
	}

	private void initVentana() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(150, 150, 380, 280);
		JPanel contenedor = new JPanel();
		contenedor.setBorder(new TitledBorder("Inserción Nuevo Usuario"));
		setContentPane(contenedor);
		contenedor.setLayout(null);
		
		//Nombre del usuario
		JLabel labelNombre = new JLabel("Nombre:");
		labelNombre.setBounds(30, 40, 80, 20);
		contenedor.add(labelNombre);
		
		JTextField nuevoNombre = new JTextField();
		nuevoNombre.setBounds(120, 40, 170, 20);
		contenedor.add(nuevoNombre);
		
		//DNI del usuario
		JLabel labelDNI = new JLabel("DNI:");
		labelDNI.setBounds(30, 80, 80, 20);
		contenedor.add(labelDNI);
		
		JTextField nuevoDNI = new JTextField();
		nuevoDNI.setBounds(120, 80, 80, 20);
		contenedor.add(nuevoDNI);
		
		//Passwords del usuario
		JLabel labelPass = new JLabel("Pass:");
		labelPass.setBounds(30, 120, 150, 20);
		contenedor.add(labelPass);
		
		JTextField nuevoPass = new JTextField();
		nuevoPass.setBounds(120, 120, 120, 20);
		contenedor.add(nuevoPass);
		
		//Botones de control		
		JButton bAceptar = new JButton("Aceptar");
		bAceptar.setBounds(80, 180, 87, 23);
		contenedor.add(bAceptar);
		
		insertaUsuario(nuevoNombre, nuevoDNI, nuevoPass, bAceptar , this);
				
		JButton bCancelar = new JButton("Cancelar");		
		bCancelar.setBounds(180, 180, 89, 23);
		contenedor.add(bCancelar);
		
		cancela(bCancelar,this);
				
		this.setVisible(true);
		
	}
	
	private void insertaUsuario(final JTextField nuevoNombre, final JTextField dniField,final JTextField passField
			,final JButton bAceptar,final JFrame v) {
		bAceptar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int resul = 0;
				String name = nuevoNombre.getText();
				String dni = dniField.getText();
				String pass = passField.getText();
				if (!name.isEmpty() && !dni.isEmpty() && dni.length()<9 && !pass.isEmpty()){
					boolean enc = control.existeUsuario(dni);
					if (!enc){
						resul = control.insertaUsuario(name,dni,pass);
						if (resul > 0) {
							JOptionPane.showMessageDialog(bAceptar, "Se ha insertado el usuario con éxito");
							comboUsuarios.removeAllItems();
							List<Usuario> aux = control.dameListaUsuarios(); 
							if (!aux.isEmpty()){
								Iterator<Usuario> it = aux.iterator();
								while (it.hasNext()){
									comboUsuarios.addItem(it.next());
								}
							}
							v.dispose();
						}
						else JOptionPane.showMessageDialog(bAceptar, "Ha habido un error en tu petición");
					}
					else JOptionPane.showMessageDialog(bAceptar, "El DNI ya está dado de alta");					
				}
				else JOptionPane.showMessageDialog(bAceptar, "Los campos están vacíos o el DNI excede los 8 dígitos");
				
			}
			
		});
	}

	private void cancela(JButton bCancelar, final VentanaNuevoUsuario v) {
		bCancelar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				v.dispose();
				
			}
			
		});
		
	}

}
