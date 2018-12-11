package pa.iscde.docGeneration;


import java.io.File;

import pt.iscte.pidesco.javaeditor.service.JavaEditorListener;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;

public class EditorListenersActions extends JavaEditorListener.Adapter {
	
	private JavaEditorServices s;

	public EditorListenersActions(JavaEditorServices editorservice) {
		this.s= editorservice;
	}
	
	public void fileSaved(File file) {
		ClassInfoChecker c = new ClassInfoChecker();
		s.parseFile(file, c);
		DocGenView.getInstance().reUpdateClass(c, file);
	}

	

}

