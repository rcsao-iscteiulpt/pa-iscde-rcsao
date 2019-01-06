package pa.iscde.docgeneration.test;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import pa.iscde.docGeneration.ext.DocGenServices;

/** 
 * Activator for the Testing Component
 * @author Ricardo Silva
 *
 */
public class Activator implements BundleActivator {

	private BundleContext context;
	private static Activator instance;
	private DocGenServices docServices;


	public void start(BundleContext context) throws Exception {
		instance = this;
		this.context = context;
		
		ServiceReference<DocGenServices> refDoc = context.getServiceReference(DocGenServices.class);
		
		docServices = (DocGenServices) context.getService(refDoc);
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		context = null;
	}
	
	
	public static Activator getInstance() {
		return instance;
	}
	
	public DocGenServices getDocServices() {
		return docServices;
	}

}
