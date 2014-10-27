/*Author: rbreak
 * ID:
 * class: CIS*3700
 * assignment: 2
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Sample 
{
	ArrayList<Example> sets = new ArrayList<Example>();
	Scheme content = new Scheme();
	int exNum=0;
	/**Reads in a sample file */
	void readSample(String args)
	{
		String fileName=args;
		boolean flag=true;
		String line;
		StringTokenizer token; 
		BufferedReader in;
		/**if there is a I/O error catch it */
		try
		{
			in = new BufferedReader(new FileReader(fileName));
			//Reads in the file
			line=in.readLine();
			if(line==null)
			{
				flag= false;
				System.out.println("Error reading the file, Exiting.");
				System.exit(0);
			}
			//tokenize the line for easier parsing
			token= new StringTokenizer(line);
			if(token.countTokens()!= content.list.size())
			{
				System.out.println("Error: Attribute size from Scheme and Trainer file do not match. Exiting.");
				System.exit(0);
			}
			int checkPos=0;
			ArrayList<Integer> location = new ArrayList<Integer>();
			while(token.hasMoreTokens())
			{
				String name=token.nextToken();
				int num= content.findValue(name);
				if(num==-1)
				{
					System.out.println("Error: Attribute name in Trainer file does not match existing attributes in Scheme. Exiting.");
					System.exit(0);
				}
				location.add(new Integer(num));	
				if(num!=checkPos)
				{
					System.out.println("Error: Attributes in scheme and trainer files are not alligned. Exiting.");
					System.exit(0); 
				}
				checkPos++;
			}
			
			/*Reads in all the attribute values*/
			while(flag==true)
			{
				line=in.readLine();
				if(line==null)
				{
					flag=false;
					break;
				}
				//System.out.println(line);
				token=new StringTokenizer(line);
				if(token.countTokens()!= content.list.size())
				{
					System.out.println("Error: Attribute size from Scheme and Trainer file do not match. Exiting.");
					System.exit(0);
				}
				int x=0;
				Example e = new Example();
				for(int y=0; y<token.countTokens();y++)
				{
					e.values.add(new Integer(0));
				}
				int lastSize=0;
				// parses and stores all the tokens
				while(token.hasMoreTokens())
				{
					
					String name=token.nextToken();
					
					for(int y=0;y<content.list.get(x).values.size();y++)
					{
						if(content.list.get(x).values.get(y).equals(name))
						{
							e.values.set(location.get(x), new Integer(y));
							
							break;
						}
					}
					if(lastSize!=e.values.size())
					{
						lastSize++;						
					}
					else
					{
						System.out.println("Error: An attribute does not match scheme from trainer set. Exiting");
						System.exit(0);
					}
					
					x++;
				}
				/*Adds e to the array list. removes the last element in e(it is the function value)*/
				e.exampleNum=exNum;
				exNum++;
				e.setValue();
				e.functionValue=e.values.get(e.values.size()-1);
				e.values.remove(e.values.size()-1);
				sets.add(e);
			}			
			in.close();
			/*this is removed now because of error checking above that checks size*/
			content.list.remove(content.list.size()-1);
		}
		catch(Exception e)
		{
			System.out.println("Error: File I/O failed or contents are not correctly parsed. Exiting");
			System.exit(0);			
		}
	
	}
	

	
/** calculates the remainder of the subgroup*/
	double getRemainder(Attribute a,ArrayList<Example> g, int h)
	{
		
		int size = g.size();
		int k=a.numValues;
		double remainder=0.0;
		ArrayList<ArrayList<Example>> subg = new ArrayList<ArrayList<Example>>();
		ArrayList<Integer> subcnt= new ArrayList<Integer>();
		
		for(int x=0; x<k; x++)
		{
			ArrayList<Example> mini =new ArrayList<Example>();
			for(int y=0; y<g.size(); y++)
			{				
				if(g.get(y).values.get(a.AttributeNum)==x)
				{
					mini.add(g.get(y));
				}
			}
			subcnt.add(new Integer(mini.size()));
			subg.add(mini);
		}
		for(int x=0; x<k; x++)
		{
			double pr =(double)( subcnt.get(x).doubleValue()/size);
			;
			double info = getInfo(subg.get(x),h);
			//System.out.println(info);
			remainder+= pr*info;
		}
		
		return remainder;
	}
	
	/**Calculates the information we have learned */
	public double getInfo(ArrayList<Example> g, int h)
	{
		int[] count = new int[h]; 
		double  size=g.size();
		
		
		
		
		for(int i=0; i<h; i++)
		{
			for(int x=0; x<size; x++)
			{			
				if(g.get(x).functionValue.intValue()==i)
				{
					count[i]++;
					//System.out.println("Count increased to: "+count[i]);
				}
			}
		}
		
		double info = 0.0;
		for(int i=0; i<h; i++)
		{
			//System.out.println("Count is"+count[i]);
			double pr = (double)count[i] / (double)size;
			if(pr!=0)
			{
				info = info - pr * Math.log10(pr) / Math.log10(2);
			}
		}		
		//System.out.println(info+":Whoa");
		return info;
	}
	/**finds the next best attribute to look at*/
	Attribute getAttribute(ArrayList<Attribute> a, ArrayList<Example> g)
	{
		
		double max=-1;
		Attribute bestA=null;
		int h = this.content.function.numValues;
		double info = getInfo(g, h );
		for(int x=0; x< a.size(); x++)
		{
			double remainder = getRemainder(a.get(x),g,h);
			//System.out.println(remainder);
			double gain = info-remainder;
			if(gain>max)
			{
				max=gain;
				bestA=a.get(x);
			}
			//System.out.println("max is :"+max);
		}
		//System.out.println("max is :"+max);
		//System.out.println("BEST IS " +bestA.name);
		return bestA;
	}	
}
