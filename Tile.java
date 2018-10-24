
public class Tile {
	
	private int sign;
	// 1 is player 1
	// 0 is nothing
	// 2 is player 2
	public Tile(){
		sign = 0;
	}
	
	public boolean markTile(boolean player){
		if (sign > 0){
			System.out.println("Cannot mark title.");
			return false;
		}
		if (player){
			sign = 1;
		}
		else{
			sign = 2;
		}
		return true;
	}
	public int getMark(){
		return sign;
	}
}