package pa.iscde.docgeneration.test;

import java.util.ArrayList;

import pa.iscde.docGeneration.ext.DocGenExtensions;

public class TestActions implements DocGenExtensions {

	@Override
	public void doubleClick(ArrayList<String> info, char type) {
//		for(String g: info) {
//			System.out.println("g == " +g);
//		}	
		
		TestView.getInstance().inputText("Type " +type + ": ");
		
		TestView.getInstance().inputText(info.toString());	
		TestView.getInstance().inputText("\n");
	}

	
	
}
