package pa.iscde.docGeneration;

import java.io.File;

import org.eclipse.core.runtime.Assert;
import InfoClasses.Modifiers;
import pa.iscde.pidesco.docGeneration.services.DocGenServices;


public class ServicesImpl implements DocGenServices {

	@Override
	public void openFile(File file) {
		Assert.isNotNull(file, "file cannot be null");
		Assert.isTrue(file.exists(), "file does not exist");
		Assert.isTrue(!file.isDirectory(), "file is a directory");
		DocGenView.getInstance().openfile(file);
	}

	

	@Override
	public File getopenedFile() {
		return null;
		// TODO Auto-generated method stub
		
	}



	@Override
	public void filterModifier(String filterword) {
		// TODO Auto-generated method stub
		
	}

}	
