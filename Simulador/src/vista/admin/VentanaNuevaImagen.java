package vista.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import logica.ExamenPractico;

public class VentanaNuevaImagen extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controlador control;
	private ExamenPractico practico;
	private boolean limpia;
	private File file;
	
	public VentanaNuevaImagen(Controlador c, ExamenPractico p, File f){
		this.control=c;
		this.practico=p;
		this.limpia=true;
		this.file=f;
		initWindow();
	}

	private void initWindow() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(150, 40, 750, 800);
		JPanel contenedor = new JPanel();
		contenedor.setBorder(new TitledBorder("Nueva Imagen"));
		setContentPane(contenedor);
		contenedor.setLayout(null);
		
		//Botones para subir los cuatro tipos de imagenes
		JPanel panelBotonesImagenes = new JPanel();
		panelBotonesImagenes.setBounds(20, 20, 700, 50);
		
		JButton botonBN = new JButton("Imagen Blanco/Negro");
		JButton botonOrganico = new JButton("Imagen Organico");
		JButton botonInorganico = new JButton("Imagen Inorganico");
		
		subeImagenBlancoNegro(botonBN);
		subeImagenOrganico(botonOrganico);
		subeImagenInorganico(botonInorganico);
		
		panelBotonesImagenes.add(botonBN);
		panelBotonesImagenes.add(botonOrganico);
		panelBotonesImagenes.add(botonInorganico);
		
		contenedor.add(panelBotonesImagenes);
		
		//Panel donde se muestra la imagen normal, para seleccionar el objeto prohibido
		byte[] array = control.getBytesFromFile(file);
		
		DibujaPanel panelImagen = new DibujaPanel(array);
		panelImagen.setBounds(10, 80, 715, 500);
		
		contenedor.add(panelImagen);
		
		//Panel con los checkButton para elegir si es una imagen sin tipo prohibido o limpia
		JRadioButton option1 = new JRadioButton("Imagen Limpia");
        option1.setActionCommand("limpia");
        option1.setSelected(true);

        JRadioButton option2 = new JRadioButton("Con Objeto Prohibido");
        option2.setActionCommand("prohibido");

        ButtonGroup group = new ButtonGroup();
        group.add(option1);
        group.add(option2);

        option1.addActionListener(this);
        option2.addActionListener(this);

        JPanel radioPanel = new JPanel();
        radioPanel.add(option1);
        radioPanel.add(option2);     
        radioPanel.setBounds(220, 600, 350, 100);
		
		//Botones de aceptar y cancelar
        JButton bAceptar = new JButton("Subir Imagen");
		bAceptar.setBounds(240, 680, 150, 25);
		contenedor.add(bAceptar);
		
		//subirImagen(bAceptar);
				
		JButton bCancelar = new JButton("Cancelar");		
		bCancelar.setBounds(420, 680, 100, 25);
		contenedor.add(bCancelar);
		
		cancela(bCancelar,this);
        
        contenedor.add(radioPanel);
		
		this.setVisible(true);
		
	}

	private void cancela(JButton bCancelar, final VentanaNuevaImagen ventanaNuevaImagen) {
		bCancelar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaNuevaImagen.dispose();
			}
			
		});
		
	}
	
	
	private void subeImagenBlancoNegro(JButton b){
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser seleccion=new JFileChooser();
				if (seleccion.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					
				}
				else JOptionPane.showMessageDialog(null,"No se ha seleccionado ningún archivo");
				
			}
			
		});
	}
	
	
	private void subeImagenOrganico(JButton b){
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser seleccion=new JFileChooser();
				if (seleccion.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					
				}
				else JOptionPane.showMessageDialog(null,"No se ha seleccionado ningún archivo");
				
			}
			
		});
	}
	
	
	private void subeImagenInorganico(JButton b){
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser seleccion=new JFileChooser();
				if (seleccion.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					
				}
				else JOptionPane.showMessageDialog(null,"No se ha seleccionado ningún archivo");
				
			}
			
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = ((JRadioButton) e.getSource()).getActionCommand();
		switch(command){
			case "limpia": limpia=true; break;
			case "prohibido": limpia=false; break;
			default: limpia=false; break;
		}
		
	}

}
