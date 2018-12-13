package InfoClasses;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class FieldInfo {

	private String name;
	private final String type;
	private final ArrayList<Modifiers> modifiers;
	
	
	public FieldInfo(FieldDeclaration node, ArrayList<Modifiers> modifiers) {
		this.type = node.getType().toString();
		this.modifiers = modifiers;
		
		for (Object o : node.fragments()) {
			VariableDeclarationFragment var = (VariableDeclarationFragment) o;
			name = var.getName().toString();
		}
		
	}


	/**
	 * 
	 * @return 
	 */
	public String[] getFieldInfo() {
	String[] g = new String[] {"","",""};
		
		g[0] += type + " " + name;
		for(Modifiers g1: modifiers) 
			g[1] += g1 + ", ";
		g[1] = g[1].replaceAll(", $", "");
	
		return g;
	}


	public String getName() {
		return name;
	}


	public String getType() {
		return type;
	}


	public ArrayList<Modifiers> getModifiers() {
		return modifiers;
	}
	
	
}
