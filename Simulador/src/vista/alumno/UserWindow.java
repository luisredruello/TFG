package vista.alumno;

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
import javax.swing.JOptionPane;
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
	private static int TAM_ARRAY_CERTIFICACIONES = 3;	//El n�mero de certificaciones total del sistema
	private JPanel panelUsuario;
	private JTabbedPane cuadro;
	private JFrame login;
	private Usuario user;
	private Controlador control;
	private JComboBox<Certificacion> comboCertificados;
	private JComboBox<ModuloTeorico> comboModulos;
	private Certificacion[] arrayCertificaciones;

	public UserWindow(JFrame v, Usuario us, Controlador control) {
		this.login=v;
		this.user=us;
		this.control=control;
		this.arrayCertificaciones = new Certificacion[TAM_ARRAY_CERTIFICACIONES];
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
		JButton bSalir = new JButton("Cerrar Sesi�n");		
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
		
		//Modulos
		JLabel labelMod = new JLabel("M�dulos:");
		ModuloTeorico[] mod = llenaModulos(this.comboCertificados.getItemAt(0).getNivel());
		
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
		int j=1;
		for (int i=0;i<TAM_ARRAY_CERTIFICACIONES;i++){
			arrayCertificaciones[i] = new Certificacion(j);
			j++;
		}
		this.comboCertificados = new JComboBox<Certificacion>();
		int nextCert = control.numCertificaciones(user.getDni());
		nextCert++;
		this.user.setNextCertificacion(nextCert);
		for (int k=0;k<user.getNextCertificacion();k++){
			this.comboCertificados.addItem(arrayCertificaciones[k]);
		}		
	}
	

	private void initTabePane() {
		cuadro = new JTabbedPane();
		
		//Cuadro de la parte principal
		JComponent panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());
		
		//Panel izquierdo Examen Teorico
		JPanel izq = new JPanel();
		izq.setBorder(new TitledBorder("Examen Te�rico"));
				
		JButton lanzaTeorico = new JButton("Realiza Te�rico");
		lanzaTeorico.setBounds(80, 180, 90, 25);
				
		lanzaExamenTeorico(lanzaTeorico);
				
		izq.add(lanzaTeorico);
				
		panelPrincipal.add(izq,BorderLayout.WEST);		
		
		//Panel Central Leer Teoria
		JPanel cent = new JPanel();
		cent.setBorder(new TitledBorder("Modulo Teorico"));
				
		JButton leerTeoria = new JButton("Lee Teor�a");
		leerTeoria.setBounds(80, 280, 95, 25);
				
		leeTeoria(leerTeoria,comboCertificados,comboModulos);
			
		cent.add(leerTeoria);
				
		panelPrincipal.add(cent,BorderLayout.CENTER);
				
		//Panel derecho Examen Practico
		JPanel der = new JPanel();
		der.setBorder(new TitledBorder("Examen Pr�ctico"));
		
		JButton lanzaPractico = new JButton("Realiza Pr�ctico");
		lanzaPractico.setBounds(80, 180, 90, 25);
		
		lanzaExamenPractico(lanzaPractico);
		
		der.add(lanzaPractico);
		
		panelPrincipal.add(der, BorderLayout.EAST);
		
		cuadro.addTab("Zona Principal", panelPrincipal);
		
	}
	
	
	private void lanzaExamenPractico(final JButton b) {
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int ind = comboCertificados.getSelectedIndex();
				if (ind!=-1){
					ExamenPractico p = control.getExamenPractico(comboCertificados.getItemAt(ind).getNivel());
					if (p!=null){
						ExamenTeorico teo = control.getExamenTeorico(comboCertificados.getItemAt(ind).getNivel());
						if (control.tieneAprobadoTeorico(user.getDni(),teo.getId_examen())){
							new VistaExamenPractico(control,p,user,comboCertificados);
						}
						else JOptionPane.showMessageDialog(b,"Primero debes aprobar el Examen Te�rico"); 
					}
					else JOptionPane.showMessageDialog(b,"Esta Certificaci�n No Tiene Examen Pr�ctico");
				}
				
			}
			
		});
		
	}

	private void lanzaExamenTeorico(JButton b) {
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int ind = comboCertificados.getSelectedIndex();
				if (ind!=-1){
					ExamenTeorico t = control.getExamenTeorico(comboCertificados.getItemAt(ind).getNivel());
					new VistaExamenTeorico(control,t,user,comboCertificados);
					//En caso de que se haya aprobado se actualiza el combo
				}
			}
			
		});
		
	}
	
	private void leeTeoria(JButton b,final JComboBox<Certificacion> c,final JComboBox<ModuloTeorico> m){
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int ind = c.getSelectedIndex();
				int ind2 = m.getSelectedIndex();
				String pdf = null;
				if (ind!=-1 && ind2!=-1){
					Certificacion cert = c.getItemAt(ind);
					ModuloTeorico modulo = control.getModuloTeorico(cert.getNivel(), m.getItemAt(ind2).getId_modulo());
					if (!modulo.existeFichero()){
						if (modulo.getPdf()==null){
							pdf = control.getPDF(modulo.getNivel(), modulo.getId_modulo());
						}
						else {
							modulo.createPDFFile();
							pdf = modulo.getPath();
						}
					}
					else pdf = modulo.getPath();
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
					Certificacion cer = c.getItemAt(ind);
					ModuloTeorico[] m = llenaModulos(cer.getNivel());
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
		List<ModuloTeorico> listaModulos = this.control.getListaModulosTeoricos(l);
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
