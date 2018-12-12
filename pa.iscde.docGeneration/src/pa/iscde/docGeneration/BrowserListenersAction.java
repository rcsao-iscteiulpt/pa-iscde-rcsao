package pa.iscde.docGeneration;

import java.io.File;

import pa.iscde.pidesco.docGeneration.services.DocGenServices;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.model.ClassElement;
import pt.iscte.pidesco.projectbrowser.model.SourceElement;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserListener;

public class BrowserListenersAction extends ProjectBrowserListener.Adapter {

	private DocGenServices services;

	public BrowserListenersAction(DocGenServices services) {
		this.services = services;
	}

	@Override
	public void doubleClick(SourceElement element) {
		if (element instanceof ClassElement) {
			File f = ((ClassElement) element).getFile();
			services.openFile(f);
		}
	}

}
