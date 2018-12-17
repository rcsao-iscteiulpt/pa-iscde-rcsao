package pa.iscde.docGeneration;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import org.osgi.framework.ServiceReference;

import pa.iscde.docGeneration.ext.DocGenServices;
import pa.iscde.javaTasks.ext.TasksServices;
import pa.iscde.search.services.SearchService;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;

public class Activator implements BundleActivator {

	private BundleContext context;
	private static Activator instance;
	private JavaEditorServices editorservice;
	private TasksServices taskservice;
	

	
	public static Activator getInstance() {
		return instance;
	}

	public void start(BundleContext context) throws Exception {
		instance = this;
		this.context = context;

		DocGenServices services = new ServicesImpl();
		context.registerService(DocGenServices.class, services, null);

		ServiceReference<ProjectBrowserServices> refBrowser = context.getServiceReference(ProjectBrowserServices.class);
		
		

		ServiceReference<JavaEditorServices> refJavaEditor = context.getServiceReference(JavaEditorServices.class);
		
		
		

		editorservice = (JavaEditorServices) context.getService(refJavaEditor);

		editorservice.addListener(new EditorListenersActions(editorservice));

		ProjectBrowserServices browserService = (ProjectBrowserServices) context.getService(refBrowser);
		browserService.addListener(new BrowserListenersAction(services));
		
		
		

	}


	public TasksServices getTaskservice() {
		return taskservice;
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

	public BundleContext getContext() {
		return context;
	}



}
