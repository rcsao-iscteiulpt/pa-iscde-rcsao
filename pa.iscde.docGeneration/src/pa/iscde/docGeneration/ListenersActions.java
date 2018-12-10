package pa.iscde.docGeneration;


import java.io.File;

import pt.iscte.pidesco.javaeditor.service.JavaEditorListener;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;

public class ListenersActions extends JavaEditorListener.Adapter {
	
	private JavaEditorServices s;

	public ListenersActions(JavaEditorServices editorservice) {
		this.s= editorservice;
	}
	
	public void fileSaved(File file) {
		DocGenView.getInstance();
	}

	

}

