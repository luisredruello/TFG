package vista.admin;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import logica.ExamenTeorico;

public class VentanaNuevaPregunta extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controlador control;
	private ExamenTeorico teorico;
	private int[] seleccion = {0, 0, 0, 0};
	private JRadioButton option1;
	private JRadioButton option2;
	private JRadioButton option3;
	private JRadioButton option4;
	
	public VentanaNuevaPregunta(Controlador c, ExamenTeorico t){
		this.control=c;
		this.teorico=t;
		initWindow();
	}

	private void initWindow() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(150, 50, 800, 775);
		JPanel contenedor = new JPanel();
		contenedor.setBorder(new TitledBorder("Nueva Pregunta"));
		setContentPane(contenedor);
		contenedor.setLayout(null);
		
		//Nueva Pregunta
		JTextArea nuevaPregunta = new JTextArea(20,5);
		nuevaPregunta.setLineWrap(true);
		
		JScrollPane scrollEnunciado = new JScrollPane(nuevaPregunta);
		scrollEnunciado.setBorder(new TitledBorder("Enunciado"));
		scrollEnunciado.setBounds(10, 30, 750, 100);
		scrollEnunciado.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		contenedor.add(scrollEnunciado);
		
		//Panel Izquierdo Preguntas
		JTextArea respuesta1 = new JTextArea(2,5);
		JScrollPane scrollRespuesta1 = new JScrollPane(respuesta1);
		scrollRespuesta1.setBorder(new TitledBorder("Respuesta 1"));
		scrollRespuesta1.setBounds(10, 160, 350, 100);
		
		JTextArea respuesta2 = new JTextArea(2,5);
		JScrollPane scrollRespuesta2 = new JScrollPane(respuesta2);
		scrollRespuesta2.setBorder(new TitledBorder("Respuesta 2"));
		scrollRespuesta2.setBounds(10, 280, 350, 100);
		
		JTextArea respuesta3 = new JTextArea(2,5);
		JScrollPane scrollRespuesta3 = new JScrollPane(respuesta3);
		scrollRespuesta3.setBorder(new TitledBorder("Respuesta 3"));
		scrollRespuesta3.setBounds(10, 400, 350, 100);
		
		JTextArea respuesta4 = new JTextArea(2,5);
		JScrollPane scrollRespuesta4 = new JScrollPane(respuesta4);
		scrollRespuesta4.setBorder(new TitledBorder("Respuesta 4"));
		scrollRespuesta4.setBounds(10, 520, 350, 100);
		
		contenedor.add(scrollRespuesta1);
		contenedor.add(scrollRespuesta2);
		contenedor.add(scrollRespuesta3);
		contenedor.add(scrollRespuesta4);
		
		//Panel Derecho CheckButtons
        option1 = new JRadioButton("correcta1");
        option1.setActionCommand("correcta1");
        option1.setSelected(true);

        option2 = new JRadioButton("correcta2");
        option2.setActionCommand("correcta2");

        option3 = new JRadioButton("correcta3");
        option3.setActionCommand("correcta3");

        option4 = new JRadioButton("correcta4");
        option4.setActionCommand("correcta4");

        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);
        group.add(option4);

        //Register a listener for the radio buttons.
        option1.addActionListener(this);
        option2.addActionListener(this);
        option3.addActionListener(this);
        option4.addActionListener(this);

        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(option1);
        radioPanel.add(option2);
        radioPanel.add(option3);
        radioPanel.add(option4);        
        radioPanel.setBounds(500, 160, 100, 450);
        
        //Botones Inferiores
        JButton bAceptar = new JButton("Insertar");
		bAceptar.setBounds(240, 650, 90, 25);
		contenedor.add(bAceptar);
		
		insertarPregunta(bAceptar,nuevaPregunta,respuesta1,respuesta2,respuesta3,respuesta4,group);
				
		JButton bCancelar = new JButton("Cancelar");		
		bCancelar.setBounds(360, 650, 90, 25);
		contenedor.add(bCancelar);
		
		cancela(bCancelar,this);
        
        contenedor.add(radioPanel);
		
		this.setVisible(true);
		
	}

	private void insertarPregunta(final JButton b, final JTextArea nuevaPregunta, final JTextArea respuesta1, 
			final JTextArea respuesta2, final JTextArea respuesta3, final JTextArea respuesta4,
			ButtonGroup group) {
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String pregunta = nuevaPregunta.getText();
				String r1 = respuesta1.getText();
				String r2 = respuesta2.getText();
				String r3 = respuesta3.getText();
				String r4 = respuesta4.getText();
				ArrayList<String> list = new ArrayList<String>();
				if (!r1.isEmpty()) list.add(r1);
				if (!r2.isEmpty()) list.add(r2);
				if (!r3.isEmpty()) list.add(r3);
				if (!r4.isEmpty()) list.add(r4);
				if (!pregunta.isEmpty() && list.size()>0){
					int idPregunta = control.insertaPregunta(pregunta,teorico.getId_examen());
					if (idPregunta!=0){
						Iterator<String> it = list.iterator();
						int i=0;
						while (it.hasNext()){
							control.insertaRespuesta(idPregunta,it.next(),seleccion[i]);
							i++;
						}
					}
					else JOptionPane.showMessageDialog(b, "Ha habido un error en la inserción de la pregunta"); 
				}
				else JOptionPane.showMessageDialog(b, "Inserta los Datos");
				
			}
			
		});
		
	}

	private void cancela(JButton b, final VentanaNuevaPregunta v) {
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				v.dispose();
			}
			
		});
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = ((JRadioButton) e.getSource()).getActionCommand();
		switch(command){
			case "correcta1": seleccion[0]=1; break;
			case "correcta2": seleccion[1]=1; break;
			case "correcta3": seleccion[2]=1; break;
			case "correcta4": seleccion[3]=1; break;
			default: seleccion[0]=1; break;
		}
		
	}

}
