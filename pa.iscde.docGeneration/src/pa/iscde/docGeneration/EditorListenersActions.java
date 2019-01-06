package pa.iscde.docGeneration;


import java.io.File;

import pt.iscte.pidesco.javaeditor.service.JavaEditorListener;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;

/**
 * Class which implementsJavaEditor's listeners and it's actions
 * @author Ricardo Silva
 *
 */
public class EditorListenersActions extends JavaEditorListener.Adapter {
	
	private JavaEditorServices s;

	/**
	 * Class Constructor
	 * @param services reference from JavaEditor Component
	 */
	public EditorListenersActions(JavaEditorServices editorservice) {
		this.s= editorservice;
	}
	
	
	/**
	 * Action performed when a save happens in JavaEditor
	 * @param file File file to be parsed and updated
	 */
	public void fileSaved(File file) {
		ClassInfoChecker c = new ClassInfoChecker();
		s.parseFile(file, c);
		DocGenView.getInstance().updateFile(c, file);
	}

	

}

