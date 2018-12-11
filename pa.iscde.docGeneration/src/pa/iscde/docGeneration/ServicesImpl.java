package pa.iscde.docGeneration;

import java.io.File;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.ASTVisitor;

import pa.iscde.pidesco.docGeneration.services.DemoListener;
import pa.iscde.pidesco.docGeneration.services.DemoServices;
import pt.iscte.pidesco.javaeditor.service.JavaEditorListener;


public class ServicesImpl implements DemoServices {

	
	public void addListener(DemoListener listener) {
		// TODO Auto-generated method stub
		
	}

	public void removeListener(DemoListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openFile(File file, ClassInfoChecker c) {
		Assert.isNotNull(file, "file cannot be null");
		Assert.isTrue(file.exists(), "file does not exist");
		Assert.isTrue(!file.isDirectory(), "file is a directory");
		DocGenView.getInstance().openfile(c, file);
	
		
	}

}	
