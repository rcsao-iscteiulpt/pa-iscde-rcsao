package pa.iscde.docGeneration;

import java.io.File;

import pa.iscde.docGeneration.ext.DocGenServices;
import pt.iscte.pidesco.projectbrowser.model.ClassElement;
import pt.iscte.pidesco.projectbrowser.model.SourceElement;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserListener;

/**
 * Class which implements ProjectBrowser's listeners and it's actions
 * @author Ricardo Silva
 *
 */
public class BrowserListenersAction extends ProjectBrowserListener.Adapter {

	private DocGenServices services;

	/**
	 * Class Constructor
	 * @param services reference from Document Generation Component
	 */
	public BrowserListenersAction(DocGenServices services) {
		this.services = services;
	}

	/**
	 * Action performed when a doubleclick happens in ProjectBorwser
	 * @param SourceElement element, it's the clicked object on ProjectBrowser
	 */
	@Override
	public void doubleClick(SourceElement element) {
		if (element instanceof ClassElement) {
			File f = ((ClassElement) element).getFile();
			services.openFile(f);
		}
	}

}
