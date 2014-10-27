/* Author: Ricky Break
 * ID: 
 * Class: CIS*3700
 * Instructor: Yang Xiang 
 * Date: 09/02/2012
 */


import java.util.LinkedList;

/**RiverAgent Class initializes the search, and displays the results*/
public class RiverAgent extends SearchAgent  {
	/** */
	@SuppressWarnings({ "rawtypes" })
	void showSolution() {
		System.out.println("Soloution to River-Crossing Problem:");
		LinkedList copy=super.solution;
		/*Print out each node!*/
		for(int x=0; x<copy.size();x++)
		{
			((Node) copy.get(x)).show();
			
		}
		
	}
	/**MAIN METHOD*/
	public static void main(String[] args) 
	{
		
		RiverAgent river=new RiverAgent();
		river.setProblem(new RiverProblem());
		/*Starts the bfs*/
		river.search();
		//river.showSolution();
		
	}
}
