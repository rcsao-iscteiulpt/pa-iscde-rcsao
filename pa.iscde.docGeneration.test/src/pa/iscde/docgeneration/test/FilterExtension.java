package pa.iscde.docgeneration.test;

import InfoClasses.ConstructorInfo;
import InfoClasses.FieldInfo;
import InfoClasses.MethodInfo;
import pa.iscde.docGeneration.ext.Filter;

/**
 * Class testing the extension filter from Document Generation Component <br>
 * In this test the predefined condition is to only allow constructors to be filtered
 * 
 * @author Ricardo Silva
 *
 */
public class FilterExtension implements Filter {

	@Override
	public boolean accept(ConstructorInfo c) {
		//Condition 1
		return true;
	}

	@Override
	public boolean accept(MethodInfo c) {
		//Condition 2
		return false;
	}

	@Override
	public boolean accept(FieldInfo c) {
		//Condition 3
		return false;
	}

}
