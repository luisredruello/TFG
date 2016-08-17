package vista.alumno;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
	//private List<Certificacion> listaCertificados;
	//private int numCertificacionesAlumno;
	private JComboBox<ModuloTeorico> comboModulos;
	private ModuloTeorico modulo;
	private List<ModuloTeorico> listaModulos;

	public UserWindow(JFrame v, Usuario us, Controlador control) {
		this.login=v;
		this.user=us;
		this.control=control;
		//this.numCertificacionesAlumno=0;
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
		JLabel carnet = new JLabel("DNI: "+this.user.getDni());
		
		bloqueSup.add(nick);
		bloqueSup.add(carnet);
		
		//Certificados
		JLabel labelCert = new JLabel("Certificaciones:");
		iniciaComboCertificados();
		
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

	private void iniciaComboCertificados() {
		Certificacion c = null;
		this.user.setNumCertificaciones(control.numCertificaciones(user.getDni()));
		if (this.user.getNumCertificaciones()==0){
			c = new Certificacion(1);
			c.setTeorico(control.getExamenTeorico(1));
			this.comboCertificados = new JComboBox<Certificacion>();
			this.comboCertificados.addItem(c);
		}
		else {
			Certificacion[] array = new Certificacion[this.user.getNumCertificaciones()];
			int j = 1;
			for(int i=0;i<this.user.getNumCertificaciones();i++){
				array[i] = new Certificacion(j);
				array[i].setPractico(this.control.getExamenPractico(j));
				array[i].setTeorico(this.control.getExamenTeorico(j));
				j++;
			}
			this.comboCertificados = new JComboBox<Certificacion>(array);
			int nivelActual = this.user.getNumCertificaciones()+1;
			c = new Certificacion(nivelActual);
			c.setTeorico(control.getExamenTeorico(nivelActual));
			c.setPractico(control.getExamenPractico(nivelActual));
			this.comboCertificados.addItem(c);
		}		
		
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

		cuadro.addTab("Teoría "+certificado.toString(), panel1);
		if (certificado.getPractico()!=null)	cuadro.addTab("Práctica "+certificado.toString(), panel2);		
		
		updatePaneInferior(comboCertificados,cuadro,panel2);
		
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
		
		lanzaExamenTeorico(lanzaExamen);
		
		der.add(lanzaExamen);
		
		p1.add(der,BorderLayout.EAST);
	}
	
	private void lanzaExamenTeorico(JButton b) {
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int ind = comboCertificados.getSelectedIndex();
				if (ind!=-1){
					ExamenTeorico t = comboCertificados.getItemAt(ind).getTeorico();
					JFrame f = new VistaExamenTeorico(control,t,user);
					//En caso de que se haya aprobado se actualiza el combo
					
					updateComboCertificados();
					//numCertificacionesAlumno++;
				}
			}
			
		});
		
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
					modulo = control.getModuloTeorico(certificado.getNivel(), m.getItemAt(ind2).getId_modulo());
					if (!modulo.existeFichero()){
						modulo.createPDFFile();
						pdf = modulo.getPath();
					}
					else if (modulo.getPdf()!= null) pdf = modulo.getPath();
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
	
	
	private void updatePaneInferior(final JComboBox<Certificacion> c, final JTabbedPane p, final JComponent panel){
		c.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int indice = c.getSelectedIndex();
				if (indice != -1){
					Certificacion t = c.getItemAt(indice);
					p.setTitleAt(0, "Teoría "+t.toString());
					if (t.getPractico()!=null)	p.addTab("Práctica "+t.toString(), panel);
					else p.remove(1);
				}				
			}
			
		});
	}
	
	/**
	 * Actualiza el Combo de Certificados del alumno, agregando uno nuevo
	 */
	public void updateComboCertificados() {
		Certificacion c = null;
		
		int nivelActual = user.getNumCertificaciones();
		c = new Certificacion(nivelActual);
		c.setTeorico(control.getExamenTeorico(nivelActual));
		c.setPractico(control.getExamenPractico(nivelActual));
		comboCertificados.addItem(c);				
	}

}
