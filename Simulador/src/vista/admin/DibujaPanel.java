package vista.admin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DibujaPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Point inicio;
	private boolean dibujando;
	private Point actual;
	private BufferedImage background;
	private int posx;
	private int posy;
	private int ancho;
	private int alto;
	
	public DibujaPanel(byte[] b) {
		dibujando = false;
		this.posx=-1;
		this.posy=-1;
		this.alto=-1;
		this.ancho=-1;
		
		try{
			InputStream in = new ByteArrayInputStream(b);
			background = ImageIO.read(in);
			in.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		final JPanel that = this;
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dibujando = true;
				inicio = e.getPoint();
				Point inicioImagen = toImageContext(inicio);
				posx=inicioImagen.x;
				posy=inicioImagen.y;
				System.out.println("origen: X "+posx+" Y: "+posy);
				actual = e.getPoint();
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				dibujando = false;
			}
		});
		
		this.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				actual = e.getPoint();
				that.repaint();
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
            int x = (getWidth() - background.getWidth()) / 2;
            int y = (getHeight() - background.getHeight()) / 2;
            g.drawImage(background, x, y, background.getWidth(), background.getHeight(), this);
        }
		if (dibujando && inicioCorrecto(inicio)) {
			Point punto = checkBorder(actual);
			
			ancho = Math.abs(inicio.x - punto.x);
			alto = Math.abs(inicio.y - punto.y);
			int x = Math.min(inicio.x, punto.x);
			int y = Math.min(inicio.y, punto.y);
			
			System.out.println("ancho: " + ancho+" alto: " + alto);
			g.setColor(Color.BLACK);
			g.drawRect(x, y, ancho, alto);
		}
	}
	
	/**
	 * Comprueba que no estamos saliendo de la imagen
	 * @param p Point
	 * @return Point con los parametros verificados para no salir de la imagen
	 */
	private Point checkBorder(Point p){
		Point limite = getImageLimits();
		Point inicio = getImageLocation();
		Point resul = new Point();
		resul.setLocation(p);
		if (p.x>limite.x) resul.x=limite.x;
		if (p.x<=inicio.x) resul.x=inicio.x;
		if (p.y>=limite.y) resul.y=limite.y;
		if (p.y<=inicio.y) resul.y=inicio.y;
		return resul;
	}
	
	/**
	 * Comprueba que clickamos dentro del area de la imagen
	 * @param p Point
	 * @return
	 */
	private boolean inicioCorrecto(Point p){
		Point aux = getImageLocation();
		if (p!=null){
			Point limite = getImageLimits();
			return p.x>=aux.x && p.y>=aux.y && p.x<=limite.x && p.y<=limite.y;
		}
		else return false;
	}
	
	/**
	 * Devuelve un punto que limite la imagen
	 * @return Point
	 */
	public Point getImageLimits(){
		Point aux = getImageLocation();
		return new Point(aux.x+background.getWidth(), aux.y+background.getHeight());
	}
	
	/**
	 * Devuelve el origen (x,y) de la imagen
	 * @return Point
	 */
	public Point getImageLocation() {
        Point p = null;
        if (background != null) {
            int x = (getWidth() - background.getWidth()) / 2;
            int y = (getHeight() - background.getHeight()) / 2;
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

	/**
	 * @return the posx
	 */
	public int getPosx() {
		return posx;
	}

	/**
	 * @param posx the posx to set
	 */
	public void setPosx(int posx) {
		this.posx = posx;
	}

	/**
	 * @return the posy
	 */
	public int getPosy() {
		return posy;
	}

	/**
	 * @param posy the posy to set
	 */
	public void setPosy(int posy) {
		this.posy = posy;
	}

	/**
	 * @return the ancho
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * @param ancho the ancho to set
	 */
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	/**
	 * @return the alto
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * @param alto the alto to set
	 */
	public void setAlto(int alto) {
		this.alto = alto;
	}

}
