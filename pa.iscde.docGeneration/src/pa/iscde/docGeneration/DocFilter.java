package pa.iscde.docGeneration;

import java.util.HashSet;
import java.util.Set;

import InfoClasses.ConstructorInfo;
import InfoClasses.FieldInfo;
import InfoClasses.MethodInfo;
import InfoClasses.Modifiers;
import pa.iscde.docGeneration.ext.Filter;

public class DocFilter implements Filter {

	private Set<String> filterwords = new HashSet<String>();;

	public DocFilter(Set<String> set) {
		this.filterwords = set;
	}

	@Override
	public boolean accept(ConstructorInfo c) {
		for (String s : filterwords) {
			for (Modifiers mod : c.getModifiers()) {
				if (mod.toString().equalsIgnoreCase(s))
					return true;
			}	
			if(c.getName().equalsIgnoreCase(s)) {
				return true;			
			}
		}
		return false;
	}

	@Override
	public boolean accept(MethodInfo c) {
		
		for (String s : filterwords) {
			for (Modifiers mod : c.getModifiers()) {
				if (mod.toString().equalsIgnoreCase(s))
					return true;
			}	
			if(c.getReturntype().equalsIgnoreCase(s) || c.getName().equalsIgnoreCase(s)) {
				return true;			
			}
		}
		return false;
	}

	@Override
	public boolean accept(FieldInfo c) {
		for (String s : filterwords) {	
			for (Modifiers mod : c.getModifiers()) {
				if (mod.toString().equalsIgnoreCase(s))
					return true;
			}	
			if(c.getType().equalsIgnoreCase(s) || c.getName().equalsIgnoreCase(s)) {
				return true;			
			}
		}
		return false;
	}
	
	
}
