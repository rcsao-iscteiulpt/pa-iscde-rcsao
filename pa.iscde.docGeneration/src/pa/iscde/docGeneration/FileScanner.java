package pa.iscde.docGeneration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileScanner {

	/**
	 * Method which founds the position of the first instance of a given String in a file 
	 * @param f File, file where the given word will be searched
	 * @param word String, word searched through the file
	 * @return offset, it's the number of characters counted until the word's position <br>
	 * returns -1 if the word doesn't exists in the given file
	 * @exception IOException throws if file doesn't exists
	 */
	public int ScanforWord(File f, String word) {
		int offset = 0;
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(f));

			String line;

			while ((line = br.readLine()) != null) {
				if (line.indexOf(word) != -1) {
					offset += line.indexOf(word);
					return offset;
				} else {
					offset += line.length();
				}
				offset += 2;
			}
			
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return -1;

	}

	/**
	 * Method which selects a certain sequence of strings on the JavaEditor's file
	 * @param f File, file where word will be selected
	 * @param offset Int word's position in the file
	 * @param lenght Int lenght of the word to be selected
	 */
	public void Select(File f, int offset, int lenght) {
		Activator.getInstance().getEditorservice().selectText(f, offset, lenght);
	}
}
