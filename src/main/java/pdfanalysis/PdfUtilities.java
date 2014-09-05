package pdfanalysis;

import javax.swing.*;
import java.io.FileNotFoundException;

public class PdfUtilities {

	public static String chooseFolder() throws FileNotFoundException {
		JFileChooser j = new JFileChooser();
		j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		j.showOpenDialog(j);
		if (j.getSelectedFile() == null) {
			System.out.println("No folder was chosen");
		} else {
			return j.getSelectedFile().getPath();
		}
		return null;
	}

}
