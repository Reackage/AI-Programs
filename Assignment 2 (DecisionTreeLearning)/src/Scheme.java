/*Author: rbreak
 * ID:
 * class: CIS*3700
 * assignment: 2
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Scheme 
{
	ArrayList<Attribute> list = new ArrayList<Attribute>();
	Attribute function = new Attribute();
	/**Attempts to open and read the given file */
	/**finds a particulare attribute based on the attributes name */
	int findValue(String name)
	{
		for(int x=0; x<list.size(); x++)
		{
			if(name.equals(list.get(x).name))
			{
				return x;
			}
		}
		return -1;
	}
	/**reads in the Scheme file */
	public void readFile(String args)
	{
		String fileName=args;
	
		String line;
		
		BufferedReader in;
		/**if there is a I/O error catch it */
		try{
			in = new BufferedReader(new FileReader(fileName));
			//Reads in the file
			line=in.readLine();
			if(line==null)
			{
				
				System.out.println("Error reading the file, Exiting.");
				System.exit(0);
			}
			int numParagraphs=Integer.parseInt(line);	
			/**for each paragraph, you read in new attribute */
			for(int x=0; x<numParagraphs;x++)
			{
				String test = in.readLine();
				Attribute element = new Attribute();
				element.name=in.readLine();	
				test = in.readLine();
				element.numValues= Integer.parseInt(test);
				StringTokenizer token = new StringTokenizer(in.readLine());
				if(token.countTokens() != element.numValues)
				{
					System.out.println("Error: in section: "+element.name+" has an incorrect integer value. Exiting.");
					System.exit(0);					
				}
				while(token.hasMoreTokens())
				{
					element.values.add(token.nextToken());
				}
				element.AttributeNum=x;
				list.add(element);
			}
			
			function=list.get(list.size()-1);
			in.close();
		}
		catch(Exception e)
		{
			System.out.println("Error: File I/O failed or contents are not correctly parsed. Exiting");//e.printStackTrace();
			System.exit(0);			
		}
		
	}
	
}
