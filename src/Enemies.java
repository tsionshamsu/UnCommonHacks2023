import objectdraw.*;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Vector;


public class Enemies extends ActiveObject implements Shootable{
	private static final int ENEMY_X= 60;
	private static final int ENEMY_Y= 60;
	private static final int ENEMY_SIZE= 60;
	private DrawingCanvas canvas;
	private boolean run;
	private int counter = 0;
	private VisibleImage shooter;
	private Shootable target;
	private Alice alice;
	private boolean fromalice;
	private MadHatterProject main;
	private boolean alive;
	private int[][] rows;
	private FilledRect r;
	private int wait = 1500;
	private VisibleImage[][] enemies = new VisibleImage[6][9];
	
	public Enemies(Image[] invaders, boolean fromalice, MadHatterProject main, DrawingCanvas canvas) {

		this.main = main;
		this.canvas=canvas;
		for (int row = 0; row < enemies.length; row++) {
			for (int col = 0; col < enemies[0].length; col++) {
				enemies[row][col] = new VisibleImage(invaders[row], 29+col*ENEMY_X, row*ENEMY_Y, 
						ENEMY_SIZE, ENEMY_SIZE, canvas);
			}
		}
		rows = new int[enemies.length][enemies[0].length]; 

		for(int i = 0; i < rows.length; ++i) {
			for(int j = 0; j < rows[0].length; ++j) {
				rows[i][j] = 0; 
			}
		}
		run = true;
		start();
	}

	public void addScore(int x) {
		main.score += x;
	}   

	public int randomRow(int random) {

		for(int r = rows.length-1; r >= 0 ; --r) {
			if(rows[r][random] == 0) {
				return r; 
			}
		}
		return -1; 
	}

	public void shoot() {

		int c = (int)(Math.random()*9);
		int r = randomRow(c);

		while(r == -1) {
			c = (int)(Math.random() * 9); 
			r = randomRow(c); 
		}

		shooter = enemies[r][c];
		new Bullet(shooter.getX() + ENEMY_X/2, shooter.getY() + 5, false, this, target, canvas);
		}


	public void addTarget(Shootable s) {
		target = s;
	}

	public boolean tryShoot(FilledRect r) {
		for(int i = 0; i < enemies.length; i++) {
			for(int j = 0; j < enemies[0].length; j++) {
				if(enemies[i][j].overlaps(r)) {
					try {
					enemies[i][j].removeFromCanvas();
					r.removeFromCanvas();
					rows[i][j] = 1;
					return true;
					} catch (Exception e) {
					}
				} 
			}
		} return false;
	}

	public boolean checkWin() {

		for(int i = 0; i < rows.length; ++i) {
			for (int j = 0; j < rows[0].length; ++j) {
				if(rows[i][j] == 0) {
					return false;
				}
			}
		}
		return true; 
	}

	public boolean checkLose() {
		for (int i = 0; i < enemies.length; i++) {
			for (int j = 0; j < enemies[0].length; j++) {
				
				if(enemies[i][j].getY() > 550) {
					return true;
				}

			}

		} return false;

	}

	public void moveLeft() {

		for (int i = 0; i < enemies.length; i++) {

			for (int j = 0; j < enemies[0].length; j++) {

				enemies[i][j].move(-ENEMY_X, 0);

			} 
		} 
	}

	public void moveRight() {

		for (int i = 0; i < enemies.length; i++) {
			for (int j = 0; j < enemies[0].length; j++) {
				enemies[i][j].move(ENEMY_X, 0);
			} 
		} 
	}
	
	public void moveDown() {

		for (int i = 0; i < enemies.length; i++) {
			for (int j = 0; j < enemies[0].length; j++) {
				enemies[i][j].move(0, ENEMY_Y);
			}
		}
	}
	public void clear() {

		for (int i = 0; i < enemies.length; i++) {
			for (int j = 0; j < enemies[0].length; j++) {
				if(enemies[i][j].overlaps(main.b)) {
					enemies[i][j].removeFromCanvas();
				}
			}
		}
	}
	
	public void run() {

		while(run) {
			pause(1000);

			if(counter <= 3) {
				moveRight();
				wait -= 100;
				/*DUUUUUUUUDE!! The code right below is probably wrong but shoot doesn't work rn*/

				//shoot();
			}else if(counter == 4 || counter == 9) {
				moveDown();
				wait -= 100;	
				//shoot();

			}else {

				moveLeft();
				wait -= 100;
				//shoot();
			}


			if(checkWin()) {
				main.win();

			} else if(checkLose()) {
				main.lose();
				//error comes back here
				//break;
				return;

			}

			pause(700);
			
			
			
			if(!checkLose()) {
			shoot();
			
			}
			
//			if(checkWin()) {
//				shoot();
//				
//				}
			
			pause(700);

			counter = (counter + 1)% 10;

		} 
	}
}