

import objectdraw.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class MadHatterProject extends WindowController implements KeyListener {



private static final int HEIGHT=800;
private static final int WIDTH = 800;
private static final int alice_WIDTH = 60;
private static final int alice_HEIGHT = 60;
//private static final Color WHITE = ;

private Alice alice;
private Enemies enemies;
private Text t;
private Text trab;
static FilledRect b;
private Text endt;
private Text scoret;
static int score;
private Bullet bullet;
private boolean running;


private boolean keyDown;

private VisibleImage firstview ;
private VisibleImage firstrabbit;

private VisibleImage secondview;
private VisibleImage secondchesh;


//private DrawingCanvas canvas;
//private Image[] dsp;

//trial 2.0
//private BufferedImage spritesheet = null;
//private BufferedImage player;

//public void init() {
//	BufferedImageLoader loader = new BufferedImageLoader();
//	try {
//		spritesheet = loader.loadImage("spritesheet.png");
//		
//	}catch(IOException e) {
//		e.printStackTrace();
//	}
//	SpriteSheet ss = new SpriteSheet(spritesheet);
//	player = ss.grabImage(1,1,32,32);
//}



public void begin() {


Container c = this;
while(! (c instanceof Frame)) {
	c = c.getParent();
	}
	((Frame)c).setResizable(false);

	b = new FilledRect(0,0,WIDTH, HEIGHT, canvas);
	b.setColor(Color.WHITE);
	
	//the not so cool pics
	Image dsp = getImage("ch.png");	
	firstview = new VisibleImage(dsp, 0, 0, 800, 800, canvas);
	
	Image secondback = getImage("backgreen.png");
	secondview = new VisibleImage(secondback, 0, 0, 800, 800, canvas);
	secondview.hide();

	t = new Text("click down the rabbit hole...",WIDTH/2-210, HEIGHT/2-250, canvas);
	t.setFontSize(30);
	t.setColor(Color.white);
	
	Image rab1 = getImage("stood.png");	
	firstrabbit = new VisibleImage(rab1, WIDTH/2-380, HEIGHT/2+250, 100, 100, canvas);
	
	trab = new Text("Oh dear! Oh dear! I shall be too late!",WIDTH/2-280, HEIGHT/2+300, canvas);
	trab.setFontSize(10);
	trab.setColor(Color.white);

	endt = new Text(" ",WIDTH/2, HEIGHT/2, canvas);
	//scoret = new Text(" ",WIDTH/2, HEIGHT/2, canvas);
	
	Image chch = getImage("congrachesh.png");	
	secondchesh = new VisibleImage(chch, WIDTH/2-80, HEIGHT/2-250, 100, 100, canvas);
	secondchesh.hide();
	
	running = false;
	
	
}







public void onMouseClick(Location l) {
	
	if(!running) {
	running=true;
	firstview.hide();
	firstrabbit.hide();
	secondview.show();
	secondchesh.hide();
	
	endt.hide();
	t.hide();
	trab.hide();
	b.show();
	b.setColor(Color.BLACK);
	Image[] invaders = {getImage("rqsprite.png"), getImage("spriblackcard.png"), getImage("reds.png"),
	getImage("dragonphoto.png"), getImage("cardsred.png"), getImage("cardsblack.png")};
	enemies = new Enemies(invaders, false, this, canvas);
	Image aliceImage = getImage("sprite_4.png");
	alice = new Alice(aliceImage, WIDTH/2, HEIGHT-200, alice_WIDTH, alice_HEIGHT, enemies, this, canvas);
	enemies.addTarget(alice);
	alice.addTarget(enemies);
	requestFocus();
	addKeyListener(this);
	canvas.addKeyListener(this);

}
}

public void keyTyped(KeyEvent e) {
	if ( e.getKeyCode() == KeyEvent.VK_SPACE ||
			e.getKeyCode() == KeyEvent.VK_UP ) {
		alice.shoot();

}
	else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
		alice.leftOn(true);


} 	else if ( e.getKeyCode() == KeyEvent.VK_RIGHT ) {

	alice.rightOn(true);

        }
}


public void keyReleased(KeyEvent e){
	keyDown = false;
	if (e.getKeyCode() == KeyEvent.VK_LEFT ||
                    e.getKeyCode() == KeyEvent.VK_RIGHT ) {
		alice.leftOn(false);
		alice.rightOn(false);

}
}


public void keyPressed(KeyEvent e){
	if (!keyDown)
	{
		keyTyped(e);
	}

	keyDown = true;
}

public void win() {
	running = false;
	alice.clear();
	enemies.clear();
	//try to remove bullet
	//bullet.remove;
	
	t.setText("Congrats Alice!\n"   + "Score: " + score + "/540" );
	
	t.setColor(Color.GREEN);
	t.setFontSize(40);
	t.moveTo(WIDTH/2 - t.getWidth()/2, HEIGHT/2 - t.getHeight());
	System.out.println();
	
//	scoret.setText("Score: " + score + "/540" );
//	scoret.setColor(Color.GREEN);
//	scoret.setFontSize(40);
//	scoret.moveTo(WIDTH/2 - scoret.getWidth()/2, HEIGHT/2 - scoret.getHeight() +40) ;
	
	secondchesh.show();
	endt.setText("Click to Play Again!");
	endt.setFontSize(40);
	endt.setColor(Color.GREEN);
	endt.moveTo(WIDTH/2 - endt.getWidth()/2, HEIGHT/2 + endt.getHeight() * 2);
	endt.show();
	t.show();
	//scoret.show();
	
	
	score = 0;
}

public void lose() {

	running = false;
	alice.clear();
	enemies.clear();
	t.setText("Game Over! Score: " + score + "/540");
	t.setColor(Color.RED);
	t.setFontSize(40);
	t.moveTo(WIDTH/2 - t.getWidth()/2, HEIGHT/2 - t.getHeight());
	endt.setText("Click to Play Again.");
	endt.setFontSize(40);
	endt.setColor(Color.RED);
	endt.moveTo(WIDTH/2 - endt.getWidth()/2, HEIGHT/2 + endt.getHeight() * 2);
	endt.show();
	t.show();
	score = 0;
}

    public static void main(String[] args) {
        new MadHatterProject().startController(WIDTH, HEIGHT);      
       
}

}