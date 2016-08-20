package vista.admin;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controlador.Controlador;
import logica.Certificacion;
import logica.ExamenPractico;
import logica.ExamenTeorico;
import logica.ModuloTeorico;

public class PanelCertificadosAdmin extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Certificacion> comboCertificados;
	private List<Certificacion> listaCertificados;
	private Controlador control;
	private JComboBox<ExamenTeorico> comboTeorico;
	private JComboBox<ExamenPractico> comboPractico;
	
	public PanelCertificadosAdmin(Controlador c){
		this.listaCertificados = null;
		this.control = c;
		initCertificados();
	}
	
	/**
	 * Crea la interfaz gráfica
	 */
	private void initCertificados() {
		this.setLayout(new BorderLayout());
		
		//Panel superior
		Certificacion[] certificadosCombo = llenaCertificados();
				
		if (certificadosCombo!=null) this.comboCertificados = new JComboBox<Certificacion>(certificadosCombo); 
		else this.comboCertificados = new JComboBox<Certificacion>();
				
		JPanel panelInfo = new JPanel();
		panelInfo.add(new JLabel("Certificados: "));
		panelInfo.add(comboCertificados);
		
		JLabel labelSubir = new JLabel("Subir Módulo Teórico:");
		JButton botonUpload = new JButton("Upload File");
		panelInfo.add(labelSubir);
		panelInfo.add(botonUpload);	
		
		subirArchivos(botonUpload,comboCertificados);
				
		this.add(panelInfo,BorderLayout.NORTH);
		
		//Paneles Inferiores
		JTabbedPane panelCertificados = new JTabbedPane();
		
		//Examen Practico
		JComponent panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		initPanelExamenPractico(panel1);
		
		//Examen Teorico
		JComponent panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		initPanelExamenTeorico(panel2);
				
		panelCertificados.add("Administración Examen Práctico", panel1);
		panelCertificados.add("Administración Examen Teórico", panel2);
				
		updateCombosInferiores(comboCertificados,comboTeorico,comboPractico);
				
		this.add(panelCertificados, BorderLayout.CENTER);
		
	}

	private void subirArchivos(final JButton botonUpload,final JComboBox<Certificacion> combo) {
		botonUpload.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int ind = combo.getSelectedIndex();
				int resul = 0;
				JFileChooser seleccion=new JFileChooser();
				if(seleccion.showOpenDialog(null) == JFileChooser.APPROVE_OPTION && ind != -1){
					File file = seleccion.getSelectedFile();
					FileInputStream is;
					int maxMod = getNumModulos(combo.getItemAt(ind).getNivel());
					try {
						byte[] fileContent = new byte[(int)file.length()];
						is = new FileInputStream(file);
						is.read(fileContent);
						is.close();
						resul = control.subeTeoria(combo.getItemAt(ind).getNivel(),maxMod,fileContent);
					} catch (IOException i) {
						i.printStackTrace();
					}
					if (resul>0) JOptionPane.showMessageDialog(botonUpload, "Se ha subido correctamente el archivo");
					else JOptionPane.showMessageDialog(botonUpload,"Ha ocurrido un error al subir el archivo");
				}
				else JOptionPane.showMessageDialog(botonUpload,"No se ha seleccionado ningún archivo");				
			}
			
			private int getNumModulos(int nivel){
				List<ModuloTeorico> list = control.getListaModulosTeoricos(nivel);
				if (list!=null) return list.size();
				else return 0;
			}
			
		});
		
	}

	private void updateCombosInferiores(final JComboBox<Certificacion> c
			, final JComboBox<ExamenTeorico> teo
			, final JComboBox<ExamenPractico> prac) {
		c.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				int ind = c.getSelectedIndex();
				if (ind!=-1){
					ExamenTeorico t = c.getItemAt(ind).getTeorico();
					ExamenPractico p = c.getItemAt(ind).getPractico();
					if (t!=null){
						teo.removeAllItems();
						teo.addItem(t);
					}
					if (p!=null){
						prac.removeAllItems();
						prac.addItem(p);
					}
				}
			}			
		});
		
	}

	private void initPanelExamenTeorico(JComponent panel4) {
		comboTeorico = new JComboBox<ExamenTeorico>();
		comboTeorico.addItem(listaCertificados.get(0).getTeorico());
		JPanel p = new JPanel();
		p.add(comboTeorico);
		panel4.add(p, BorderLayout.NORTH);
	}

	private void initPanelExamenPractico(JComponent panel3) {
		comboPractico = new JComboBox<ExamenPractico>();
		comboPractico.addItem(listaCertificados.get(1).getPractico());
		JPanel pcombo = new JPanel();
		pcombo.add(comboPractico);
		
		JButton botonSubida = new JButton("Subir Imagen");
		JPanel pboton = new JPanel();
		pboton.add(botonSubida);
		
		panel3.add(pcombo, BorderLayout.NORTH);
		panel3.add(pboton, BorderLayout.SOUTH);
	}

	private Certificacion[] llenaCertificados() {
		this.listaCertificados = this.control.getListaCertificados();
		if (listaCertificados != null){
			Certificacion[] resul = new Certificacion[listaCertificados.size()];
			Iterator<Certificacion> it = listaCertificados.iterator();
			int i=0;
			while(it.hasNext()){
				resul[i] = it.next();
				resul[i].setPractico(this.control.getExamenPractico(resul[i].getNivel()));
				resul[i].setTeorico(this.control.getExamenTeorico(resul[i].getNivel()));
				i++;
			}
			return resul;
		}
		else return null;
	}

}
