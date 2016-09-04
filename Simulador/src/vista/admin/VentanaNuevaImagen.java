package vista.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import logica.ExamenPractico;
import logica.ObjetoProhibido;
import logica.TipoArma;

public class VentanaNuevaImagen extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controlador control;
	private ExamenPractico practico;
	private boolean limpia;
	private File fileNormal;
	private byte[] arrayNormal;
	private byte[] arrayBN;
	private byte[] arrayOrganico;
	private byte[] arrayInorganico;
	private DibujaPanel panelImagen;
	private JComboBox<TipoArma> comboTipo;
	
	public VentanaNuevaImagen(Controlador c, ExamenPractico p, File f){
		this.control=c;
		this.practico=p;
		this.limpia=true;
		this.fileNormal=f;
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
		arrayNormal = control.getBytesFromFile(fileNormal);
		
		panelImagen = new DibujaPanel(arrayNormal);
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
        
        TipoArma[] tipo = iniciaComboTipoArma();
        if (tipo!=null) comboTipo = new JComboBox<TipoArma>(tipo);
        else comboTipo = new JComboBox<TipoArma>();

        JPanel radioPanel = new JPanel();
        radioPanel.add(option1);
        radioPanel.add(option2);
        radioPanel.add(comboTipo);
        radioPanel.setBounds(220, 600, 350, 100);
		
		//Botones de aceptar y cancelar
        JButton bAceptar = new JButton("Subir Imagen");
		bAceptar.setBounds(240, 680, 150, 25);
		contenedor.add(bAceptar);
		
		subirImagenAlSistema(bAceptar, this);
				
		JButton bCancelar = new JButton("Cancelar");		
		bCancelar.setBounds(420, 680, 100, 25);
		contenedor.add(bCancelar);
		
		cancela(bCancelar,this);
        
        contenedor.add(radioPanel);
		
		this.setVisible(true);
		
	}

	private void subirImagenAlSistema(JButton bAceptar, final JFrame v) {
		bAceptar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if (arrayNormal!=null && arrayBN != null && arrayOrganico!=null && arrayInorganico!=null){
					int resul = 0;
					if (limpia){
						resul = control.subeImagenLimpia(practico.getId_examen(),arrayNormal
								,arrayBN,arrayOrganico,arrayInorganico);
					}
					else {
						ObjetoProhibido prohibido = new ObjetoProhibido();
						int ind = comboTipo.getSelectedIndex();
						if (panelImagen.getPosx()>=0 && panelImagen.getPosy()>=0
								&& panelImagen.getAlto()>=0 && panelImagen.getAncho()>=0 && ind!=-1){
							TipoArma tt = comboTipo.getItemAt(ind);
							prohibido.setPosx(panelImagen.getPosx());
							prohibido.setPosy(panelImagen.getPosy());
							prohibido.setAlto(panelImagen.getAlto());
							prohibido.setAncho(panelImagen.getAncho());
							resul = control.subeImagenProhibido(practico.getId_examen(),arrayNormal,
									arrayBN,arrayOrganico,arrayInorganico,prohibido,tt.getId_arma());
						}
						else JOptionPane.showMessageDialog(null, "Debes seleccionar el area donde se encuentra"
								+ "el objeto prohibido");
					}
					if (resul>0) JOptionPane.showMessageDialog(null, "Se han subido correctamente los archivos");
					else JOptionPane.showMessageDialog(null, "Ha habido un error al subir los archivos");
					v.dispose();
				}
				else JOptionPane.showMessageDialog(null, "Debes subir las 4 imágenes, primero");
			}
			
		});
		
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
					File file = seleccion.getSelectedFile();
					arrayBN = control.getBytesFromFile(file);
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
					File file = seleccion.getSelectedFile();
					arrayOrganico = control.getBytesFromFile(file);
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
					File file = seleccion.getSelectedFile();
					arrayInorganico = control.getBytesFromFile(file);
				}
				else JOptionPane.showMessageDialog(null,"No se ha seleccionado ningún archivo");
			}
			
		});
	}
	
	/**
	 * Devuelve un array de TipoArma
	 * @return TipoArma[]
	 */
	private TipoArma[] iniciaComboTipoArma(){
		List<TipoArma> list = control.getListaTipoArma();
		if (list!=null){
			TipoArma[] tipos = new TipoArma[list.size()];
			Iterator<TipoArma> it = list.iterator();
			int i=0;
			while(it.hasNext()){
				tipos[i] = it.next();
				i++;
			}
			return tipos;
		}
		else return null;
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
