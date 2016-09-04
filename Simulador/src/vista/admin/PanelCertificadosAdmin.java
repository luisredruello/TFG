package vista.admin;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controlador.Controlador;
import logica.Certificacion;
import logica.ExamenPractico;
import logica.ExamenTeorico;
import logica.ModuloTeorico;

public class PanelCertificadosAdmin extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Certificacion> comboCertificados;
	private List<Certificacion> listaCertificados;
	private Controlador control;
	private JComboBox<ExamenTeorico> comboTeorico;
	private JComboBox<ExamenPractico> comboPractico;
	
	public PanelCertificadosAdmin(Controlador c){
		this.listaCertificados = null;
		this.control = c;
		initCertificados();
	}
	
	/**
	 * Crea la interfaz gráfica
	 */
	private void initCertificados() {
		this.setLayout(new BorderLayout());
		
		//Panel superior
		Certificacion[] certificadosCombo = llenaCertificados();
				
		if (certificadosCombo!=null) this.comboCertificados = new JComboBox<Certificacion>(certificadosCombo); 
		else this.comboCertificados = new JComboBox<Certificacion>();
				
		JPanel panelInfo = new JPanel();
		panelInfo.add(new JLabel("Certificados: "));
		panelInfo.add(comboCertificados);
		
		JLabel labelSubir = new JLabel("Subir Módulo Teórico:");
		JButton botonUpload = new JButton("Upload File");
		panelInfo.add(labelSubir);
		panelInfo.add(botonUpload);	
		
		subirArchivosTeoricos(botonUpload,comboCertificados);
				
		this.add(panelInfo,BorderLayout.NORTH);
		
		//Paneles Inferiores
		JTabbedPane panelCertificados = new JTabbedPane();
		
		//Examen Teorico
		JComponent panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		initPanelExamenTeorico(panel1);
		
		//Examen Practico
		JComponent panelPractico = new JPanel();
		panelPractico.setLayout(new BorderLayout());
		initPanelExamenPractico(panelPractico);
		
		panelCertificados.add("Administración Examen Teórico", panel1);
		panelCertificados.add("Administración Examen Práctico", panelPractico);
				
		this.add(panelCertificados, BorderLayout.CENTER);
		
	}
	
	/**
	 * Inicia el panel inferior de la interfaz grafica que administra las opciones de un Examen Teorico
	 * @param panel4 JComponent
	 */
	private void initPanelExamenTeorico(JComponent panel4) {
		comboTeorico = new JComboBox<ExamenTeorico>();
		Iterator<Certificacion> it = listaCertificados.iterator();
		while (it.hasNext()){
			ExamenTeorico teo = control.getExamenTeorico(it.next().getNivel());
			comboTeorico.addItem(teo);
		}
		JPanel p = new JPanel();
		JLabel labelComboTeorico = new JLabel("Elige un Examen Teorico: ");
		p.add(labelComboTeorico);
		p.add(comboTeorico);
		panel4.add(p, BorderLayout.NORTH);
		
		JPanel panelBoton = new JPanel();
		JButton botonPregunta = new JButton("Agregar Pregunta");
		agregaPregunta(botonPregunta);
		panelBoton.add(botonPregunta);
		
		panel4.add(panelBoton, BorderLayout.SOUTH);
	}
	
	/**
	 * Inicia la interfaz grafica que administra las opciones de un Examen Practico
	 * @param pPractico JComponent
	 */
	private void initPanelExamenPractico(JComponent pPractico) {
		comboPractico = new JComboBox<ExamenPractico>();
		Iterator<Certificacion> it = listaCertificados.iterator();
		while (it.hasNext()){
			ExamenPractico prac = control.getExamenPractico(it.next().getNivel());
			if (prac!=null)	comboPractico.addItem(prac);
		}
		JPanel p = new JPanel();
		JLabel labelComboPractico = new JLabel("Elige un Examen Practico: ");
		p.add(labelComboPractico);
		p.add(comboPractico);
		
		pPractico.add(p, BorderLayout.NORTH);
		
		JPanel panelBoton = new JPanel();
		JButton botonImagen = new JButton("Subir Imagen");
		agregaImagen(botonImagen);
		panelBoton.add(botonImagen);
		
		pPractico.add(panelBoton, BorderLayout.SOUTH);
		
	}
	
	private void agregaImagen(JButton botonImagen) {
		botonImagen.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Elige la imagen sin contrastes, por favor");
				int ind = comboPractico.getSelectedIndex();
				JFileChooser seleccion=new JFileChooser();
				if (seleccion.showOpenDialog(null) == JFileChooser.APPROVE_OPTION && ind!=-1){
					File file = seleccion.getSelectedFile();
					ExamenPractico p = comboPractico.getItemAt(ind);
					new VentanaNuevaImagen(control,p,file);
				}
				else JOptionPane.showMessageDialog(null,"Debes Elegir Una Imagen");
			}
			
		});
		
	}

	/**
	 * Inicia el action listener del boton de subida de un pdf teorico
	 * @param botonUpload JButton lanza el action listener
	 * @param combo
	 */
	private void subirArchivosTeoricos(final JButton botonUpload,final JComboBox<Certificacion> combo) {
		botonUpload.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int ind = combo.getSelectedIndex();
				int resul = 0;
				JFileChooser seleccion=new JFileChooser();
				if(seleccion.showOpenDialog(null) == JFileChooser.APPROVE_OPTION && ind != -1){
					File file = seleccion.getSelectedFile();
					int maxMod = getNumModulos(combo.getItemAt(ind).getNivel());
					resul = control.subeTeoria(combo.getItemAt(ind).getNivel(),maxMod+1,file);
					if (resul>0) JOptionPane.showMessageDialog(botonUpload, "Se ha subido correctamente el archivo");
					else JOptionPane.showMessageDialog(botonUpload,"Ha ocurrido un error al subir el archivo");
				}
				else JOptionPane.showMessageDialog(botonUpload,"No se ha seleccionado ningún archivo");				
			}
			
			/**
			 * Devuelve el numero de modulos teoricos que hay en el sistema, para un determinado nivel
			 * de Certificacion
			 * @param nivel int
			 * @return int el mayor numero insertado para un modulo teorico
			 */
			private int getNumModulos(int nivel){
				List<ModuloTeorico> list = control.getListaModulosTeoricos(nivel);
				if (list!=null) return list.size();
				else return 0;
			}
			
		});
		
	}


	/**
	 * Inicia el action listener que agrega una pregunta al sistema
	 * @param b JButtton
	 */
	private void agregaPregunta(JButton b) {
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int ind = comboTeorico.getSelectedIndex();
				if (ind!=-1){
					ExamenTeorico t = comboTeorico.getItemAt(ind);
					new VentanaNuevaPregunta(control,t);
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
				i++;
			}
			return resul;
		}
		else return null;
	}

}
