package pa.iscde.docGeneration;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import org.osgi.framework.ServiceReference;
import pa.iscde.pidesco.docGeneration.services.DocGenServices;
import pt.iscte.pidesco.extensibility.PidescoServices;

import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserListener;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;

public class Activator implements BundleActivator {

	private static Activator instance;
	private JavaEditorServices editorservice;

	
	public static Activator getInstance() {
		return instance;
	}

	public void start(BundleContext context) throws Exception {
		instance = this;

		ServiceReference<PidescoServices> ref = context.getServiceReference(PidescoServices.class);
		PidescoServices pidescoServices = context.getService(ref);

		DocGenServices services = new ServicesImpl();
		context.registerService(DocGenServices.class, services, null);

		ServiceReference<ProjectBrowserServices> refbrowser = context.getServiceReference(ProjectBrowserServices.class);

		ServiceReference<JavaEditorServices> refjavaeditor = context.getServiceReference(JavaEditorServices.class);

		editorservice = (JavaEditorServices) context.getService(refjavaeditor);

		editorservice.addListener(new EditorListenersActions(editorservice));

		ProjectBrowserServices browserservice = (ProjectBrowserServices) context.getService(refbrowser);
		browserservice.addListener(new BrowserListenersAction(services));

	}


	/**
	 * Getter for the JavaEditor's Services
	 * @return
	 */
	public JavaEditorServices getEditorservice() {
		return editorservice;
	}

	public void stop(BundleContext context) throws Exception {
		context = null;
		instance = null;
	}

}
