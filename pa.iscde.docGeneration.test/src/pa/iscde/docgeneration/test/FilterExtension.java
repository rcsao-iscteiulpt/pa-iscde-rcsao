package pa.iscde.docgeneration.test;

import InfoClasses.ConstructorInfo;
import InfoClasses.FieldInfo;
import InfoClasses.MethodInfo;
import pa.iscde.docGeneration.ext.Filter;

public class FilterExtension implements Filter {

	@Override
	public boolean accept(ConstructorInfo c) {
		System.out.println("accept1");
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean accept(MethodInfo c) {
		System.out.println("accept2");
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean accept(FieldInfo c) {
		System.out.println("accpet3");
		// TODO Auto-generated method stub
		return false;
	}

}
