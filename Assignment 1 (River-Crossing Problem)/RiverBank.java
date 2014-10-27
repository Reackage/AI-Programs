/* Author: Ricky Break
 * ID: 
 * Class: CIS*3700
 * Instructor: Yang Xiang 
 * Date: 09/02/2012
 */

/**The RiverBank Class contains all the information on the state of the current node*/
public class RiverBank extends ObjectPlus {
	/*Should have used BOOLEANS*/
	public int sheep=0;
	public int wolf=0;
	public int cabbage=0;
	public int farmer=0;
	//Zero means on the one side, 1 means the other
	/**Constructor defaulting the Farmer, sheep, wolf and cabbage to the one side */
	public RiverBank()
	{
		this.sheep=0;
		this.wolf=0;
		this.cabbage=0;
		this.farmer=0;
		
	}
	/**Constructor moving the Farmer, sheep, wolf and cabbage to specified position */
	public RiverBank(int f, int w,int c, int s)
	{
		this.farmer=f;
		this.cabbage=c;
		this.sheep=s;
		this.wolf=w;
		
	}
	/**Returned the opposite location for sheep */
	public int inverseSheep() {
		if(sheep==0){
			return 1;
		}
		return 0;
	}
	/**Returned the opposite location for wolf */
	public int inverseWolf() {
		if(wolf==0){
			return 1;
		}
		return 0;
	}
	/**Returned the opposite location for cabbage */
	public int inverseCabbage() {
		if(cabbage==0){
			return 1;
		}
		return 0;
	}
	/**Returned the opposite location for farmer */
	public int inverseFarmer() {
		if(farmer==0){
			return 1;
		}
		return 0;
	}
	/**Prints out the state of the current node */
	@Override
	void show() {
		System.out.print("< ");
		if(farmer==0)
		{
			System.out.print("f ");
			
		}
		if(wolf==0){
			System.out.print("w ");
		}
		
		if(sheep==0){
			System.out.print("s ");
		}
		if(cabbage==0){
			System.out.print("c ");
		}
		
		System.out.print("||");
		if(farmer==1){
			System.out.print(" f");
		}
		if(wolf==1){
			System.out.print(" w");
		}
		if(sheep==1){
			System.out.print(" s");
		}
		if(cabbage==1){
			System.out.print(" c");
		}
		
		System.out.println(">");
		// TODO Auto-generated method stub
		
	}
	
	/**Checks if the current move is legal */
	boolean legal(ObjectPlus State)
	{
		if(State instanceof RiverBank)
		{
			
			RiverBank check=(RiverBank)State;
			if(check.sheep==check.cabbage)
			{
				if(check.farmer!=check.sheep)
				{
					return false;
				}
			}
			if(check.sheep==check.wolf)
			{
				if(check.farmer!=check.sheep)
				{
					return false;
				}
			}
			return true;
		}
		return false;
		
	}
	/**Check if this move is indeed a move that will not harm the sheep and 
	 * the cabbage, but mostly the sheep. THINK OF THE POOR SHEEP*/
	boolean checkMove(int f, int w, int c, int s)
	{
		if(f==this.farmer){
			return false;
		}
		else{
			if((c!=this.cabbage && w!=this.wolf)){
				return false;
			}
			else if(c!=this.cabbage&&s!=this.sheep){
					return false;
			}
			else if(s!=this.sheep&&w!=this.wolf){
				return false;
			}
			else{
				return true;
			}
		}
	}
	/**moves the Farmer, wolf, cabbage, and the sheep. Seriously, think of the SHEEP! */
	void transfer(int f, int w, int c, int s)
	{
		this.farmer=f;
		this.wolf=w;
		this.cabbage=c;
		this.sheep=s;
	}
}
