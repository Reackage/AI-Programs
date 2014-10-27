/*Author: rbreak
 * ID:
 * class: CIS*3700
 * assignment: 2
 */


import java.util.ArrayList;


public class Node 
{
	Node parent=null;
	String label=null;
	String fromParent=null;
	ArrayList<Node> children= new ArrayList<Node>(); 
	/*Prints the tree*/
	public void printTree(Node n, int num, int num2)
	{
		if(n.children != null)
		{
			for(int x = 0; x < n.children.size(); x ++)
			{
				System.out.print(num2 + ", " + n.children.get(x).fromParent + ", " + n.children.get(x).label + ", " + x + "\t\t");
			}
			System.out.println("\n\n");
			for(int x = 0; x < n.children.size(); x++)
			{
				printTree(n.children.get(x), num2, x);
			}
		}
	}

}
