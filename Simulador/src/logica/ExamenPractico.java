package logica;

public class ExamenPractico extends Examen{
	
	private int numImagenes;
	
	public ExamenPractico (){
		super();
	}
	
	public ExamenPractico(int id, int n, int num){
		super(id, n);
		this.numImagenes=num;
	}

	public int getNumImagenes() {
		return numImagenes;
	}

	public void setNumImagenes(int numImagenes) {
		this.numImagenes = numImagenes;
	}
	
	public String toString(){
		return "Exámen Práctico "+Integer.toString(super.getId_examen());
	}

}
