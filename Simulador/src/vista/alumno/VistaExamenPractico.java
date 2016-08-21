package vista.alumno;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import controlador.Controlador;
import logica.ExamenPractico;
import logica.Usuario;

public class VistaExamenPractico extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controlador control;
	private ExamenPractico practico;
	private Usuario alumno;
	private JProgressBar progressBar;
	
	public VistaExamenPractico(Controlador c, ExamenPractico ex, Usuario u){
		this.control=c;
		this.practico=ex;
		this.alumno=u;
		initWindow();
	}
	
	/**
	 * Inicializa la interfaz gráfica
	 */
	private void initWindow(){
		this.setTitle("Examen Práctico");
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(this.getToolkit().getScreenSize());
		
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
		
		//Panel Inferior (3 Botones, avanzar, retroceder, comprobar)
		JPanel panelInferior = new JPanel();
		
		JButton botonRetroceder = new JButton("Anterior");
		JButton botonAvanzar = new JButton("Siguiente");
		JButton botonResultado = new JButton("Comprobar");
		
		panelInferior.add(botonRetroceder);
		panelInferior.add(botonAvanzar);
		panelInferior.add(botonResultado);
		
		this.add(panelInferior, BorderLayout.SOUTH);
		this.setVisible(true);
	}

}
