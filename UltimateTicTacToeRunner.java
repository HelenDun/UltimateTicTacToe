import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class UltimateTicTacToeRunner {

	static JPanel ultimateBoard;
	static int size;
	static boolean player;
	static TileBoard[][] ticTacToeBoard;
	static int victor;
	// 1 is player 1
	// 0 is tie
	// 2 is player 2
	//-1 is not finished
	static int openSpaces;
	static String player1Name;
	static String player2Name;
	static JButton newGame;
	static JButton changeOptions;
	static JTextField outputText;
	static JPanel main;
	static JFrame frame;
	static JPanel text;
	static Color player1Color;
	static Color player2Color;
	
	public static void main(String[] args){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Ultimate TicTacToe");
		frame.setSize(600, 600);
		
		main = new JPanel();
		main.setLayout(new BorderLayout());
		
		class ButtonListener implements ActionListener{
			
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(newGame)){
					outputText.setText(player1Name + "'s turn");
					newBoard();
					openBoard();
				}
				else if(e.getSource().equals(changeOptions)){
					JFrame options = new JFrame();
					options.setTitle("Options");
					options.setSize(300,500);
					options.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					JTextField outputSize = new JTextField("Size of board: ");
					JTextField outputPlayer1Name = new JTextField("Name of Player 1: ");
					JTextField outputPlayer2Name = new JTextField("Name of Player 2: ");
					outputSize.setEditable(false);
					outputPlayer1Name.setEditable(false);
					outputPlayer2Name.setEditable(false);
					
					JPanel colorPanel1 = new JPanel();
					colorPanel1.setLayout(new GridLayout(4,2));
					
					JTextField playerField1 = new JTextField("Player 1's Color:");
					JTextField outputRed1 = new JTextField("Red:");
					JTextField inputRed1 = new JTextField("" + player1Color.getRed());
					JTextField outputGreen1 = new JTextField("Green:");
					JTextField inputGreen1 = new JTextField("" + player1Color.getGreen());
					JTextField outputBlue1 = new JTextField("Blue:");
					JTextField inputBlue1 = new JTextField("" + player1Color.getBlue());
					outputRed1.setEditable(false);
					outputGreen1.setEditable(false);
					outputBlue1.setEditable(false);
					colorPanel1.add(outputRed1);
					colorPanel1.add(inputRed1);
					colorPanel1.add(outputGreen1);
					colorPanel1.add(inputGreen1);
					colorPanel1.add(outputBlue1);
					colorPanel1.add(inputBlue1);
					
					JPanel colorPanel2 = new JPanel();
					colorPanel2.setLayout(new GridLayout(4,2));
					
					JTextField playerField2 = new JTextField("Player 2's Color:");
					JTextField outputRed2 = new JTextField("Red:");
					JTextField inputRed2 = new JTextField("" + player2Color.getRed());
					JTextField outputGreen2 = new JTextField("Green:");
					JTextField inputGreen2 = new JTextField("" + player2Color.getGreen());
					JTextField outputBlue2 = new JTextField("Blue:");
					JTextField inputBlue2 = new JTextField("" + player2Color.getBlue());
					outputRed2.setEditable(false);
					outputGreen2.setEditable(false);
					outputBlue2.setEditable(false);
					colorPanel2.add(outputRed2);
					colorPanel2.add(inputRed2);
					colorPanel2.add(outputGreen2);
					colorPanel2.add(inputGreen2);
					colorPanel2.add(outputBlue2);
					colorPanel2.add(inputBlue2);
					
					
					JTextField inputSize = new JTextField("" + size);
					JTextField inputPlayer1Name = new JTextField("" + player1Name);
					JTextField inputPlayer2Name = new JTextField("" + player2Name);
					
					JButton apply = new JButton("Apply");
					JButton done = new JButton("Done");
					
					class OptionsListener implements ActionListener{

						public void actionPerformed(ActionEvent e) {
							if(e.getSource().equals(apply)){
								size = Integer.parseInt(inputSize.getText());
								player1Name = inputPlayer1Name.getText();
								player2Name = inputPlayer2Name.getText();
								player1Color = new Color(Integer.parseInt(inputRed1.getText()), 
										Integer.parseInt(inputGreen1.getText()), 
										Integer.parseInt(inputBlue1.getText()));
								player2Color = new Color(Integer.parseInt(inputRed2.getText()), 
										Integer.parseInt(inputGreen2.getText()), 
										Integer.parseInt(inputBlue2.getText()));
							}
							else if(e.getSource().equals(done)){
								options.dispose();
							}
						}
						
					}
					
					apply.addActionListener(new OptionsListener());
					done.addActionListener(new OptionsListener());
					
					JPanel optionsPanel = new JPanel();
					optionsPanel.setLayout(new GridLayout(6,2));
					optionsPanel.add(outputSize);
					optionsPanel.add(inputSize);
					optionsPanel.add(outputPlayer1Name);
					optionsPanel.add(inputPlayer1Name);
					optionsPanel.add(outputPlayer2Name);
					optionsPanel.add(inputPlayer2Name);
					optionsPanel.add(playerField1);
					optionsPanel.add(colorPanel1);
					optionsPanel.add(playerField2);
					optionsPanel.add(colorPanel2);
					
					optionsPanel.add(apply);
					optionsPanel.add(done);
					
					options.add(optionsPanel);
					options.setVisible(true);
				}
			}
					
		}
	outputText = new JTextField("	Start a new game!	");
		outputText.setEditable(false);
		newGame = new JButton("New Game");
		newGame.addActionListener(new ButtonListener());
		changeOptions = new JButton("Change Options");
		changeOptions.addActionListener(new ButtonListener());
		
		text = new JPanel();
		text.setLayout(new FlowLayout());
		text.add(newGame);
		text.add(outputText);
		text.add(changeOptions);
		
		size = 3;
		player1Name = "Player 1";
		player2Name = "Player 2";
		player1Color = Color.green;
		player2Color = Color.blue;
		newBoard();
		
		main.add(text, BorderLayout.NORTH);
		main.add(ultimateBoard, BorderLayout.CENTER);
		frame.add(main);
		frame.setVisible(true);
	}
	
	static void refocusPanels(int row, int col){
		if(ticTacToeBoard[row][col].getVictor() >= 0)
			enableAllPanels();
		else{
			for(int r = 0; r < size; r++)
				for(int c = 0; c < size; c++){
					if (r == row && c == col)
						enablePanel(true, r, c);
					else 
						enablePanel(false, r, c);
				}
		}
	}
	
	static void enablePanel(boolean enable, int row, int col){
		JPanel tempPanel = (JPanel) ultimateBoard.getComponent((row*size)+col);
		//change color
		if(ticTacToeBoard[row][col].getVictor() < 0){
			if(enable){
				if (player)
					tempPanel.setBackground(lighterColor(player1Color));
				else
					tempPanel.setBackground(lighterColor(player2Color));
			}
			else
				tempPanel.setBackground(null);
			//enable buttons + change color of buttons 
			for(int r = 0; r < size; r++)
				for(int c = 0; c < size; c++){
					JButton tempButton = (JButton) tempPanel.getComponent((r*size)+c);
					if (tempButton.getComponentCount() < 2){
						tempButton.setEnabled(enable);
						if(enable){
							if (player)
								tempButton.setBackground(lighterColor(player1Color));
							else
								tempButton.setBackground(lighterColor(player2Color));
						}
						else
							tempButton.setBackground(null);
					}
				}
		}
		else if(ticTacToeBoard[row][col].getVictor() == 1){
			tempPanel.setBackground(lighterColor(player1Color));
			for(int r = 0; r < size; r++)
				for(int c = 0; c < size; c++){
					JButton tempButton = (JButton) tempPanel.getComponent((r*size)+c);
					if (tempButton.getComponentCount() < 2){
						tempButton.setEnabled(false);
						tempButton.setBackground(lighterColor(player1Color));
					}
				}
		}
		else if(ticTacToeBoard[row][col].getVictor() == 2){
			tempPanel.setBackground(lighterColor(player2Color));
			for(int r = 0; r < size; r++)
				for(int c = 0; c < size; c++){
					JButton tempButton = (JButton) tempPanel.getComponent((r*size)+c);
					if (tempButton.getComponentCount() < 2){
						tempButton.setEnabled(false);
						tempButton.setBackground(lighterColor(player2Color));
					}
				}
		}
		return;	
	}
	
	static void enableAllPanels(){
		for (int row = 0; row < size; row++)
			for (int col = 0; col < size; col++)
				if(ticTacToeBoard[row][col].getVictor() < 0)
					enablePanel(true,row,col);
				else
					enablePanel(false,row,col);
	}
	
	static boolean checkBoard(){
		for (int pl = 1; pl < 3; pl++){
			//horizontal
			for (int row = 0; row < ticTacToeBoard.length; row++){
				int counter = 0;
				for (int col = 0; col < ticTacToeBoard[row].length; col++)
					if(ticTacToeBoard[row][col].getVictor() == pl)
						counter++;
				if(counter == size){
					victor = pl;
					return true;
				}
			}
			//vertical
			for (int col = 0; col < ticTacToeBoard[0].length; col++){
				int counter = 0;
				for (int row = 0; row < ticTacToeBoard.length; row++)
					if(ticTacToeBoard[row][col].getVictor() == pl)
						counter++;
				if(counter == size){
					victor = pl;
					return true;
				}
			}
			//diagonal from top left to bottom right
			{
				int counter = 0;
				for (int row = 0; row < ticTacToeBoard.length; row++){
					if(ticTacToeBoard[row][row].getVictor() == pl)
							counter++;
				}
				if(counter == size){
					victor = pl;
					return true;
				}
			}
			//diagonal from bottom left to top right
			{
				int counter = 0;
				for (int row = 0; row < ticTacToeBoard.length; row++){
					if(ticTacToeBoard[row][size-(row+1)].getVictor() == pl)
							counter++;
				}
				if(counter == size){
					victor = pl;
					return true;
				}
			}
		}
		if (openSpaces == 0){
			victor = 0;
			return true;
		}
		return false;
	}
	
	static int getVictor(){
		return victor;
	}
	
	static void closeBoard(){
		for(int row = 0; row < size; row++)
			for(int col = 0; col < size; col++)
				enablePanel(false,row,col);
	}
	
	static void openBoard(){
		for(int row = 0; row < size; row++)
			for(int col = 0; col < size; col++){
				JPanel tempPanel = (JPanel) ultimateBoard.getComponent((row*size)+col);
				for(int r = 0; r < size; r++)
					for(int c = 0; c < size; c++){
						JButton tempButton = (JButton) tempPanel.getComponent((r*size)+c);
						tempButton.setEnabled(true);
						if (tempButton.getComponentCount() > 1)
							tempButton.remove(1);
					}
			}
		ultimateBoard.revalidate();
		ultimateBoard.repaint();
	}
	
	static void newBoard(){
		player = true;
		victor = -1;
		openSpaces = size*size;
		
		class TileListener implements ActionListener{
			private int panelRow;
			private int panelCol;
			private int row;
			private int col;
			
			public TileListener(int pR, int pC, int r, int c){
				panelRow = pR;
				panelCol = pC;
				row = r;
				col = c;
			}
			
			public void actionPerformed(ActionEvent e) {
				if (ticTacToeBoard[panelRow][panelCol].markTile(row, col, player)){
					JButton button = (JButton) e.getSource();
					if (player){
						button.add(new PlayerComponent(1, player1Color));
						outputText.setText(player2Name + "'s turn");
					}
					else{
						button.add(new PlayerComponent(2, player2Color));
						outputText.setText(player1Name + "'s turn");
					}
					button.setEnabled(false);
					player = (!player);
					refocusPanels(row, col);
					if(ticTacToeBoard[panelRow][panelCol].getVictor() >= 0){
						openSpaces--;
						if (checkBoard()){//checks for victory
							closeBoard();
							if (victor == 1)//player1
								outputText.setText(player1Name + " won!");
							else if (victor == 2)//player2
								outputText.setText(player2Name + " won!");
							else//tie
								outputText.setText("	Tie!	");
						}
					}
					
					ultimateBoard.revalidate();
				}
			}
			
		}
		
		ultimateBoard = new JPanel();
		ultimateBoard.setLayout(new GridLayout(size,size));
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++){
				JPanel board = new JPanel();
				board.setLayout(new GridLayout(size,size));
				board.setBackground(lighterColor(player1Color));
				board.setBorder(new LineBorder(Color.GRAY));
				for(int k = 0; k < size; k++)
					for(int l = 0; l < size; l++){
						JButton button = new JButton();
						button.addActionListener(new TileListener(i,j,k,l));
						button.setBackground(lighterColor(player1Color));
						button.add(new PlayerComponent(0, new Color(0,0,0)));
						button.setEnabled(false);
						board.add(button);
					}
				ultimateBoard.add(board);
			}
		main.removeAll();
		main.add(text, BorderLayout.NORTH);
		main.add(ultimateBoard, BorderLayout.CENTER);
		frame.revalidate();
		
		ticTacToeBoard = new TileBoard[size][size];
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
				ticTacToeBoard[i][j] = new TileBoard(size);
	}
	
	static Color lighterColor(Color tempColor){
		int tempRed = tempColor.getRed();
		int tempGreen = tempColor.getGreen();
		int tempBlue = tempColor.getBlue();
			if (tempRed > tempGreen && tempRed > tempBlue){
				tempGreen = (int)(tempGreen*1.5);
				tempBlue = (int)(tempBlue*1.5);
				if(tempGreen == 0)
					tempGreen = tempRed/2;
				if(tempBlue == 0)
					tempBlue = tempRed/2;
			}
			else if(tempGreen > tempRed && tempGreen > tempBlue){
				tempRed = (int)(tempRed*1.5);
				tempBlue = (int)(tempBlue*1.5);
				if(tempRed == 0)
					tempRed = tempGreen/2;
				if(tempBlue == 0)
					tempBlue = tempGreen/2;
			}
			else if (tempBlue > tempRed && tempBlue > tempGreen){
				tempGreen = (int)(tempGreen*1.5);
				tempRed = (int)(tempRed*1.5);
				if(tempGreen == 0)
					tempGreen = tempBlue/2;
				if(tempRed == 0)
					tempRed = tempBlue/2;
			}
		if(tempRed > 255)
			tempRed = 255;
		if(tempGreen > 255)
			tempGreen = 255;
		if(tempBlue > 255)
			tempBlue = 255;
		return new Color(tempRed, tempGreen, tempBlue);
	}
	
}
