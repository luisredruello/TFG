package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import logica.Usuario;

public class VentanaEditaUsuario extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private Controlador controlador;
	
	public VentanaEditaUsuario(Controlador c,Usuario u){
		this.controlador=c;
		this.usuario=u;
		initVentanaEditaUsuario();
	}

	private void initVentanaEditaUsuario() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		JPanel contenedor = new JPanel();
		contenedor.setBorder(new TitledBorder("Vas a modificar los datos de: "+usuario.getNombre_completo()));
		setContentPane(contenedor);
		contenedor.setLayout(null);
		
		//Nombre
		JLabel labelNombre = new JLabel("Nombre Nuevo: ");
		labelNombre.setBounds(30, 35, 140, 20);
		contenedor.add(labelNombre);
		
		JTextField nuevoNombre = new JTextField();
		nuevoNombre.setBounds(200, 35, 180, 20);
		contenedor.add(nuevoNombre);
		
		//Password
		JLabel labelPass = new JLabel("Nuevo Password:");
		labelPass.setBounds(30, 80, 150, 20);
		contenedor.add(labelPass);
				
		JTextField nuevoPass = new JTextField();
		nuevoPass.setBounds(200, 80, 80, 20);
		contenedor.add(nuevoPass);
		
		//Botones
		JButton bAceptar = new JButton("Aceptar");
		bAceptar.setBounds(80, 140, 90, 25);
		contenedor.add(bAceptar);
		
		modificaDatos(bAceptar,nuevoNombre,nuevoPass,this);
		
		JButton bCancelar = new JButton("Cancelar");		
		bCancelar.setBounds(250, 140, 90, 25);
		contenedor.add(bCancelar);
		
		cancela(bCancelar,this);
				
		this.setVisible(true);
		
		
	}
	
	private void modificaDatos(final JButton bAceptar, final JTextField nuevoNombre, final JTextField nuevoPass,
			final JFrame w) {
		bAceptar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int resul = 0;
				String name = nuevoNombre.getText();
				String pass = nuevoPass.getText();
				if (!name.isEmpty() && !pass.isEmpty()){
					resul = controlador.actualizaUsuario(usuario,name,pass);
					if (resul>0) {
						JOptionPane.showMessageDialog(bAceptar, "Se han actualizado con éxito los datos");
						w.dispose();
					}
				    else JOptionPane.showMessageDialog(bAceptar,"No se pudo actualizar los datos");
				}
				else JOptionPane.showMessageDialog(bAceptar, "Los campos están vacíos");
			}
			
		});
		
	}

	private void cancela(JButton bCancelar, final VentanaEditaUsuario ventanaEditaUsuario) {
		bCancelar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaEditaUsuario.dispose();
				
			}
			
		});
		
	}

	

}
