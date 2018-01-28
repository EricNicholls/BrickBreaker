import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.Random;

public class BrickClass {
	
	DecimalFormat form = new DecimalFormat ("#.##");
	Random rn = new Random ();
	
	Font font = new Font("Arial", Font.BOLD, 40);
	Font smallFont = new Font ("Arial", Font.BOLD, 30);
	
	//canvas size
	private int xCan = 1800;
	private int yCan = 900;
	
	//paddle
	private int Xcoor;
	private int Ycoor;
	private double barSize = 150;
	
	//ball
	private int XcoorBall;
	private int YcoorBall;
	private double Xdirection = 2 * rn.nextDouble() -1;
	private int Ydirection = 1;
	
	private int initSpeed = 6;
	private double speed = initSpeed;
	
	//score display
	private int startingLives = 2;
	private int lives = startingLives;
	private int score = 0;
	private int highScore = 0;
	private int level = 1;
	
	//random numbers for reset direction and location
	private int rand = 0;
	private double rand2 = 0;
	
	//bricks
	private int r1 = 800;
	private int r2 = 900;
	boolean b1 = true;
	boolean b2 = true;
	
	public int canvasX ()
	{
		return xCan;
	}
	
	public int canvasY ()
	{
		return yCan;
	}

	public BrickClass ()
	{
		Xcoor = xCan /2;
		Ycoor = 50;
		XcoorBall = Xcoor;
		YcoorBall = 65;
	}
	
	public void background ()
	{
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledRectangle(xCan /2, yCan / 2, xCan / 2, yCan / 2);
	}
	
	public void drawBar()
	{
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledRectangle(Xcoor, Ycoor, barSize, 7);
	}
	
	public void drawBall()
	{
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledCircle(XcoorBall, YcoorBall, 20);
	}
	
	public void left()
	{
		if (Xcoor > barSize)
		{
			Xcoor -= 22;
		}
	}
	public void right()
	{
		if (Xcoor < xCan - barSize)
		{
			Xcoor += 22;
		}
		
	}
	
	public int getBallX()
	{
		return XcoorBall;
	}
	public int getBallY()
	{
		return YcoorBall;
	}
	
	
	
	public void displayScore()
	{
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.setFont(font);
		// StdDraw.textRight(2190, 970, "High Score:     " + highScore);
		// StdDraw.textRight(2190, 910, "Score:     " + score);
		
		StdDraw.textRight (xCan - 10, yCan - 30, "Lives:     " + (lives + 1));
		StdDraw.textLeft (10, yCan - 30, "Level:     " + (level));
	}
	

	public void gameOverWin()
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(xCan/2, yCan / 2, "YOU WIN");
		speed = 0;
	}
	
	public void newLevel()
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(StdDraw.WHITE);
		level ++;
		b1 = true;
		// r1 = rn.nextInt(2 * xCan - 149);
		// r2 = rn.nextInt(2 * xCan - 149);
		b2 = true;
		
		
		StdDraw.text(xCan / 2, yCan - 200, "New Level. Level: " + level);
		lives --;
		XcoorBall = Xcoor;
		YcoorBall = 67; 
		Ydirection = 1;
		
		rand2 = 2 * rn.nextDouble() -1;
		Xdirection = rand2;
		
		score = 0;
		speed = initSpeed;
	}
	
	public void gameOverLoss()
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(xCan / 2, yCan / 2, "GAME OVER!");
		speed = 0;
			
			
		if (score > highScore)
		{
			highScore = score;
		}	
	}
	
	
	public void livesLost()
	{
		StdDraw.setFont(font);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.text(xCan / 2, yCan / 2, "Lives:   " + (lives));
		speed = 0;
	}
	
	public int xBall ()
	{
		return XcoorBall;
	}

	public int yBall ()
	{
		return YcoorBall;
	}
	
	public void resetBall()
	{
		lives --;
		XcoorBall = Xcoor;
		YcoorBall = 67;
		Ydirection = 1;
		
		rand2 = 2 * rn.nextDouble() -1;
		Xdirection = rand2;
		
		score = 0;
		speed = initSpeed;
	}
	
	
	
	public void ballMove()
	{
		XcoorBall += Xdirection * speed;
		
		
		if (XcoorBall >= xCan - 15)
		{
			if (Xdirection > 0)
			{
				Xdirection *= -1;
			}
		}
			
		if (XcoorBall <= 15)
		{
			if (Xdirection < 0)
			{
				Xdirection *= -1;
			}
		}
		
		YcoorBall += Ydirection * speed;
		
		if (YcoorBall >= yCan - 15)
		{
			Ydirection *= -1;
		}
		
		if (YcoorBall >= 50 && YcoorBall <= 67 && Xcoor > XcoorBall - barSize && Xcoor < XcoorBall + barSize)
		{
			Ydirection = 1;
			speed += 0.3;
			score ++;
			if (Xcoor >= XcoorBall - barSize && Xcoor <= XcoorBall - (2*barSize/3))
			{
				Xdirection = 2.3;
			}
			else if (Xcoor >= XcoorBall - (2* barSize / 3) && Xcoor <= XcoorBall - (barSize/3))
			{
				Xdirection = 1.25;
			}
			else if (Xcoor >= XcoorBall - (barSize/3) && Xcoor <= XcoorBall + 0)
			{
				Xdirection = 0.5;
			}
			else if (Xcoor >= XcoorBall + 0 && Xcoor <= XcoorBall + (barSize/3))
			{
				Xdirection = -0.5;
			}
			else if (Xcoor >= XcoorBall + (barSize/3) && Xcoor <= XcoorBall + (2 * barSize /3))
			{
				Xdirection = -1.25;
			}
			
			else if (Xcoor >= XcoorBall + (2*barSize / 3) && Xcoor <= XcoorBall + barSize)
			{
				Xdirection = -2.3;
			}
			
		}
		
	}
	public int getLives()
	{
		return lives;
	}
	
	
	// BRICKS
	public boolean b1 ()
	{
		return b1;
	}
	public void brick1()
	{
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledSquare(r1, yCan - 20, 50);
		if ((XcoorBall > r1 - 70 && XcoorBall < r1 + 70) && (YcoorBall >= yCan - 50))
		{
			b1 = false;
		}
		
	}
	public boolean b2 ()
	{
		return b2;
	}
	public void brick2()
	{
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledSquare(r2, yCan - 20, 50);
		if ((XcoorBall > r2 - 70 && XcoorBall < r2 + 70) && (YcoorBall >= 950 ))
		{
			b2 = false;
		}
		
	}
}
