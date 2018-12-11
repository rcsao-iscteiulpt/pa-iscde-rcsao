package pa.iscde.pidesco.docGeneration.services;

import java.io.File;
import pa.iscde.docGeneration.ClassInfoChecker;


public interface DemoServices {

	void addListener(DemoListener listener);

	void removeListener(DemoListener listener);

	void openFile(File f, ClassInfoChecker c);

	

	

}
	

