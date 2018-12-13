package pa.iscde.docGeneration.ext;

import InfoClasses.ConstructorInfo;
import InfoClasses.FieldInfo;
import InfoClasses.MethodInfo;

public interface Filter {
	
	 boolean accept(ConstructorInfo c);
	 
	 boolean accept(MethodInfo c);
	 
	 boolean accept(FieldInfo c);
	 
}
