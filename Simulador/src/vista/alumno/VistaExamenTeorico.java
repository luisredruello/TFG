package vista.alumno;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import logica.*;
import tools.Utilities;

public class VistaExamenTeorico extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controlador control;
	private ExamenTeorico teorico;
	private Usuario alumno;
	private List<Pregunta> listaPreguntas;
	private HashMap<Integer, List<Respuesta>> tablaRespuestas; //K: id pregunta, V: Lista Respuestas
	
	public VistaExamenTeorico(Controlador c,ExamenTeorico t,Usuario u){
		this.control=c;
		this.teorico=t;
		this.alumno=u;
		this.tablaRespuestas = new HashMap<Integer, List<Respuesta>>();
		iniciaListas();
		initWindow();
	}

	private void iniciaListas() {
		this.listaPreguntas = control.getListaPreguntasFromExamen(this.teorico.getId_examen());
		Iterator<Pregunta> it = this.listaPreguntas.iterator();
		while(it.hasNext()){
			Pregunta aux = it.next();
			this.tablaRespuestas.put(aux.getId_pregunta(), control.getListaRespuestasFromPregunta(aux.getId_pregunta()));
		}
		
	}

	private void initWindow() {
		this.setTitle(this.teorico.getNombre());
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 950, 600);
		
		//Panel Superior Datos del Examen
		JPanel panelSup = new JPanel();
		JLabel labelDesc = new JLabel(this.teorico.getDescripcion());
		panelSup.add(labelDesc);
		this.add(panelSup, BorderLayout.NORTH);
		
		//Panel Central Lista Preguntas
		JPanel panelPreguntas = new JPanel();
		panelPreguntas.setLayout(new BoxLayout(panelPreguntas, BoxLayout.Y_AXIS));
		JScrollPane scroll = new JScrollPane();
		scroll.setBorder(new TitledBorder("Lista Preguntas"));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.add(scroll, BorderLayout.CENTER);
		
		scroll.setViewportView(panelPreguntas);
		
		initPanelPreguntas(panelPreguntas);
		
		//Panel Inferior Boton Comprobar Resultados
		
		JButton botonResultado = new JButton("Comprobar");
		comprobarResultado(botonResultado);
		
		this.add(botonResultado, BorderLayout.SOUTH);
		
		this.setVisible(true);
		
	}

	private void initPanelPreguntas(JPanel panel) {
		int i = 0;
		int aleatorio = 0;
		HashMap<Integer,Integer> insertados = new HashMap<Integer,Integer>();
		
		while (i<teorico.getNum_preguntas()){
			//Escogemos una pregunta aleatoria de la lista entera entre 0 y numPreguntas
			aleatorio = Utilities.getRandomNumber(teorico.getNum_preguntas());
			
			//miramos si no habiamos escogido esa pregunta anteriormente
			if (!insertados.containsKey(aleatorio)){
				insertados.put(aleatorio, 0);
				Pregunta p = listaPreguntas.get(aleatorio);
				System.out.println(p.toString());
				
				//Enunciado de Pregunta
				JPanel aux = new JPanel(new GridLayout(0, 1));				
				aux.setBorder(new TitledBorder("Pregunta "+Integer.toString(i+1)));
				JLabel textArea = new JLabel(p.getEnunciado());
				aux.add(textArea);
				panel.add(aux);
				
				//Respuestas
				List<Respuesta> list = tablaRespuestas.get(p.getId_pregunta());
				Iterator<Respuesta> it = list.iterator();
				ButtonGroup group = new ButtonGroup();
				JPanel radioPanel = new JPanel(new GridLayout(0, 1));
				JRadioButton checkButton = null;
				while (it.hasNext()){
					//Creamos los radio button y los agrupamos
					checkButton = new JRadioButton(it.next().getEnunciado());
					group.add(checkButton);
					
					//Ponemos el grupo de botones en una columna
			        radioPanel.add(checkButton);
				}			
		        
		        aux.add(radioPanel);
		        panel.add(aux);
		        i++;
			}			
			
		}			
		
	}
	
	private void comprobarResultado(JButton botonResultado) {
		// TODO Auto-generated method stub
		
	}

}
