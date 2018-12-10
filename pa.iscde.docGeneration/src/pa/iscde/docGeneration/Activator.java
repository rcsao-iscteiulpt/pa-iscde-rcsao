package pa.iscde.docGeneration;


import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.Assert;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import org.osgi.framework.ServiceReference;

import pa.iscde.pidesco.docGeneration.services.DemoListener;
import pa.iscde.pidesco.docGeneration.services.DemoServices;
import pt.iscte.pidesco.extensibility.PidescoServices;


import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserListener;
import pt.iscte.pidesco.projectbrowser.service.ProjectBrowserServices;

public class Activator implements BundleActivator {

	private static Activator instance;

	private BundleContext context;
	private ProjectBrowserListener browserlistener;
	private JavaEditorServices editorservice; 
	private Set<DemoListener> listeners;
	private DemoServices services;
	private PidescoServices pidescoServices;
	//private ISelectionListener selectionListener;

	BundleContext getContext() {
		return context;
	}
	
	public static Activator getInstance() {
		return instance;
	}
	
	public DemoServices getServices() {
		return services;
	}
	
	public void addListener(DemoListener l) {
		Assert.isNotNull(l);
		listeners.add(l);
	}

	public void removeListener(DemoListener l) {
		Assert.isNotNull(l);
		listeners.remove(l);
	}
	
	public PidescoServices getPidescoServices() {
		return pidescoServices;
	}
	
	public Set<DemoListener> getListeners() {
		return listeners;
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
		listeners = new HashSet<DemoListener>();

		ServiceReference<PidescoServices> ref = context.getServiceReference(PidescoServices.class);
		PidescoServices pidescoServices = context.getService(ref);
	
		
		
		services = new ServicesImpl();
		context.registerService(DemoServices.class, services, null);
		
		
		ServiceReference<ProjectBrowserServices> serviceReference = context
				.getServiceReference(ProjectBrowserServices.class);
		
		ServiceReference<JavaEditorServices> serviceReference1 = context
				.getServiceReference(JavaEditorServices.class);
		
	    editorservice = (JavaEditorServices) context.getService(serviceReference1);
		
		services.addListener(new ListenersActions(editorservice));
	
			
		if (serviceReference != null) {
			ProjectBrowserServices browserservice = (ProjectBrowserServices) context.getService(serviceReference);
			browserlistener = new BrowserListenersAction(services, editorservice);	
			browserservice.addListener(browserlistener);
		}
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

	
	public void stop(BundleContext bundleContext) throws Exception {
		this.context = null;
	}


}
