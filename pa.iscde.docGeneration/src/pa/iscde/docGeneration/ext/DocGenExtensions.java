package pa.iscde.docGeneration.ext;


import java.util.ArrayList;

public interface DocGenExtensions {

	/**
	 * Event that happens whenever a doubleclick is performed on the Documentation View
	 * @param info list containing info about the clicked object
	 * @param type char type of item clicked, e.g ('C' = Constructor, 'M' = Method, 'F' = Field)
	 */
	void doubleClick(ArrayList<String> info, char type);
	
}
