package pa.iscde.docGeneration;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import InfoClasses.ConstructorInfo;
import InfoClasses.FieldInfo;
import InfoClasses.MethodInfo;
import pa.iscde.docGeneration.ext.Filter;
import pa.iscde.docGeneration.ext.DocGenExtensions;

/**
 * Class used for treating extension points of the Documentation Component
 * 
 * @author Ricardo Silva
 *
 */
public class EvaluateContributionsHandler {

	private static final String EXT_POINT_FILTER = "pa.iscde.docGeneration.ext";
	private IExtensionRegistry registry = Platform.getExtensionRegistry();

	/**
	 * Variable containing all extensions of the component
	 */
	private IConfigurationElement[] config = registry.getConfigurationElementsFor(EXT_POINT_FILTER);

	/**
	 * Class constructor Checks which are String instances are not null and adds
	 * them to the filter if they aren't
	 */
	public EvaluateContributionsHandler() {
		for (IConfigurationElement e : config) {
			if (e.getAttribute("StringName") != null) {
				DocGenView.getInstance().addFilter(e.getAttribute("StringName"));
			}
		}
	}

	/**
	 * Method used for warning extensions of when a doubleclick is performed on the
	 * Documentation View
	 * 
	 */
	public void doubleClick(ArrayList<String> info, char type) {
		try {
			for (IConfigurationElement e : config) {
				Object o = e.createExecutableExtension("DocGenExtensions");
				if (o instanceof DocGenExtensions) {
					((DocGenExtensions) o).doubleClick(info, type);
				}
			}
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	
	/**
	 * Method to filter documentation according to the extension's defined conditions
	 * @param f Object object that will be subject to the Extension filter conditions
	 * @return a boolean that says if the object should be filtered or not
	 */
	public boolean Filter(Object f) {
		try {
			for (IConfigurationElement e : config) {
				Object o = e.createExecutableExtension("Filter");
				if (o instanceof Filter) {
					if (f instanceof MethodInfo) {
						return ((Filter) o).accept((MethodInfo) f);
					}
					if (f instanceof ConstructorInfo) {
						return ((Filter) o).accept((ConstructorInfo) f);
					}
					if (f instanceof FieldInfo) {
						return ((Filter) o).accept((FieldInfo) f);
					}
				}
			}
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return true;
	}

}
