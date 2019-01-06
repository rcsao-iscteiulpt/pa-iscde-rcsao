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
	}



	@Override
	public void filterWord(String filterWord) {
		Assert.isNotNull(filterWord, "String cannot be null");
		DocGenView.getInstance().addFilter(filterWord);
		
	}



	@Override
	public void cleanAllFilters() {
		DocGenView.getInstance().ClearAllfilters();	
	}

}	
