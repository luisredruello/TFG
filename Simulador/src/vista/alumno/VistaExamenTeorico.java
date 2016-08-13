package vista.alumno;

import java.awt.BorderLayout;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import logica.*;

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
		this.setBounds(100, 100, 700, 500);
		
		//Panel Superior Datos
		JPanel panelSup = new JPanel();
		JLabel labelDesc = new JLabel(this.teorico.getDescripcion());
		panelSup.add(labelDesc);
		this.add(panelSup, BorderLayout.NORTH);
		
		//Panel Central Lista Preguntas
		JPanel panelPreguntas = new JPanel();
		JScrollPane scroll = new JScrollPane();
		scroll.setBorder(new TitledBorder("Lista Preguntas"));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.add(scroll, BorderLayout.CENTER);
		
		scroll.setViewportView(panelPreguntas);
		
		initPanelPreguntas(panelPreguntas);
		
		//Panel Inferior Boton Comprobar Resultados
		
		JButton botonResultado = new JButton("Comprobar");
		comprobarResultado(botonResultado);
		
		this.setVisible(true);
		
	}

	private void initPanelPreguntas(JPanel panel) {
			
		
	}
	
	private void comprobarResultado(JButton botonResultado) {
		// TODO Auto-generated method stub
		
	}

}
