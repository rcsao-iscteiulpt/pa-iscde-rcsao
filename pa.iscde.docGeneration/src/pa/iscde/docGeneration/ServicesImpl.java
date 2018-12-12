package pa.iscde.docGeneration;

import java.io.File;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.ASTVisitor;

import pa.iscde.pidesco.docGeneration.services.DocGenServices;
import pt.iscte.pidesco.javaeditor.service.JavaEditorListener;


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

}	
