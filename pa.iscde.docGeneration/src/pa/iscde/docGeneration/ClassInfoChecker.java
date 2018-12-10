package pa.iscde.docGeneration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;

import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.swt.graphics.Image;

import InfoClasses.ConstructorInfo;
import InfoClasses.FieldInfo;
import InfoClasses.MethodInfo;


public class ClassInfoChecker extends ASTVisitor {

	private final HashMap<String, Object> classbasicinfo = new HashMap<String, Object>();
	private final ArrayList<FieldInfo> classfields = new ArrayList<FieldInfo>();
	private final ArrayList<ConstructorInfo> classconstructors = new ArrayList<ConstructorInfo>();
	private final ArrayList<MethodInfo> classmethods = new ArrayList<MethodInfo>();

	// visits class/interface declaration
	@Override
	public boolean visit(TypeDeclaration node) {
		classbasicinfo.put("Modifiers", getModifiersList(node.getModifiers()));
		classbasicinfo.put("ClassName", node.getName().toString());
		return true;
	}

	
	@Override
	public boolean visit(MethodDeclaration node) {
		if(node.isConstructor()) {
			classconstructors.add(new ConstructorInfo(node, getModifiersList(node.getModifiers())));
		} else {
			classmethods.add(new MethodInfo(node, getModifiersList(node.getModifiers())));
		}
		return true; // false to avoid child VariableDeclarationFragment to be processed again
	}

	// visits attributes
	@Override
	public boolean visit(FieldDeclaration node) {
		classfields.add(new FieldInfo(node, getModifiersList(node.getModifiers())));
		return false; // false to avoid child VariableDeclarationFragment to be processed again
	}

	// visits variable declarations in parameters
	/*
	@Override
	public boolean visit(SingleVariableDeclaration node) {
		String name = node.getName().toString();
		return true;
	}
	*/

	public ArrayList<String> getModifiersList(int modifiers) {
		ArrayList<String> modifierslist = new ArrayList<>();

		if (Modifier.isPublic(modifiers))
			modifierslist.add("Public");
		if (Modifier.isProtected(modifiers))
			modifierslist.add("Protected");
		if (Modifier.isPrivate(modifiers))
			modifierslist.add("Private");
		if (Modifier.isAbstract(modifiers))
			modifierslist.add("abstract");
		if (Modifier.isStatic(modifiers))
			modifierslist.add("Static");
		if (Modifier.isFinal(modifiers))
			modifierslist.add("Final");

		return modifierslist;

	}
	
	public HashMap<String, Object> getClassbasicinfo() {
		return classbasicinfo;
	}

	public ArrayList<FieldInfo> getClassfields() {
		return classfields;
	}

	public ArrayList<ConstructorInfo> getClassconstructors() {
		return classconstructors;
	}

	public ArrayList<MethodInfo> getClassmethods() {
		return classmethods;
	}



	

	
	
	
}
