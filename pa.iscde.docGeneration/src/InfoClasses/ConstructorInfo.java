package InfoClasses;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.MethodDeclaration;

public class ConstructorInfo {

	private String name;
	private Object parameters;
	private ArrayList<String> modifiers;
	
	
	public ConstructorInfo(MethodDeclaration node, ArrayList<String> modifiers) {
		this.name = node.getName().toString();
		this.parameters = node.parameters();
		this.modifiers = modifiers;
	}


	public String[] getConstructorInfo() {
		String[] g = new String[] {"",""};
		
		g[0] += name +"(";
		for(Object o: (Iterable)parameters) {
			g[0] += o + ", ";
		}
		g[0] = g[0].replaceAll(", $", "") + ")";
		
		for(String g1: modifiers) 
			g[1] += g1 + ", ";
		g[1] = g[1].replaceAll(", $", "");
	
		return g;
	}
		
	
	
}
