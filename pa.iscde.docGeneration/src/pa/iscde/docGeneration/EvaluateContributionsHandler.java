package pa.iscde.docGeneration;

import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import pa.iscde.docGeneration.ext.DocGenExtensions;
import pa.iscde.javaTasks.ext.Task;
import pa.iscde.javaTasks.ext.TasksAction;

public class EvaluateContributionsHandler {

	private static final String EXT_POINT_FILTER = "pa.iscde.docGeneration.ext";
	private IExtensionRegistry registry = Platform.getExtensionRegistry();
	private IConfigurationElement[] config = registry.getConfigurationElementsFor(EXT_POINT_FILTER);

	public EvaluateContributionsHandler() {
		for (IConfigurationElement e : config) {
			DocGenView.getInstance().addFilter(e.getAttribute("StringName"));
		}
	}
	
	
	public void doubleClick(ArrayList<String> info) {
		for (IConfigurationElement e : config) {
			if (e instanceof DocGenExtensions) {
				((DocGenExtensions) e).doubleClick(info);
			}
		}
	}
}


