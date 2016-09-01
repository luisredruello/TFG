package vista.alumno;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import controlador.Controlador;
import logica.Imagen;
import logica.ObjetoProhibido;
import logica.TipoArma;

public class PanelPruebaImagen extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private static String normal = "normal";
	private static String bn = "bn";
	private static String organico = "organico";
	private static String inorganico = "inorganico";
	private JLabel picture;
	private JLabel tipoArma;
	private ImageIcon img;
	private Controlador control;
	private Imagen imagen;
	private int posicion;
	private Point click;
	private Frame parent;
	private boolean pulsado;
	private ObjetoProhibido prohibido;
	
	public PanelPruebaImagen(Controlador c, Imagen image, int pos, Frame frame){
		this.control=c;
		this.imagen=image;
		this.posicion=pos+1;
		this.parent=frame;
		this.pulsado=false;
		this.tipoArma=new JLabel();
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
				if (click==null){
					click=new Point();
					Point panelPoint = e.getPoint();
	                Point imgContext = toImageContext(panelPoint);
	                System.out.println("Punto x: "+e.getX()+" Punto y: "+imgContext.y);
				    click.setLocation(e.getX(), imgContext.y);
				}
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
        JPanel inferiorDerecha = new JPanel(new BorderLayout());
        inferiorDerecha.setBorder(new TitledBorder("Opciones"));
        
        JButton limpia = new JButton("Sin Peligro");
        agregaImagenLimpia(limpia);
        JButton peligro = new JButton("Objeto Peligroso");
        agregaImagenPeligro(peligro);
        
        JPanel panelBotones = new JPanel();
        panelBotones.add(limpia);
        panelBotones.add(peligro);
        
        inferiorDerecha.add(panelBotones,BorderLayout.NORTH);
        
        JPanel panelTipoArma = new JPanel();
        panelTipoArma.add(tipoArma);
        tipoArma.setVisible(false);
        
        inferiorDerecha.add(panelTipoArma,BorderLayout.SOUTH);
        
        panelDerecho.add(inferiorDerecha);
        
        this.add(panelDerecho);
        
        this.setVisible(true);
	}
	
	private void agregaImagenLimpia(final JButton limp) {
		limp.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!pulsado){
					if (imagen.getId_objeto()==0){
						limp.setForeground(Color.GREEN);
						((VistaPruebaPractico) parent).aumentaCorrectas();
						JOptionPane.showMessageDialog(null,"Respuesta Correcta");
					}
					else {
						limp.setForeground(Color.RED);
						JOptionPane.showMessageDialog(null,"Has fallado");
					}
					((VistaPruebaPractico) parent).updateNumPreguntas();
					pulsado=true;
				}
				else JOptionPane.showMessageDialog(null,"Ya has elegido una opción");
				
			}		
		});
		
	}

	private void agregaImagenPeligro(final JButton pel) {
		pel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!pulsado){
					if (click!=null){
						pulsado=true;
						if (imagen.getId_objeto()!=0){
							//correcta
							prohibido = control.getObjetoProhibido(imagen.getId_objeto());
							//Comprobamos si ha clickado correctamente
							if (prohibido.estaDentro(click)){
								pel.setForeground(Color.GREEN);
								JOptionPane.showMessageDialog(null,"Has acertado");
								((VistaPruebaPractico) parent).aumentaCorrectas();
								tipoArma.setVisible(true);
								TipoArma tipo = control.getTipoArma(prohibido.getId_arma());
								tipoArma.setText("El objeto prohibido es del tipo: "+tipo.getDescripcion());
							}
							else pel.setForeground(Color.RED);
						}
						else {
							//Fallo
							JOptionPane.showMessageDialog(null,"Has fallado");
							pel.setForeground(Color.RED);
						}
						((VistaPruebaPractico) parent).updateNumPreguntas();
					}
					else JOptionPane.showMessageDialog(pel,
							"Pulsa en el elemento prohibido y vuelve a pulsar este botón, por favor");
				}
				else JOptionPane.showMessageDialog(null,"Ya has elegido una opción");
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
