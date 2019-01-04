package InfoClasses;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

/**
 * Class that contains information about given Field
 * @author Ricardo Silva
 */
public class FieldInfo {

	private String name;
	private final String type;
	private final ArrayList<Modifiers> modifiers;
	
	
	/**
	 * Class constructor 
	 * @param node FieldDeclaration, it's the received Field.
	 * @param modifiers ArrayList(Modifiers), list of Enum instances which correspond to the field modifiers
	 */
	public FieldInfo(FieldDeclaration node, ArrayList<Modifiers> modifiers) {
		this.type = node.getType().toString();
		this.modifiers = modifiers;
		
		for (Object o : node.fragments()) {
			VariableDeclarationFragment var = (VariableDeclarationFragment) o;
			name = var.getName().toString();
		}
		
	}


	/**
	 * Method which organizes each type of information in a String vector 
	 * @return a string vector containing the field info following this order: Type & Name , Modifiers <br>
	 * e.g string vector might contain ["String g", "Private, Final"]
	 */
	public String[] getFieldInfo() {
	String[] g = new String[] {"","",""};
		
		g[0] += type + " " + name;
		for(Modifiers g1: modifiers) 
			g[1] += g1 + ", ";
		g[1] = g[1].replaceAll(", $", "");
	
		return g;
	}
	
	
	/**
	 * Getter for the field name
	 * @return String name of the field
	 */
	public String getName() {
		return name;
	}

	
	/**
	 * Getter for the field type
	 * @return String type of the field
	 */
	public String getType() {
		return type;
	}

	/**
	 * Getter for the field modifiers
	 * @return list of field modifiers
	 */
	public ArrayList<Modifiers> getModifiers() {
		return modifiers;
	}
	
	
}
