package pa.iscde.docGeneration;


import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import org.osgi.framework.ServiceReference;

import pa.iscde.docGeneration.ext.DocGenServices;
import pa.iscde.javaTasks.ext.TasksServices;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;

/**
 * Activator for the Documentation Generation Component
 * @author Ricardo Silva
 *
 */
public class Activator implements BundleActivator {

	private BundleContext context;
	private static Activator instance;
	private JavaEditorServices editorService;
	//private TasksServices taskService;
		

	public void start(BundleContext context) throws Exception {
		instance = this;
		this.context = context;

		DocGenServices services = new ServicesImpl();
		context.registerService(DocGenServices.class, services, null);

		ServiceReference<ProjectBrowserServices> refBrowser = context.getServiceReference(ProjectBrowserServices.class);
		ServiceReference<JavaEditorServices> refJavaEditor = context.getServiceReference(JavaEditorServices.class);
		//ServiceReference<TasksServices> refTasks = context.getServiceReference(TasksServices.class);
		
		
		
		editorService = (JavaEditorServices) context.getService(refJavaEditor);
		
		editorService.addListener(new EditorListenersActions(editorService));

		ProjectBrowserServices browserService = (ProjectBrowserServices) context.getService(refBrowser);
		browserService.addListener(new BrowserListenersAction(services));
		
		//taskService = (TasksServices) context.getService(refTasks);
		

	}


//	public TasksServices getTaskservice() {
//		return taskService;
//	}

	/**
	 * Getter for the JavaEditor's Services
	 * @return JavaEditor's Services instance
	 */
	public JavaEditorServices getEditorservice() {
		return editorService;
	}

	public void stop(BundleContext context) throws Exception {
		context = null;
		instance = null;
	}
	
	/**
	 * Getter for the Activator instance
	 * @return Activator instance;
	 */
	public static Activator getInstance() {
		return instance;
	}

	/**
	 * Getter for the Activator's context
	 * @return Activator's context
	 */
	public BundleContext getContext() {
		return context;
	}



}
