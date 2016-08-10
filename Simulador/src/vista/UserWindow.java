package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
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
import logica.*;
import pdf.PDFReader;

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
	private JComboBox<ModuloTeorico> comboModulos;
	private ModuloTeorico modulo;
	private List<ModuloTeorico> listaModulos;

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
		JLabel labelCert = new JLabel("Certificaciones:");
		Certificacion[] cert = llenaCertificados();
		
		if (cert!=null) this.comboCertificados = new JComboBox<Certificacion>(cert);
		else this.comboCertificados = new JComboBox<Certificacion>();
		
		this.certificado=this.comboCertificados.getItemAt(0);
		
		//Modulos
		JLabel labelMod = new JLabel("Módulos:");
		ModuloTeorico[] mod = llenaModulos(this.certificado.getNivel());
		
		if (mod!=null) this.comboModulos = new JComboBox<ModuloTeorico>(mod);
		else this.comboModulos = new JComboBox<ModuloTeorico>();
		
		JPanel bloqueCentral = new JPanel();
		bloqueCentral.add(labelCert);
		bloqueCentral.add(comboCertificados);
		bloqueCentral.add(labelMod);
		bloqueCentral.add(comboModulos);
		
		panelUsuario.add(bloqueSup,BorderLayout.NORTH);
		panelUsuario.add(bloqueCentral, BorderLayout.CENTER);
		
		updateComboModulos(this.comboCertificados);
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
		
		leeTeoria(leerTeoria,comboCertificados,comboModulos);
		
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
	
	private void leeTeoria(JButton b,final JComboBox<Certificacion> c,final JComboBox<ModuloTeorico> m){
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int ind = c.getSelectedIndex();
				int ind2 = m.getSelectedIndex();
				String pdf = null;
				if (ind!=-1 && ind2!=-1){
					certificado = c.getItemAt(ind);
					//modulo = m.getItemAt(ind2);
					modulo = control.getModuloTeorico(certificado.getNivel(), m.getItemAt(ind2).getId_modulo());
					if (!modulo.existeFichero()){
						modulo.createPDFFile();
						pdf = modulo.getPath();
					}
					else pdf = control.getPDF(modulo.getNivel(), modulo.getId_modulo());
					PDFReader.readPDF(pdf);
				}	
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
	
	private void updateComboModulos(final JComboBox<Certificacion> c){
		c.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int ind = c.getSelectedIndex();
				if (ind!=-1){
					certificado = c.getItemAt(ind);
					ModuloTeorico[] m = llenaModulos(certificado.getNivel());
					if (m!=null){
						comboModulos.removeAllItems();
						for(int i=0;i<m.length;i++){
							comboModulos.addItem(m[i]);
						}
					}					
				}				
			}
			
		});
	}
	
	private Certificacion[] llenaCertificados() {
		this.listaCertificados = this.control.getListaCertificados();
		if (listaCertificados != null){
			Certificacion[] resul = new Certificacion[listaCertificados.size()];
			Iterator<Certificacion> it = listaCertificados.iterator();
			int i=0;
			while(it.hasNext()){
				resul[i] = it.next();
				resul[i].setPractico(this.control.getExamenPractico(resul[i].getNivel()));
				resul[i].setTeorico(this.control.getExamenTeorico(resul[i].getNivel()));
				i++;
			}
			return resul;
		}
		else return null;
	}
	
	
	private ModuloTeorico[] llenaModulos(int l) {
		this.listaModulos = this.control.getListaModulosTeoricos(l);
		if (listaModulos != null){
			ModuloTeorico[] resul = new ModuloTeorico[listaModulos.size()];
			Iterator<ModuloTeorico> it = listaModulos.iterator();
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
