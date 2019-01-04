package InfoClasses;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.MethodDeclaration;

/**
 * Class that contains information about given Method
 * @author Ricardo Silva
 */
public class MethodInfo {

	private String name;
	private final String returntype;
	private final Object parameters;
	private final ArrayList<Modifiers> modifiers;
	
	/**
	 * Class constructor 
	 * @param node MethodDeclaration, it's the received method.
	 * @param modifiers ArrayList(Modifiers), list of Enum instances which correspond to the method modifiers
	 */
	public MethodInfo(MethodDeclaration node, ArrayList<Modifiers> modifiers) {
		this.name = node.getName().toString();
		this.returntype = node.getReturnType2().toString();
		this.parameters = node.parameters();
		this.modifiers = modifiers;
	}
	
	
	/**
	 * Method which organizes each type of information in a String vector 
	 * @return a string vector containing the method info following this order: Name , Modifiers, ReturnType <br>
	 * e.g string vector might contain ["Methodname(String g)", "Private, Final", "Boolean"]
	 */
	public String[] getMethodInfo() {
		String[] g = new String[] {"","",""};
		
		g[0] += name +"(";
		for(Object o: (Iterable<?>)parameters) {
			g[0] += o + ", ";
		}
		g[0] = g[0].replaceAll(", $", "") + ")";
		for(Modifiers g1: modifiers)
			g[1] += g1 + ", ";
		g[1] = g[1].replaceAll(", $", "");
		
		g[2] += returntype;
		
		
		return g;
		
	}

	
	/**
	 * Getter for the method name
	 * @return String name of the method
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for the ReturnType of the method
	 * @return String ReturnType of the method
	 */
	public String getReturntype() {
		return returntype;
	}

	/**
	 * Getter for the method parameters
	 * @return Object parameters of the method
	 */
	public Object getParameters() {
		return parameters;
	}

	/**
	 * Getter for the method modifiers
	 * @return list of method modifiers
	 */
	public ArrayList<Modifiers> getModifiers() {
		return modifiers;
	}	
	
}
