package pa.iscde.pidesco.docGeneration.services;

import pt.iscte.pidesco.projectbrowser.model.SourceElement;


public interface DemoListener {

	
	void doubleClick(SourceElement element);
	
	public class Adapter implements DemoListener {

		/**
		 * Does nothing.
		 */
		@Override
		public void doubleClick(SourceElement element) {
			
		}

	
	}	
	
}	



