/* Author: Ricky Break
 * ID: 
 * Class: CIS*3700
 * Instructor: Yang Xiang 
 * Date: 09/02/2012
 */



import java.util.LinkedList;

/**RiverProblem contains the information on */ 
public class RiverProblem extends Problem{
	RiverBank test;
	
	/*Sets up the parent class */
	public RiverProblem()
	{
		this.test = new RiverBank();
		super.setInitialState(this.test);
		
		super.setGoalState(new RiverBank(1,1,1,1));
	}
	/**Creates the child nodes and puts them into a linked list */
	@Override
	LinkedList<RiverBank> getSuccessor(ObjectPlus arg0) {
		// TODO Auto-generated method stub
		LinkedList<RiverBank> ll =new LinkedList<RiverBank>();
		if(arg0 instanceof RiverBank)
		{
			/**Bounded the question: The farmer HAS to move, leaving 4 alternative moves, as you can only move either the farmer, or the farmer and the wolf, cabbage or sheep */
			RiverBank check=(RiverBank)arg0;
			/*Move farmer and wolf*/
			if(check.checkMove(check.inverseFarmer(), check.inverseWolf(), check.cabbage, check.sheep))
			{
					
				RiverBank node= new RiverBank(check.inverseFarmer(), check.inverseWolf(), check.cabbage, check.sheep);
				if(check.legal(node))
				{
					ll.add(node);
				}
			}
			/*move Farmer and Cabbage*/
			if(check.checkMove(check.inverseFarmer(), check.wolf, check.inverseCabbage(), check.sheep))
			{
				RiverBank node= new RiverBank(check.inverseFarmer(), check.wolf, check.inverseCabbage(), check.sheep);
				if(check.legal(node))
				{
					ll.add(node);
				}
			}
			/*move Farmer and Sheep*/
			if(check.checkMove(check.inverseFarmer(), check.wolf, check.cabbage, check.inverseSheep()))
			{
				RiverBank node= new RiverBank(check.inverseFarmer(), check.wolf, check.cabbage, check.inverseSheep());
				if(check.legal(node))
				{
					ll.add(node);
				}
			}
			/*move just the farmer*/
			if(check.checkMove(check.inverseFarmer(), check.wolf, check.cabbage, check.sheep))
			{
				RiverBank node= new RiverBank(check.inverseFarmer(), check.wolf, check.cabbage,check.sheep);
				if(check.legal(node))
				{
					ll.add(node);
				}
			}
				
			return ll;
		}
		return null;
	}

	@Override
	boolean isGoalState(ObjectPlus arg0) {
		/*Check if it is the correct object*/
		if(arg0 instanceof RiverBank)
		{
			RiverBank check=(RiverBank)arg0;
			/*If everyone is on the other side*/
			if(check.cabbage==1 && check.farmer== 1 && check.wolf ==1&&check.sheep==1 ){
				return true;
			}
		}
		return false;	
	}	

}
