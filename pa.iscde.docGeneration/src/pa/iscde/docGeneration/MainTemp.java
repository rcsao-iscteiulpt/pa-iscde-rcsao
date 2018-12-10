package pa.iscde.docGeneration;

import java.io.File;
import java.io.IOException;

public class MainTemp {

	public static void main(String[] args) {
		
		File file = new File("TestWorkSpace/src/pack1/class1.java");
		File file2 = new File("TestWorkSpace/src/pack1.pack1/class11.java");
		System.out.println(file.getParent());
		System.out.println(file2.isDirectory());
		
		Class<?> clazz;
		try {
			String[] l = file.getName().split("\\.");
			String classname = file.getParentFile().getName() +"." + l[0];
			clazz = Class.forName("pack1.class1");
			//ClassStats gen = new ClassStats(file);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		

	}

}
