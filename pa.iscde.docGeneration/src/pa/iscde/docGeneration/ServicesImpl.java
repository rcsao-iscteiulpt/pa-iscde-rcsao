package pa.iscde.docGeneration;

import java.io.File;

import org.eclipse.core.runtime.Assert;
import pa.iscde.docGeneration.ext.DocGenServices;


public class ServicesImpl implements DocGenServices {

	@Override
	public void openFile(File file) {
		Assert.isNotNull(file, "file cannot be null");
		Assert.isTrue(file.exists(), "file does not exist");
		Assert.isTrue(!file.isDirectory(), "file is a directory");
		DocGenView.getInstance().openfile(file);
	}

	

	@Override
	public File getSelectedFile() {
		return DocGenView.getInstance().getSelectedFile();
		// TODO Auto-generated method stub
		
	}



	@Override
	public void filterModifier(String filterword) {
		// TODO Auto-generated method stub
		
	}

}	
