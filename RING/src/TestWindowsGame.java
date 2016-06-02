import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class TestWindowsGame{
	
	public static void main(String[] args) throws SlickException {
		        new AppGameContainer(new WindowGame(), 640, 480, false).start();
		    }

}
