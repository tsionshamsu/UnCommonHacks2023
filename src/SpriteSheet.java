import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {

	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image) {
		this.image = image;
		
	}
	
	public BufferedImage grabImage(int column, int row, int width, int height) {
		
		BufferedImage img = image.getSubimage((column*55)-55, (row*55) -55, width, height);
		return img;
	}
}
