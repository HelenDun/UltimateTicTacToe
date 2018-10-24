
public class TileBoard {

	private Tile[][] board;
	private int size;
	private int openSpaces;
	private int victor;
	// 1 is player 1
	// 0 is tie
	// 2 is player 2
	//-1 is not finished
	public TileBoard(){
		size = 3;
		board = new Tile[size][size];
		for (int i=0; i < size; i++)
			for (int j=0; j < size; j++)
				board[i][j] = new Tile();
		openSpaces = size*size;
		victor = -1;
	}
	
	public TileBoard(int s){
		size = s;
		board = new Tile[size][size];
		for (int i=0; i < size; i++)
			for (int j=0; j < size; j++)
				board[i][j] = new Tile();
		openSpaces = size*size;
		victor = -1;
	}
	
	public boolean markTile(int row, int col, boolean player){
		if (board[row][col].markTile(player) && victor < 0){
			openSpaces--;
			checkBoard();
			return true;
		}
		return false;
	}
		
	public void checkBoard(){
		for (int pl = 1; pl < 3; pl++){
			//horizontal
			for (int row = 0; row < board.length; row++){
				int counter = 0;
				for (int col = 0; col < board[row].length; col++)
					if(board[row][col].getMark() == pl)
						counter++;
				if(counter == size){
					victor = pl;
					return;
				}
			}
			//vertical
			for (int col = 0; col < board[0].length; col++){
				int counter = 0;
				for (int row = 0; row < board.length; row++)
					if(board[row][col].getMark() == pl)
						counter++;
				if(counter == size){
					victor = pl;
					return;
				}
			}
			//diagonal from top left to bottom right
			{
				int counter = 0;
				for (int row = 0; row < board.length; row++){
					if(board[row][row].getMark() == pl)
							counter++;
				}
				if(counter == size){
					victor = pl;
					return;
				}
			}
			//diagonal from bottom left to top right
			{
				int counter = 0;
				for (int row = 0; row < board.length; row++){
					if(board[row][size-(row+1)].getMark() == pl)
							counter++;
				}
				if(counter == size){
					victor = pl;
					return;
				}
			}
		}
		if (openSpaces == 0){
			victor = 0;
			return;
		}
	}

	public int getVictor(){
		return victor;
	}
}