package vista.alumno;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import controlador.Controlador;
import logica.ExamenPractico;
import logica.Usuario;

public class VistaExamenPractico extends JFrame implements ItemListener{

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
	private int pagina;
	
	public VistaExamenPractico(Controlador c, ExamenPractico ex, Usuario u){
		this.control=c;
		this.practico=ex;
		this.alumno=u;
		this.panelImagen = new LinkedList<PanelImagen>();
		this.pagina=0;
		initWindow();
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
		vistaImagenes = new JPanel(new CardLayout());
		vistaImagenes.add(new PanelImagen(),"1");
		
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

}
