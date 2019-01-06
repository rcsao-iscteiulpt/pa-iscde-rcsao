package pa.iscde.docGeneration;

import java.util.HashSet;
import java.util.Set;

import InfoClasses.ConstructorInfo;
import InfoClasses.FieldInfo;
import InfoClasses.MethodInfo;
import InfoClasses.Modifiers;
import pa.iscde.docGeneration.ext.Filter;

/**
 * Class implementing the Filter interface
 * @author Ricardo Silva
 *
 */
public class DocFilter implements Filter {

	private Set<String> filterwords = new HashSet<String>();;

	public DocFilter(Set<String> set) {
		this.filterwords = set;
	}

	
	
	@Override
	public boolean accept(ConstructorInfo c) {
		int matches = 0;
		for (String s : filterwords) {
			for (Modifiers mod : c.getModifiers()) {
				if (mod.toString().equalsIgnoreCase(s))
					matches++;
			}	
			if(c.getName().equalsIgnoreCase(s)) {
				matches++;			
			}
			
			for(Object o: (Iterable<?>) c.getParameters()) {
				String[] temp = o.toString().split(" ");
				if(temp[0].equalsIgnoreCase(s) || temp[1].equalsIgnoreCase(s))
					matches++;
			}
		}
		
		if(matches == filterwords.size())
			return true;
		else
			return false;
	}

	
	
	@Override
	public boolean accept(MethodInfo c) {
		int matches = 0;
		for (String s : filterwords) {
			for (Modifiers mod : c.getModifiers()) {
				if (mod.toString().equalsIgnoreCase(s))
					matches++;	
			}	
			if(c.getReturntype().equalsIgnoreCase(s) || c.getName().equalsIgnoreCase(s)) {
				matches++;				
			}
			
			for(Object o: (Iterable<?>) c.getParameters()) {
				String[] temp = o.toString().split(" ");
				if(temp[0].equalsIgnoreCase(s) || temp[1].equalsIgnoreCase(s))
					matches++;
			}
		}
		if(matches == filterwords.size())
			return true;
		else
			return false;
	}

	
	
	@Override
	public boolean accept(FieldInfo c) {
		int matches = 0;
		for (String s : filterwords) {	
			for (Modifiers mod : c.getModifiers()) {
				if (mod.toString().equalsIgnoreCase(s))
					matches++;	
			}	
			if(c.getType().equalsIgnoreCase(s) || c.getName().equalsIgnoreCase(s)) {
				matches++;		
			}
		}
		if(matches == filterwords.size())
			return true;
		else
			return false;
	}
	
	
}
