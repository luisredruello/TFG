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

public class PanelImagen extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String bn = "B-N";
	private static String organico = "ContrasteOrganico";
	private static String inorganico = "ContrasteInorganico";
	private JLabel picture;
	
	public PanelImagen(){
		initWindow();
	}
	
	/**
	 * Inicia la Interfaz Gráfica
	 */
	private void initWindow(){
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Imagen"));
		
		//Imagen
		picture = new JLabel();
		picture.addMouseListener(new MouseAdapter(){
			
			@Override
			  public void mouseClicked(MouseEvent e) {
			     
			  }
			
		});
		
		this.add(picture, BorderLayout.CENTER);
		
		//Check Button (B\N, Organico e Inorganico)		
		JRadioButton bnButton = new JRadioButton(bn);
        bnButton.setActionCommand(bn);
        bnButton.setSelected(true);

        JRadioButton orgButton = new JRadioButton(organico);
        orgButton.setActionCommand(organico);

        JRadioButton inorgButton = new JRadioButton(inorganico);
        inorgButton.setActionCommand(inorganico);
        
        bnButton.addActionListener(this);
        orgButton.addActionListener(this);
        inorgButton.addActionListener(this);
        
        ButtonGroup group = new ButtonGroup();
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
		picture.setIcon(createImageIcon("images/"
                + e.getActionCommand()
                + ".gif"));
		
	};
	
	/** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = PanelImagen.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

}
