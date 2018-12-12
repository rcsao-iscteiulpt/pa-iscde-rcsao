package pa.iscde.docGeneration.ext;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

public class EvaluateContributionsHandler {

	private static final String EXT_POINT_FILTER = "pa.iscde.docGeneration.ext";
	private IExtensionRegistry registry = Platform.getExtensionRegistry();
	private IConfigurationElement[] config = registry.getConfigurationElementsFor(EXT_POINT_FILTER);

	public EvaluateContributionsHandler() {
		System.out.println(config.length);
		for (IConfigurationElement e : config) {
			System.out.println(e.getAttribute("Faggot"));

		}
	}

//    
//    private void executeExtension(final Object o) {
//        ISafeRunnable runnable = new ISafeRunnable() {
//            @Override
//            public void handleException(Throwable e) {
//                System.out.println("Exception in client");
//            }
//
////            @Override
//            public void run() throws Exception {
////                ((IGreeter) o).greet();
//            }
//        };
//        SafeRunner.run(runnable);
}
