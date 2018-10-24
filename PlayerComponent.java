import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

public class PlayerComponent extends JComponent {

	private Polygon player1;
	private Ellipse2D player2;
	private int mark;
	private Color playerColor;
	public PlayerComponent(int sign, Color playerCol){
		mark = sign;
		playerColor = playerCol;
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2D = (Graphics2D) g;
		
		player1 = new Polygon();
		//player1 has an "X" symbol
		player1.addPoint(0, 0);
		//top left
		player1.addPoint(16, 0);
		player1.addPoint(25, 17);
		//top mid
		player1.addPoint(34, 0);
		player1.addPoint(50, 0);
		//top right
		player1.addPoint(34, 25);
		//mid right
		player1.addPoint(50, 50);
		//bot right
		player1.addPoint(34, 50);
		player1.addPoint(25, 33);
		//bot mid
		player1.addPoint(16, 50);
		player1.addPoint(0, 50);
		//bot left
		player1.addPoint(16, 25);
		//mid left
		
		//player2 has an "O" symbol
		player2 = new Ellipse2D.Double(5,2,40,45);
		Ellipse2D.Double player2Hole = new Ellipse2D.Double(10,7,30,35);
		
		if(mark == 1){
			g2D.setColor(playerColor);
			g2D.fill(player1);
		}
		else if(mark == 2){
			g2D.setColor(playerColor);
			g2D.fill(player2);getSize();
			g2D.setColor(new Color(235,235,235));
			g2D.fill(player2Hole);
		}
	}
	
}