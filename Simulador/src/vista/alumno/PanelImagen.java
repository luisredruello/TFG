package vista.alumno;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import logica.Imagen;

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
	private ImageIcon img;
	private Controlador control;
	private Imagen imagen;
	private int posicion;
	
	public PanelImagen(Controlador c, Imagen im, int pos){
		this.control=c;
		this.imagen=im;
		this.posicion = pos+1;
		initWindow();
	}
	
	/**
	 * Inicia la Interfaz Gráfica
	 */
	private void initWindow(){
		this.setLayout(new GridLayout(1,2));
		this.setBorder(new TitledBorder("Imagen: "+posicion));
		
		//Imagen
		byte[] array = control.getImageBytes(normal, imagen.getId_imagen(), imagen.getId_examen());
		img = new ImageIcon(array);
		picture = new JLabel();
		picture.setIcon(img);
		picture.addMouseListener(new MouseAdapter(){
			
			@Override
			  public void mouseClicked(MouseEvent e) {
				Point panelPoint = e.getPoint();
                Point imgContext = toImageContext(panelPoint);
			    System.out.println(e.getPoint()+" Relativo a: "+imgContext);
			  }
			
		});
		
		this.add(picture);
		
		//Panel Derecho (Con los Check Buttons y Los Botones de Seleccion)
		JPanel panelDerecho = new JPanel(new GridLayout(2,1));
		
		//Check Button (Normal, B\N, Organico e Inorganico)
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
        radioPanel.add(normalButton);
        radioPanel.add(bnButton);
        radioPanel.add(orgButton);
        radioPanel.add(inorgButton);
        
        panelDerecho.add(radioPanel);
        
        //Panel con los botones
        JPanel inferiorDerecha = new JPanel();
        inferiorDerecha.setBorder(new TitledBorder("Opciones"));
        
        JButton limpia = new JButton("Sin Peligro");
        JButton peligro = new JButton("Objeto Peligroso");
        inferiorDerecha.add(limpia);
        inferiorDerecha.add(peligro);
        
        panelDerecho.add(inferiorDerecha);
        
        this.add(panelDerecho);
        
        this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		picture.setIcon(createImageIcon(((JRadioButton) e.getSource()).getActionCommand()));
		
	};
	
	/** Returns an ImageIcon, or null if the path was invalid. */
    private ImageIcon createImageIcon(String tipo) {
        byte[] bytes = control.getImageBytes(tipo, imagen.getId_imagen(), imagen.getId_examen());
        if (bytes != null) {
            return new ImageIcon(bytes);
        } else {
            System.err.println("No se ha podido cargar la imagen");
            return null;
        }
    }
    
    public Point getImageLocation() {
        Point p = null;
        if (img != null) {
            int x = (picture.getWidth() - img.getIconWidth()) / 2;
            int y = (picture.getHeight() - img.getIconHeight()) / 2;
            p = new Point(x, y);
        }
        return p;
    }

    public Point toImageContext(Point p) {
        Point imgLocation = getImageLocation();
        Point relative = new Point(p);
        relative.x -= imgLocation.x;
        relative.y -= imgLocation.y;
        return relative;
    }

}
