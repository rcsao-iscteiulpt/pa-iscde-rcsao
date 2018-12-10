package pa.iscde.docGeneration;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import org.osgi.framework.ServiceReference;
import pa.iscde.pidesco.docGeneration.services.DemoServices;
import pt.iscte.pidesco.extensibility.PidescoServices;

import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserListener;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;

public class Activator implements BundleActivator {

	private static Activator instance;

	private BundleContext context;
	private JavaEditorServices editorservice;
	private DemoServices services;

	public static Activator getInstance() {
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		instance = this;
		this.context = context;

		ServiceReference<PidescoServices> ref = context.getServiceReference(PidescoServices.class);
		PidescoServices pidescoServices = context.getService(ref);

		services = new ServicesImpl();
		context.registerService(DemoServices.class, services, null);

		ServiceReference<ProjectBrowserServices> refbrowser = context.getServiceReference(ProjectBrowserServices.class);

		ServiceReference<JavaEditorServices> refjavaeditor = context.getServiceReference(JavaEditorServices.class);

		editorservice = (JavaEditorServices) context.getService(refjavaeditor);

		editorservice.addListener(new EditorListenersActions(editorservice));

		ProjectBrowserServices browserservice = (ProjectBrowserServices) context.getService(refbrowser);
		browserservice.addListener(new BrowserListenersAction(services, editorservice));

	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */

	public JavaEditorServices getEditorservice() {
		return editorservice;
	}

	public void stop(BundleContext context) throws Exception {
		context = null;
		instance = null;
	}

}
