package pa.iscde.docGeneration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileScanner {

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

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return -1;

	}

	public void Select(File f, int offset, int lenght) {
		Activator.getInstance().getEditorservice().selectText(f, offset, lenght);
	}
}
