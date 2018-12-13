package pa.iscde.docGeneration;

import InfoClasses.ConstructorInfo;
import InfoClasses.FieldInfo;
import InfoClasses.MethodInfo;
import InfoClasses.Modifiers;
import pa.iscde.docGeneration.ext.Filter;

public class DocFilter implements Filter {

	private String filterword;

	public DocFilter(String s) {
		this.filterword = s;
	}

	@Override
	public boolean accept(ConstructorInfo c) {
		for(Modifiers mod : c.getModifiers()) {
			if(mod.toString().equalsIgnoreCase(filterword)) 
				return true;
		}
		return false;
	}

	@Override
	public boolean accept(MethodInfo c) {
		for(Modifiers mod : c.getModifiers()) {
			if(mod.toString().equalsIgnoreCase(filterword)) 
				return true;
		}
		return false;
	}

	@Override
	public boolean accept(FieldInfo c) {
		for(Modifiers mod : c.getModifiers()) {
			if(mod.toString().equalsIgnoreCase(filterword)) 
				return true;
		}
	return false;
	}
}
