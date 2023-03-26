import objectdraw.*;
import java.awt.Color;
import java.util.ArrayList;

public class Bullet extends ActiveObject implements Shootable{

	private static final int BULLET_LENGTH = 9; 
	private static final int BULLET_WIDTH = 3; 
	private Shootable target;
	FilledRect bullet;
	private DrawingCanvas canvas;
	private boolean fromAlice;
	private double speed;
	private Enemies enemies;
	private FilledRect r;

	public Bullet( double x, double y, boolean fromAlice, Enemies enemies, Shootable target, DrawingCanvas canvas) {
		bullet = new FilledRect(x, y, BULLET_WIDTH, BULLET_LENGTH, canvas);
		this.canvas = canvas;
		this.fromAlice = fromAlice;
		this.target = target;
		this.enemies = enemies;
		
		if(fromAlice) {
			bullet.setColor(Color.WHITE);
			speed = -3;
		} else {
			bullet.setColor(Color.YELLOW);
			speed = 3;
		}
		start();
	}

	public boolean fromAlice() {
		return fromAlice;
	}

	
	
	public boolean tryShoot(FilledRect r) {
		if(r.overlaps(bullet)){	
			r.removeFromCanvas();
			bullet.removeFromCanvas();
			return true;
		} 
		 return false;
	}  
	
	//ok might work remove method || nvm :/
	
//	public void remove(FilledRect s) {
//		if(s.overlaps(bullet)) {
//			bullet.removeFromCanvas();
//		}
//	}
	
	
	public void run() {

		while((bullet.getX() >= 0 && bullet.getX() <= canvas.getWidth()) && (bullet.getY() <= canvas.getHeight() && bullet.getY() >= 0)) {
			bullet.move(0,speed); 

			pause(22);

			if(target.tryShoot(bullet)) {
				enemies.addScore(10);
				
				return;
			}
			try {
				if (bullet.getY() == canvas.HEIGHT) {
					bullet.removeFromCanvas();
					return;
				}
			} catch(Exception e) {
			}
			
		}
	}
}
