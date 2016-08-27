package vista.alumno;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import tools.*;
import controlador.Controlador;
import logica.ExamenPractico;
import logica.Imagen;
import logica.Usuario;

public class VistaExamenPractico extends JFrame implements ItemListener, PropertyChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controlador control;
	private ExamenPractico practico;
	private Usuario alumno;
	private JProgressBar progressBar;
	private JPanel vistaImagenes;	//Representa el CardLayout
	private LinkedList<PanelImagen> panelImagen;
	private HashMap<Integer, Imagen> tablaImagen;	//K: id imagen, V: imagen
	private Task task;
	private int pagina;
	
	public VistaExamenPractico(Controlador c, ExamenPractico ex, Usuario u){
		this.control=c;
		this.practico=ex;
		this.alumno=u;
		this.panelImagen = new LinkedList<PanelImagen>();
		this.tablaImagen = new HashMap<Integer, Imagen>();
		this.vistaImagenes = new JPanel(new CardLayout());
		this.pagina=0;
		initList();
		initWindow();
		
		task = new Task(this,practico.getSegundos());
        task.addPropertyChangeListener(this);
        task.execute();
	}
	
	/**
	 * Inicializa las distintas estructuras de datos necesarias para la logica del examen
	 */
	private void initList() {
		List<Imagen> l = control.getImagenesFromExamen(practico.getId_examen());
		if (!l.isEmpty()){
			Iterator<Imagen> it = l.iterator();
			while (it.hasNext()){
				Imagen im = it.next();
				tablaImagen.put(im.getId_imagen(), im);
			}
		}
		int i = 0;
		int aleatorio = 0;
		//K: id imagen, para comprobar si ya hemos insertado la imagen
		HashMap<Integer,Integer> insertados = new HashMap<Integer,Integer>();
		
		while (i<practico.getNum_imagenes()){
			aleatorio = Utilities.getRandomNumber(tablaImagen.size());
			
			if (!insertados.containsKey(aleatorio)){
				insertados.put(aleatorio, 0);
				Imagen image = tablaImagen.get(aleatorio);
				panelImagen.add(new PanelImagen(control,image));
				vistaImagenes.add(panelImagen.getLast(),Integer.toString(i));
				i++;
			}
		}
		
	}

	/**
	 * Inicializa la interfaz gráfica
	 */
	private void initWindow(){
		this.setTitle("Examen Práctico");
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//this.setSize(this.getToolkit().getScreenSize());
		this.setBounds(100, 100, 1300, 600);
		
		//Panel Superior Datos del Examen
		JPanel panelSup = new JPanel();
		JLabel labelDesc = new JLabel("Tiempo Total: "+practico.getTiempo_examen()/60+" Minutos");
				
		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		        
		JLabel labelTiempo = new JLabel("Tiempo Consumido: ");
		panelSup.add(labelDesc);
		panelSup.add(labelTiempo);
		panelSup.add(progressBar);
		this.add(panelSup, BorderLayout.NORTH);
		
		//Panel Central CardLayout		
		this.add(vistaImagenes, BorderLayout.CENTER);
		
		//Panel Inferior (3 Botones, avanzar, retroceder, comprobar)
		JPanel panelInferior = new JPanel();
		
		JButton botonRetroceder = new JButton("Anterior");
		botonRetroceder.setActionCommand("back");
		botonRetroceder.addItemListener(this);
		
		JButton botonAvanzar = new JButton("Siguiente");
		botonAvanzar.setActionCommand("forward");
		botonAvanzar.addItemListener(this);
		
		JButton botonResultado = new JButton("Comprobar");
		
		panelInferior.add(botonRetroceder);
		panelInferior.add(botonAvanzar);
		panelInferior.add(botonResultado);
		
		this.add(panelInferior, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	/**
	 * Avanza la página actual, si llega al final vuelve a la primera imagen
	 */
	private void avanzaPagina(){
		pagina++;
		if (pagina==panelImagen.size()){
			pagina=0;
		}
	}
	
	/**
	 * Retrocede una página, si llega al principio y se retrocede se vuelve al final
	 */
	private void retrocedePagina(){
		pagina--;
		if (pagina<0){
			pagina=panelImagen.size()-1;
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		CardLayout cl = (CardLayout)vistaImagenes.getLayout();
		String command = ((JButton)e.getSource()).getActionCommand();
		if (command.compareTo("forward")==0){
			avanzaPagina();
			cl.show(panelImagen.get(pagina), (String)e.getItem());
		}
		else if (command.compareTo("back")==0){
			retrocedePagina();
			cl.show(panelImagen.get(pagina), (String)e.getItem());
		}
		
	}	

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            progressBar.setValue(progress);
        } 
		
	}

}
