package wycody.effects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Created at: Jan 22, 2017 3:00:19 AM
 * 
 * @author Walied-Yassen A.K.A Cody
 */
public class EffectUtil {

	/**
	 * Creates an empty image with a specific dimensions.
	 * 
	 * @param width
	 *            the image width.
	 * @param height
	 *            the image height.
	 * @param color
	 *            the image fill color.
	 * @return the created {@link BufferedImage} instance.
	 */
	public static BufferedImage createEmptyImage(int width, int height, Color color) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		g.setColor(color);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());
		g.dispose();
		return image;

	}

}
