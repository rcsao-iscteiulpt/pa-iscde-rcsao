package pa.iscde.docGeneration.ext;


import java.io.File;

import pa.iscde.docGeneration.DocGenView;
import pa.iscde.javaTasks.ext.Task;
import pa.iscde.javaTasks.ext.TasksAction;

/**
 * Class implementing TasksAction from JavaTasks Component
 * @author Ricard0
 *
 */
public class ExtensionJavaTasks implements TasksAction {
	
	/**
	 * Action performed whenever a doubleclick happens on the JavaTasks View
	 */
	public void doubleClick(Task t) {
		DocGenView.getInstance().openfile(
				new File(t.getPath() + "/" + t.getResource()));						
	}
}
