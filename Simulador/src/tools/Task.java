package tools;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 * Clase que va a implementar la nueva tarea para controlar el progreso del examen
 * @author Jefferson
 *
 */
public class Task extends SwingWorker<Void, Void> {
	
	private JFrame frame;
	private int time;
	
	public Task(){}
	
	public Task(JFrame v, int t){
		frame=v;
		time=t;
	}
    /*
     * Main task. Executed in background thread.
     */
    @Override
    public Void doInBackground() {
        int progress = 0;
        //Initialize progress property.
        setProgress(0);
        while (progress < 100) {
            //Cambiamos el tiempo que debe durar el examen, es decir, dormimos el thread que realiza la cuenta.
            try {
                Thread.sleep(time);
            } catch (InterruptedException ignore) {}
            progress++;
            setProgress(progress);
        }
        return null;
    }

    /*
     * Executed in event dispatching thread
     */
    @Override
    public void done() {
    	if (!isCancelled()){
    		JOptionPane.showMessageDialog(null,"Se ha acabado el tiempo");
        	frame.dispose();
    	}    	
        
    }
}
