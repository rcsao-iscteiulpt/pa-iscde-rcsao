package pa.iscde.docGeneration.ext;

import InfoClasses.ConstructorInfo;
import InfoClasses.FieldInfo;
import InfoClasses.MethodInfo;

/**
 * Interface used for figuring out which objects should be filtered and be displayed in the View or not
 * @author Ricardo Silva
 *
 */
public interface Filter {

	
	/**
	 * This method sees if the all the current filters are present in any of the Constructors's info <br>
	 * If they are then the object will be filtered, the opposite happens if they don't
	 */
	boolean accept(ConstructorInfo c);

	/**
	 * This method sees if the all the current filters are present in any of the
	 * Method's info <br>
	 * If they are then the object will be filtered, the opposite happens if they
	 * don't
	 */
	boolean accept(MethodInfo c);

	/**
	 * This method sees if the all the current filters are present in any of the
	 * Field's info <br>
	 * If they are then the object will be filtered, the opposite happens if they
	 * don't
	 */
	boolean accept(FieldInfo c);

}
