package vista.alumno;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import tools.*;
import controlador.Controlador;
import logica.Certificacion;
import logica.ExamenPractico;
import logica.Imagen;
import logica.ObjetoProhibido;
import logica.Usuario;

public class VistaExamenPractico extends JFrame implements ActionListener, PropertyChangeListener{

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
	private HashMap<Integer, Point> imagenPuntos;	//K: id imagen, V: punto clickado o null, si no ha clickado
	private Task task;
	private int pagina;
	private JLabel numImagenes;
	private JComboBox<Certificacion> comboModel;
	
	public VistaExamenPractico(Controlador c, ExamenPractico ex, Usuario u, JComboBox<Certificacion> com){
		this.control=c;
		this.practico=ex;
		this.alumno=u;
		this.panelImagen = new LinkedList<PanelImagen>();
		this.tablaImagen = new HashMap<Integer, Imagen>();
		this.imagenPuntos = new HashMap<Integer, Point>();
		this.vistaImagenes = new JPanel(new CardLayout());
		this.pagina=0;
		this.numImagenes = new JLabel("Imagenes Restantes: "+practico.getNum_imagenes());
		this.comboModel=com;
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
			
			if (!insertados.containsKey(aleatorio) && aleatorio>0){
				insertados.put(aleatorio, 0);
				Imagen image = tablaImagen.get(aleatorio);
				panelImagen.add(new PanelImagen(control,image,i,this));
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
		this.setBounds(50, 50, 1500, 750);
		
		//Panel Superior Datos del Examen
		JPanel panelSup = new JPanel();
		JLabel labelDesc = new JLabel("Tiempo Total: "+practico.getTiempo_examen()/60+" Minutos");
				
		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		        
		JLabel labelTiempo = new JLabel("Tiempo Consumido: ");
		JLabel labelImagenes = new JLabel("Imagenes Totales: "+practico.getNum_imagenes());
		panelSup.add(labelDesc);
		panelSup.add(labelTiempo);
		panelSup.add(progressBar);
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
				int tam = imagenPuntos.size();
				if (tam==practico.getNum_imagenes()){
					task.cancel(true);
					int resultado = 0;
					Iterator<Integer> it = imagenPuntos.keySet().iterator();
					while (it.hasNext()){
						Integer key = it.next();
						Point value = imagenPuntos.get(key);
						Imagen aux = tablaImagen.get(key);
						if (value==null && aux.getId_objeto()==0) resultado++;
						else if (value!=null && aux.getId_objeto()!=0){
							ObjetoProhibido prohibido = control.getObjetoProhibido(aux.getId_objeto());
							if (prohibido.estaDentro(value)) resultado++;
						}
					}
					if (aprobado(resultado)){
						if (control.apruebaExamenPractico(alumno,practico)>0){
							JOptionPane.showMessageDialog(c,"Has aprobado con un "+resultado+", Enhorabuena");
							if (control.insertaCertificacion(alumno, practico.getNivel())>0){
								int cert = alumno.getNextCertificacion();
								cert++;
								if (cert<=3){
									alumno.setNextCertificacion(cert);
									comboModel.addItem(new Certificacion(cert));
								}
								JOptionPane.showMessageDialog(c,
										"Has Conseguido la Certificación "+practico.getNivel());
							}
						}
					}
					else JOptionPane.showMessageDialog(null,"Lo sentimos, has suspendido con un: "+resultado);
					v.dispose();
				}
				else JOptionPane.showMessageDialog(null,"Debes marcar todas las imágenes, por favor");
				
			}
			
			private boolean aprobado(int r){
				float mitad = (practico.getNum_imagenes()*50)/100;
				return (r>=mitad)?true:false;
			}
			
		});
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            progressBar.setValue(progress);
        } 
		
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
	public void updateNumPreguntas(int n){
		int restante = practico.getNum_imagenes()-n;
		numImagenes.setText("Imagenes Restantes: "+restante);
		numImagenes.repaint();
	}

	/**
	 * @return the imagenPuntos
	 */
	public HashMap<Integer, Point> getImagenPuntos() {
		return imagenPuntos;
	}

	/**
	 * @param imagenPuntos the imagenPuntos to set
	 */
	public void setImagenPuntos(HashMap<Integer, Point> imagenPuntos) {
		this.imagenPuntos = imagenPuntos;
	}

}
