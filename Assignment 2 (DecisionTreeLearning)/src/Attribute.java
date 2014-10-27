/*Author: rbreak
 * ID:
 * class: CIS*3700
 * assignment: 2
 */

import java.util.ArrayList;


/**This class records all the information on an attribute */
public class Attribute 
{
	String name;
	ArrayList<String> values=new ArrayList<String>();
	int numValues=0;
	int AttributeNum=0;
	/** finds the index number of the name in the values field*/
	int findValue(String name)
	{
		for(int x=0; x<numValues; x++)
		{
			if(name.equals(values.get(x)))
			{
				return x;
			}
		}
		return -1;
	}
}
