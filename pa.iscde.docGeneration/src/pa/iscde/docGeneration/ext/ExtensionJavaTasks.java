package pa.iscde.docGeneration.ext;


import java.io.File;

import pa.iscde.docGeneration.DocGenView;
import pa.iscde.javaTasks.ext.Task;
import pa.iscde.javaTasks.ext.TasksAction;

public class ExtensionJavaTasks implements TasksAction {
	
	public void doubleClick(Task t) {
		DocGenView.getInstance().openfile(
				new File(t.getPath() + "/" + t.getResource()));						
	}
}
