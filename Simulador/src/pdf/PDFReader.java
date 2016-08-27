package pdf;

import java.awt.Desktop;
import java.io.File;

//Cross platform solution to view a PDF file
public class PDFReader {

	public static void readPDF(String path) {

	  try {

		File pdfFile = new File(path);
		if (pdfFile.exists()) {

			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(pdfFile);
			} else {
				System.out.println("Awt Desktop is not supported!");
			}

		} else {
			System.out.println("File does not exists!");
		}

		System.out.println("Done");

	  } catch (Exception ex) {
		ex.printStackTrace();
	  }

	}
}
