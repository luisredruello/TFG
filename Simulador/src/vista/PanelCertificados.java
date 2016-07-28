package vista;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controlador.Controlador;
import logica.Certificacion;
import logica.ExamenTeorico;

public class PanelCertificados extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<Certificacion> comboCertificados;
	private Certificacion certificado;
	private List<Certificacion> listaCertificados;
	private Controlador control;
	
	public PanelCertificados(Controlador c){
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
				
				
		this.add(panelInfo,BorderLayout.NORTH);
				
		JTabbedPane panelCertificados = new JTabbedPane();
				
		this.certificado = this.comboCertificados.getItemAt(0);
				
		JComponent panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		initExamenPractico(panel3);
				
		JComponent panel4 = new JPanel();
		panel4.setLayout(new BorderLayout());
		initExamenTeorico(panel4);		
				
		panelCertificados.add(this.certificado.pintaExamenPractico(), panel3);
		panelCertificados.add(this.certificado.getTeorico().getNombre(), panel4);
				
		updatePaneExamen(this.comboCertificados,panelCertificados);
				
		this.add(panelCertificados, BorderLayout.CENTER);
		
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
