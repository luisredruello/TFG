package vista.alumno;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controlador.Controlador;
import logica.ExamenPractico;
import logica.Imagen;
import tools.Utilities;

public class VistaPruebaPractico extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controlador control;
	private ExamenPractico practico;
	private JPanel vistaImagenes;	//Representa el CardLayout
	private LinkedList<PanelPruebaImagen> panelImagen;
	private HashMap<Integer, Imagen> tablaImagen;	//K: id imagen, V: imagen
	private int puntuacion;
	private int pagina;
	private JLabel numImagenes;
	private int restantes;
	
	public VistaPruebaPractico(Controlador c, ExamenPractico prac){
		this.control=c;
		this.puntuacion=0;
		this.practico=prac;
		this.pagina=0;
		this.restantes=practico.getNum_imagenes();
		this.vistaImagenes=new JPanel(new CardLayout());
		this.panelImagen = new LinkedList<PanelPruebaImagen>();
		this.tablaImagen = new HashMap<Integer, Imagen>();
		this.numImagenes= new JLabel();
		initList();
		initWindow();
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
			
			if (!insertados.containsKey(aleatorio) && aleatorio>0){
				insertados.put(aleatorio, 0);
				Imagen image = tablaImagen.get(aleatorio);
				panelImagen.add(new PanelPruebaImagen(control,image,i,this));
				vistaImagenes.add(panelImagen.getLast(),Integer.toString(i));
				i++;
			}
		}
		
	}

	/**
	 * Inicializa la interfaz gráfica
	 */
	private void initWindow(){
		this.setTitle("Prueba Práctico");
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(50, 50, 1500, 750);
		
		//Panel Superior Datos del Examen
		JPanel panelSup = new JPanel();
		
		JLabel labelImagenes = new JLabel("Imagenes Totales: "+practico.getNum_imagenes());

		panelSup.add(labelImagenes);
		panelSup.add(numImagenes);
		this.add(panelSup, BorderLayout.NORTH);
		
		//Panel Central CardLayout		
		this.add(vistaImagenes, BorderLayout.CENTER);
		
		//Panel Inferior (3 Botones, avanzar, retroceder, comprobar)
		JPanel panelInferior = new JPanel();
		
		JButton botonRetroceder = new JButton("Anterior");
		botonRetroceder.setActionCommand("back");
		botonRetroceder.addActionListener(this);
		
		JButton botonAvanzar = new JButton("Siguiente");
		botonAvanzar.setActionCommand("forward");
		botonAvanzar.addActionListener(this);
		
		JButton botonResultado = new JButton("Comprobar");
		comprobarResultado(botonResultado,this);
		
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
	
	private void comprobarResultado(final JButton c, final JFrame v){
		c.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if (restantes<=0){
					JOptionPane.showMessageDialog(c,"Esta es tu puntuación: "+puntuacion);
					v.dispose();
				}
				else JOptionPane.showMessageDialog(null,"Debes marcar todas las imágenes, por favor");	
			}
			
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout cl = (CardLayout)vistaImagenes.getLayout();
		String command = ((JButton)e.getSource()).getActionCommand();
		if (command.compareTo("forward")==0){
			avanzaPagina();
			cl.show(vistaImagenes, Integer.toString(pagina));
		}
		else if (command.compareTo("back")==0){
			retrocedePagina();
			cl.show(vistaImagenes, Integer.toString(pagina));
		}
	}
	
	/**
	 * Actualiza el label numero de preguntas restantes
	 * @param n
	 */
	public void updateNumPreguntas(){
		restantes--;
		numImagenes.setText("Imagenes Restantes: "+restantes);
		numImagenes.repaint();
	}
	
	/**
	 * Suma 1 punto a la puntuacion actual
	 */
	public void aumentaCorrectas(){
		this.puntuacion++;
	}


}
