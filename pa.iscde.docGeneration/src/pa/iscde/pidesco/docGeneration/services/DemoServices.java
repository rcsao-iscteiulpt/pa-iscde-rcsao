package pa.iscde.pidesco.docGeneration.services;

import java.io.File;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.ASTVisitor;

import pa.iscde.docGeneration.ClassInfoChecker;
import pt.iscte.pidesco.javaeditor.service.AnnotationType;
import pt.iscte.pidesco.javaeditor.service.JavaEditorListener;

public interface DemoServices {

	void addListener(DemoListener listener);

	void removeListener(DemoListener listener);

	void openFile(File f, ClassInfoChecker c);

	

	

}
	

