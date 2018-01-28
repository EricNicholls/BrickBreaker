import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class BrickDriver {

	
	public static void main (String[] args) {

		BrickClass t = new BrickClass ();
		
		int xCan = t.canvasX();
		int yCan = t.canvasY();
		
		StdDraw.setCanvasSize(xCan, yCan);
        StdDraw.setXscale(0, xCan);
        StdDraw.setYscale(0, yCan);
        
        int yBall;
        int xBall;
        int lives1;
        
        boolean br1;
        boolean br2;
        
        while (true)
        {
        	StdDraw.clear();
        	t.background();
        	t.drawBar (); 
        	t.drawBall ();
        	
        	if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
        	{
        		t.left();
        	}
        	if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
        	{
        		t.right();
        	}
        	
        	
        	t.ballMove();
        	
        	t.displayScore();
        	
        	br1 = t.b1 ();
        	if (br1 == true)
        	{
        		t.brick1();
        	}
        	
        	br2 = t.b2 ();
        	if (br2 == true)
        	{
        		t.brick2();
        	}
        	
        	if (br1 == false && br2 == false)
        		
        	{
        		t.gameOverWin();
        		if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE))
    			{
    				t.newLevel();
    			}
        		
        	}
        	
        	yBall = t.yBall();
        	lives1 = t.getLives();
   
        	
        	if (yBall < 0)
        	{
        		
        		if (lives1 > 0)
        		{
        			t.livesLost();
        			if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE))
        			{
        				t.resetBall();
        			}
        		}
        		else 
        		{
        			t.gameOverLoss();
        		}
        	}
        	
        	StdDraw.show(20);
        }
        
	}
}