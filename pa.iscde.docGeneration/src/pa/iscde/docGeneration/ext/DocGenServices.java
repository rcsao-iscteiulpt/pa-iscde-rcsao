package pa.iscde.docGeneration.ext;

import java.io.File;

import InfoClasses.Modifiers;


public interface DocGenServices {

	/**
	 * Opens documentation on a new tab for the given file if it isn't already open <br>
	 * If it is, it selects the given file
	 * @param File (non-null)
	 */
	void openFile(File f);
	
	/** Still isnt implemented!!!!!!!!!!
	 * Get the selected file on the viewer
	 * @return a reference for the selected file, or null if it doesn't exists
	 */
	File getSelectedFile();
	
	
	void filterModifier(String filterword);


}
	

