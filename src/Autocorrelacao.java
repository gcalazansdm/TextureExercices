import java.awt.Color;
import java.awt.image.BufferedImage;

public class Autocorrelacao {
	public static float run(final int p, final int q, final BufferedImage img){
		float rValue = 0;
		int denominador = 0;
		int width = img.getWidth();
		int height = img.getHeight();
		int colorA;
		int colorB;
		for (int i = 0; i < width-p; i++) {
			for (int j = 0; j < height-q; j++) {
				colorA = new Color(img.getRGB(i, j)).getRed();
				colorB = new Color(img.getRGB(i+p, j+q)).getRed();
				denominador += (colorA + colorB)*(colorA + colorB);
				rValue = colorA * colorB;
			}
		}
		rValue /= denominador;

		return rValue;
	}
}
