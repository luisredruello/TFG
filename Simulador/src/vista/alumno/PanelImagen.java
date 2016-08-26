package vista.alumno;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import controlador.Controlador;

public class PanelImagen extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String normal = "normal";
	private static String bn = "bn";
	private static String organico = "organico";
	private static String inorganico = "inorganico";
	private JLabel picture;
	private Controlador control;
	
	public PanelImagen(Controlador c){
		this.control=c;
		initWindow();
	}
	
	/**
	 * Inicia la Interfaz Gráfica
	 */
	private void initWindow(){
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Imagen"));
		
		//Imagen
		byte[] array = control.getImageBytes(normal, 1, 1);
		picture = new JLabel();
		picture.setIcon(new ImageIcon(array));
		picture.addMouseListener(new MouseAdapter(){
			
			@Override
			  public void mouseClicked(MouseEvent e) {
			     System.out.println(e.getPoint());
			  }
			
		});
		
		this.add(picture, BorderLayout.CENTER);
		
		//Check Button (B\N, Organico e Inorganico)
		JRadioButton normalButton = new JRadioButton(normal);
		normalButton.setActionCommand(normal);
		normalButton.setSelected(true);
		
		JRadioButton bnButton = new JRadioButton(bn);
        bnButton.setActionCommand(bn);

        JRadioButton orgButton = new JRadioButton(organico);
        orgButton.setActionCommand(organico);

        JRadioButton inorgButton = new JRadioButton(inorganico);
        inorgButton.setActionCommand(inorganico);
        
        normalButton.addActionListener(this);
        bnButton.addActionListener(this);
        orgButton.addActionListener(this);
        inorgButton.addActionListener(this);
        
        ButtonGroup group = new ButtonGroup();
        group.add(normalButton);
        group.add(bnButton);
        group.add(orgButton);
        group.add(inorgButton);
        
        //Ponemos los checkButton en una fila
        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(bnButton);
        radioPanel.add(orgButton);
        radioPanel.add(inorgButton);
        
        this.add(radioPanel, BorderLayout.EAST);
        
        this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		picture.setIcon(createImageIcon(normal));
		
	};
	
	/** Returns an ImageIcon, or null if the path was invalid. */
    private ImageIcon createImageIcon(String tipo) {
        byte[] bytes = control.getImageBytes(tipo, 1, 1);
        if (bytes != null) {
            return new ImageIcon(bytes);
        } else {
            System.err.println("No se ha podido cargar la imagen");
            return null;
        }
    }

}
