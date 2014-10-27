/*Author: rbreak
 * ID:
 * class: CIS*3700
 * assignment: 2
 */


import java.util.ArrayList;


public class DecisionTreeLearning 
{
	int numOfResults;
	Sample data = new Sample();
	int functionValues;
	
	/**This method creates the Decision tree */
	Node learnDecisionTree( ArrayList<Example> g, ArrayList<Attribute> attrib, Node sMajor) {
		
		if( g.size()==0||attrib.size()==0)
		{
			return majorityValue(g);
			//return a node labeled sMajor;
		}
		
		//int count=0;
		boolean flag=true;
		
//		if all examples in g have class q, return a node labeled q;
		int value=g.get(0).functionValue;
		for(int x=1;x<g.size();x++)
		{
			if(value!=g.get(x).functionValue)
			{
				flag=false;
				break;
			}
		}

	
		if(flag==true)
		{
			Node q = new Node();
			//System.out.println(value);
			q.label =data.content.function.values.get(value);
			return q;
		}

		Attribute a = data.getAttribute(attrib, g);
		Node m = majorityValue(g);
		Node tr = new Node();
		tr.label=a.name;
		//System.out.println(a.numValues);
		for(int x=0; x<a.numValues;x++)
		{
			ArrayList<Example> subg = new ArrayList<Example>();
			for(int y=0; y< g.size();y++)
			{
				if(g.get(y).values.get(a.AttributeNum) == x )
				{
					subg.add(g.get(y));
				}
			}
			
			attrib.remove(a);
			Node subtr = learnDecisionTree(subg, attrib, m);
			
			subtr.parent=tr;
			subtr.fromParent=a.values.get(x);
			tr.children.add(subtr);
		
		}
		

		return tr;
	}
	/**Find the next node with the most of the same function value */
	Node majorityValue(ArrayList<Example> g)
	{
		int a[];
		
		int size=g.size();
				//this.data.content.list.get(this.data.content.list.size()-1).values.size();
		a= new int[size];
		String s = data.content.function.name;
		for(int x=0;x<g.size();x++)
		{
			for(int y=0; y<size; y++)
			{
				if( y == g.get(x).functionValue)
				{
					a[y]++;
				}
			}			
		}
		int max=a[0];
		for(int y=1; y<size; y++)
		{
			if(a[max]<a[y])
			{
				max=y;
			}			
		}
		Node n = new Node();
		n.label=s; 
		return n;
		
	}
	
	/**Main method */
	public static void main(String args[])
	{
		DecisionTreeLearning test = new DecisionTreeLearning();
		test.data.content.readFile(args[0]);
		test.data.readSample(args[1]);
		Node ans= test.learnDecisionTree(test.data.sets,test.data.content.list,test.majorityValue(test.data.sets));
		
		
		System.out.println(ans.label);
		System.out.println(0 + "\n");
		ans.printTree(ans, 0, 0);
		//ans.print();
		if(args.length<2)
		{
			System.out.print("not enough args!");
			System.exit(0);			
		}
		
		
		
	}
}
