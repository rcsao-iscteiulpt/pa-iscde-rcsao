package pa.iscde.docGeneration.ext;

import java.io.File;



public interface DocGenServices {

	/**
	 * Opens documentation on a new tab for the given file if it isn't already open <br>
	 * If it is, it selects the given file
	 * @param File (non-null)
	 */
	void openFile(File f);
	
	/**
	 * Get the selected file on the viewer
	 * @return a reference for the selected file, or null if it doesn't exists
	 */
	File getSelectedFile();
	
	/**
	 * Filters documentation tables according to given String
	 * @param String filterWord (non-null)
	 */
	void filterWord(String filterWord);
	
	/**
	 * Erases all apllied filters on the Documention View
	 */
	void cleanFilters();
	
	


}
	

