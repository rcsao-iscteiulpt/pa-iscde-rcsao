package pa.iscde.pidesco.docGeneration.services;

import java.io.File;

import InfoClasses.Modifiers;


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
	File getopenedFile();
	
	
	void filterModifier(String filterword);


}
	

