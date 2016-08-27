package vista.alumno;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private Point click;
	private Frame parent;
	
	public PanelImagen(Controlador c, Imagen im, int pos, Frame padre){
		this.control=c;
		this.imagen=im;
		this.posicion = pos+1;
		this.click = new Point();
		this.parent=padre;
		//this.tablaPuntos=table;
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
			    click.setLocation(e.getX(), imgContext.y);
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
        agregaImagenLimpia(limpia);
        JButton peligro = new JButton("Objeto Peligroso");
        agregaImagenPeligro(peligro);
        inferiorDerecha.add(limpia);
        inferiorDerecha.add(peligro);
        
        panelDerecho.add(inferiorDerecha);
        
        this.add(panelDerecho);
        
        this.setVisible(true);
	}
	
	private void agregaImagenLimpia(JButton limp) {
		limp.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				HashMap<Integer, Point> tablaPuntos = ((VistaExamenPractico) parent).getImagenPuntos();
				if (!tablaPuntos.containsKey(imagen.getId_imagen())){
					tablaPuntos.put(imagen.getId_imagen(), null);
					((VistaExamenPractico) parent).setImagenPuntos(tablaPuntos);
					((VistaExamenPractico) parent).updateNumPreguntas(tablaPuntos.size());
				}
			}			
		});
		
	}

	private void agregaImagenPeligro(final JButton pel) {
		pel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(pel,"Pulsa en el elemento prohibido, por favor");
				HashMap<Integer, Point> tablaPuntos = ((VistaExamenPractico) parent).getImagenPuntos();
				if (!tablaPuntos.containsKey(imagen.getId_imagen())){
					System.out.println("La imagen es "+imagen.getId_imagen());
					tablaPuntos.put(imagen.getId_imagen(), click);
					((VistaExamenPractico) parent).setImagenPuntos(tablaPuntos);
					((VistaExamenPractico) parent).updateNumPreguntas(tablaPuntos.size());
				}
			}
			
		});
		
	}

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

	@Override
	public void actionPerformed(ActionEvent e) {
		picture.setIcon(createImageIcon(((JRadioButton) e.getSource()).getActionCommand()));
		
	};
	
    
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
