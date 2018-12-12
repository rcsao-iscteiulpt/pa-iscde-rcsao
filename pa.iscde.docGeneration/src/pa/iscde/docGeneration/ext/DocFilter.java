package pa.iscde.docGeneration.ext;

import InfoClasses.ConstructorInfo;

public interface DocFilter {
	
	 boolean accept(ConstructorInfo c);
	 
}
