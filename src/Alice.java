import objectdraw.*;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Vector; 

public class Alice extends ActiveObject implements Shootable{
	
	private DrawingCanvas canvas;
	private VisibleImage alice;
	public boolean rightOn = false;
	public boolean leftOn = false;
	private static final int alice_WIDTH = 50;
	private static final int alice_HEIGHT = 50;
	private Enemies enemies;
	private boolean alive;
	private FilledRect r;
	private MadHatterProject main;
	private Shootable target;

	
	public Alice(Image aliceImage, double x , double y, double w, double h, Enemies enemies, MadHatterProject main, DrawingCanvas canvas) {
		
		alice = new VisibleImage(aliceImage, x, y, w, h, canvas);
		this.canvas = canvas;
		this.enemies = enemies;
		this.main = main;
		start();
	}
	
	public void rightOn(boolean b) {
		rightOn = b;
	}

	
	public void leftOn(boolean b) {
		leftOn = b;
	}

	public boolean tryShoot(FilledRect r) {
		if(r.overlaps(alice)) {
			r.removeFromCanvas();
			main.lose();
			return true;
		} 
		return false;
	}


	public void clear() {
			alive = false;
			if(alice.overlaps(MadHatterProject.b)) {
				alice.removeFromCanvas();
			}
	}
	
	public void moveLeft() {
		alice.move(-10, 0);
	}

	public void moveRight() {
		alice.move(10, 0);

	}

	public void addTarget(Shootable s) {
		target = s;
	}

	public void shoot() {
		Bullet bull = new Bullet(alice.getX() + alice_WIDTH/2, alice.getY() + 5, true, enemies, target, canvas);
	}

	public void run() {
		pause(1000);
		while(true) {
			if(rightOn && alice.getX() < 800-alice_WIDTH) {
				moveRight();
			}
			else if (leftOn && alice.getX() > 0) {
				moveLeft(); 
			}
			pause(40);
		}
		
	}
}
