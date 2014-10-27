/*Author: rbreak
 * ID:
 * class: CIS*3700
 * assignment: 2
 */
import java.util.ArrayList;

/**This class contains the values of an example. Each example's order is based off the Scheme */
public class Example 
{
	int exampleNum;
	ArrayList<Integer> values =  new ArrayList<Integer>();
	Integer functionValue;
	// this method was created to help read in the file easier
	void setValue()
	{
		if(values.size()!=0)
		{
			functionValue= new Integer(values.get(values.size()-1));
		}
		
	}
	
}
