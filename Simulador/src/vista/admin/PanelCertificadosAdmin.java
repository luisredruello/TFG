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
import logica.ExamenTeorico;
import logica.ModuloTeorico;

public class PanelCertificadosAdmin extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Certificacion> comboCertificados;
	private Certificacion certificado;
	private List<Certificacion> listaCertificados;
	private Controlador control;
	
	public PanelCertificadosAdmin(Controlador c){
		this.listaCertificados = null;
		this.control = c;
		initCertificados();
	}

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
				
		this.certificado = this.comboCertificados.getItemAt(0);
		
		//Examen Practico
		JComponent panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		initExamenPractico(panel1);
		
		//Examen Teorico
		JComponent panel2 = new JPanel();
		panel2.setLayout(new BorderLayout());
		initExamenTeorico(panel2);
				
		panelCertificados.add(this.certificado.pintaExamenPractico(), panel1);
		panelCertificados.add(this.certificado.getTeorico().getNombre(), panel2);
				
		updatePaneExamen(this.comboCertificados,panelCertificados);
				
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

	private void updatePaneExamen(final JComboBox<Certificacion> comboCertificados2,final JTabbedPane panelCertificados) {
		comboCertificados2.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				int indice = comboCertificados2.getSelectedIndex();
				if (indice != -1){
					ExamenTeorico t = comboCertificados2.getItemAt(indice).getTeorico();
					panelCertificados.setTitleAt(0, comboCertificados2.getItemAt(indice).pintaExamenPractico());
					panelCertificados.setTitleAt(1, t.getNombre());
				}				
			}			
		});
		
	}

	private void initExamenTeorico(JComponent panel4) {
		// TODO Auto-generated method stub
		
	}

	private void initExamenPractico(JComponent panel3) {
		// TODO Auto-generated method stub
		
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
