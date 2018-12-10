package pa.iscde.docGeneration;

import java.io.File;

import pa.iscde.pidesco.docGeneration.services.DemoServices;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.model.ClassElement;
import pt.iscte.pidesco.projectbrowser.model.SourceElement;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserListener;

public class BrowserListenersAction extends ProjectBrowserListener.Adapter {

	private DemoServices services;
	private JavaEditorServices s;

	public BrowserListenersAction(DemoServices services, JavaEditorServices editorservice) {
		this.services = services;
		this.s= editorservice;
	}

	@Override
	public void doubleClick(SourceElement element) {
		if (element instanceof ClassElement) {
			File f = ((ClassElement) element).getFile();
			ClassInfoChecker c = new ClassInfoChecker();
			s.parseFile(f,c);
			services.openFile(f,c);
		}
	}

}
