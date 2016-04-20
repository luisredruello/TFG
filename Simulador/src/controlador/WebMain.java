package controlador;



import java.awt.Desktop;
import java.io.File;

//Cross platform solution to view a PDF file
public class WebMain {

	public static void main(String[] args) {

	  try {

		File pdfFile = new File("C:\\Memoria.pdf");
		if (pdfFile.exists()) {

			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(pdfFile);
			} else {
				System.out.println("Awt Desktop is not supported!");
			}

		} else {
			System.out.println("File is not exists!");
		}

		System.out.println("Done");

	  } catch (Exception ex) {
		ex.printStackTrace();
	  }

	}
}
