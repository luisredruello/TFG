package vista.alumno;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import controlador.Controlador;
import logica.*;
import tools.*;

public class VistaExamenTeorico extends JFrame implements PropertyChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controlador control;
	private ExamenTeorico teorico;
	private Usuario alumno;
	private List<Pregunta> listaPreguntas;
	private HashMap<Integer, List<Respuesta>> tablaRespuestas; //K: id pregunta, V: Lista Respuestas
	private HashMap<Integer, Respuesta> tablaRespuestasAgregadas;
	private ArrayList<Respuesta> listaRespuestasAlumno; //K: id respuesta, V: Respuesta
	private JProgressBar progressBar;
	private Task task;
	private JComboBox<Certificacion> comboModel;
	
	public VistaExamenTeorico(Controlador c,ExamenTeorico t,Usuario u,JComboBox<Certificacion> com){
		this.control=c;
		this.teorico=t;
		this.alumno=u;
		this.tablaRespuestas = new HashMap<Integer, List<Respuesta>>();
		this.tablaRespuestasAgregadas = new HashMap<Integer,Respuesta>();
		this.listaRespuestasAlumno = new ArrayList<Respuesta>();
		this.comboModel=com;
		iniciaListas();
		initWindow();
		
		task = new Task(this,this.teorico.getSegundos());
        task.addPropertyChangeListener(this);
        task.execute();
	}

	private void iniciaListas() {
		this.listaPreguntas = control.getListaPreguntasFromExamen(this.teorico.getId_examen());
		Iterator<Pregunta> it = this.listaPreguntas.iterator();
		while(it.hasNext()){
			Pregunta aux = it.next();
			List<Respuesta> l = control.getListaRespuestasFromPregunta(aux.getId_pregunta());
			this.tablaRespuestas.put(aux.getId_pregunta(), l);
		}
		
	}

	private void initWindow() {
		this.setTitle(this.teorico.getNombre());
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 1100, 600);
		
		//Panel Superior Datos del Examen
		JPanel panelSup = new JPanel();
		JLabel labelDesc = new JLabel("Tiempo Total: "+teorico.getTiempo_examen()/60+" Minutos");
		
		progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        
		JLabel labelTiempo = new JLabel("Tiempo Consumido: ");
		panelSup.add(labelDesc);
		panelSup.add(labelTiempo);
		panelSup.add(progressBar);
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
		comprobarResultado(botonResultado,this);
		
		this.add(botonResultado, BorderLayout.SOUTH);
		
		this.setVisible(true);
		
	}

	private void initPanelPreguntas(JPanel panel) {
		int i = 0;
		int aleatorio = 0;
		//K: id pregunta, para comprobar si ya hemos insertado la pregunta
		HashMap<Integer,Integer> insertados = new HashMap<Integer,Integer>();
		
		while (i<teorico.getNum_preguntas()){
			//Escogemos una pregunta aleatoria de la lista entera entre 0 y numPreguntas
			aleatorio = Utilities.getRandomNumber(teorico.getNum_preguntas());
			
			//miramos si no habiamos escogido esa pregunta anteriormente
			if (!insertados.containsKey(aleatorio) && listaPreguntas.size()>aleatorio){
				insertados.put(aleatorio, 0);
				Pregunta p = listaPreguntas.get(aleatorio);
				
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
				RadioButtonActionListener actionlistener = new RadioButtonActionListener();
				JRadioButton checkButton = null;
				while (it.hasNext()){
					Respuesta r = it.next();
					
					this.tablaRespuestasAgregadas.put(r.getId_respuesta(), r);
					if (r.esCorrecta()) System.out.println(r.getEnunciado());
					
					//Creamos los radio button y los agrupamos
					checkButton = new JRadioButton(r.getEnunciado());
					checkButton.setActionCommand(Integer.toString(r.getId_respuesta()));
					checkButton.addItemListener(actionlistener);
					
					//Agregamos el boton creado a la tabla de botones
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
	
	private void comprobarResultado(final JButton botonResultado,final JFrame v) {
		botonResultado.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int resultado = 0;
				if (!listaRespuestasAlumno.isEmpty() && listaRespuestasAlumno.size()==teorico.getNum_preguntas()){
					task.cancel(true);
					Iterator<Respuesta> it = listaRespuestasAlumno.iterator();
					while(it.hasNext()){
						if (it.next().esCorrecta()) resultado++;
					}
					if (aprobado(resultado)){
						//agregar a BBDD el aprobado
						if (control.apruebaExamenTeorico(alumno,teorico)>0)	{
							JOptionPane.showMessageDialog(botonResultado,"Has aprobado con un "+resultado+", Enhorabuena");
							//El nivel 1 de Certificacion no tiene parte practica, por lo que se obtendría directamente
							//la certificacion al aprobar el examen
							if (teorico.getNivel()==1){
								if (control.insertaCertificacion(alumno,teorico)>0) {									
									int cert = alumno.getNextCertificacion();
									cert++;
									alumno.setNextCertificacion(cert);
									comboModel.addItem(new Certificacion(cert));
									JOptionPane.showMessageDialog(botonResultado,"Has Conseguido la Certificación "+teorico.getNivel());
								}									
								else JOptionPane.showMessageDialog(botonResultado,"Ha habido un error al obtener certificación");
							}
						}								
						else JOptionPane.showMessageDialog(botonResultado,"Ha habido un error en tu petición");
					}
					else JOptionPane.showMessageDialog(botonResultado
							,"Lo sentimos, has suspendido con un: "+resultado);
					v.dispose();
				}
				else JOptionPane.showMessageDialog(botonResultado,"Hay Respuestas sin responder, revísalo por favor");
			}
			
			private boolean aprobado(int r){
				float mitad = (teorico.getNum_preguntas()*50)/100;
				return (r>=mitad)?true:false;
			}
			
		});
		
	}
	
	public class RadioButtonActionListener implements ItemListener {
		
		@Override
		public void itemStateChanged(ItemEvent e) {	        
	        int state = e.getStateChange();
	        String command = ((JRadioButton) e.getSource()).getActionCommand();
	        Respuesta r = null;
	        if (state == ItemEvent.SELECTED) {
	        	r = tablaRespuestasAgregadas.get(Integer.parseInt(command));
	        	listaRespuestasAlumno.add(r);
	        } else if (state == ItemEvent.DESELECTED) {
	        	r = tablaRespuestasAgregadas.get(Integer.parseInt(command));
	        	listaRespuestasAlumno.remove(r);
	        }
	        
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
