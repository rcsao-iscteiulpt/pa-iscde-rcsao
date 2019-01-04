package InfoClasses;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.MethodDeclaration;

/**
 * Class that contains information about given Constructor
 * @author Ricardo Silva
 */
public class ConstructorInfo {

	private final String name;
	private final Object parameters;
	private final ArrayList<Modifiers> modifiers;
	
	
	/**
	 * Class constructor 
	 * @param node MethodDeclaration, it's the received constructor.
	 * @param modifiers ArrayList(Modifiers), list of Enum instances which correspond to the constructor modifiers
	 */
	public ConstructorInfo(MethodDeclaration node, ArrayList<Modifiers> modifiers) {
		this.name = node.getName().toString();
		this.parameters = node.parameters();
		this.modifiers = modifiers;
	}


	
	/**
	 * Method which organizes each type of information in a String vector 
	 * @return a string vector containing the constructor info following this order: Name , Modifiers <br>
	 * e.g string vector might contain ["Classname(String g)", "Private, Final"]
	 */
	public String[] getConstructorInfo() {
		String[] g = new String[] {"",""};
		
		g[0] += name +"(";
		for(Object o: (Iterable<?>)parameters) {
			g[0] += o + ", ";
		}
		g[0] = g[0].replaceAll(", $", "") + ")";
		
		for(Modifiers g1: modifiers) 
			g[1] += g1 + ", ";
		g[1] = g[1].replaceAll(", $", "");
	
		return g;
	}
		
	
	/**
	 * Getter for the constructor name
	 * @return String name of the constructor
	 */
	public String getName() {
		return name;
	}



	/**
	 * Getter for the constructor parameters
	 * @return Object parameters of the constructor
	 */
	public Object getParameters() {
		return parameters;
	}



	/**
	 * Getter for the constructor modifiers
	 * @return list of constructors modifiers
	 */
	public ArrayList<Modifiers> getModifiers() {
		return modifiers;
	}

	
}
