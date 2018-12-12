package InfoClasses;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class FieldInfo {

	private String name;
	private String type;
	private ArrayList<String> modifiers;
	
	
	public FieldInfo(FieldDeclaration node, ArrayList<String> modifiers) {
		this.type = node.getType().toString();
		this.modifiers = modifiers;
		
		for (Object o : node.fragments()) {
			VariableDeclarationFragment var = (VariableDeclarationFragment) o;
			this.name = var.getName().toString();
		}
		
	}


	/**
	 * 
	 * @return 
	 */
	public String[] getFieldInfo() {
	String[] g = new String[] {"","",""};
		
		g[0] += type + " " + name;
		for(String g1: modifiers) 
			g[1] += g1 + ", ";
		g[1] = g[1].replaceAll(", $", "");
	
		return g;
	}
	
	
}
