package pa.iscde.docGeneration;

import java.io.File;

import pa.iscde.pidesco.docGeneration.services.DemoListener;
import pa.iscde.pidesco.docGeneration.services.DemoServices;
import pt.iscte.pidesco.javaeditor.service.JavaEditorServices;
import pt.iscte.pidesco.projectbrowser.model.ClassElement;
import pt.iscte.pidesco.projectbrowser.model.SourceElement;

public class ListenersActions extends DemoListener.Adapter {

	//private DemoServices services;
	private JavaEditorServices s;

	public ListenersActions(JavaEditorServices editorservice) {
		this.s= editorservice;
	}

	@Override
	public void doubleClick(SourceElement element) {
		System.out.println("_DoubleClick");
	}

}

