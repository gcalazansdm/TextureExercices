import java.awt.Color;
import java.awt.image.BufferedImage;

public class NGTDM {

	private double[] matrix;

	public NGTDM(BufferedImage img, int windowRadius) {
		matrix = new double[256];
		int width = img.getWidth();
		int height = img.getHeight();
		double valuesInWindow;
		int grayColor;
		int window;
		for (int k = 0; k < width; k++) {
			for (int l = 0; l < height; l++) {
				valuesInWindow = 0;
				window= 0;
				for (int i = -1 * windowRadius; i < windowRadius; i++) {
					if(k + i < 0 || k + i > width)
						continue;
					for (int j = -1 * windowRadius; j < windowRadius; j++) {
						if(l + j < 0 || l + j >height)
							continue;
						valuesInWindow += new Color(img.getRGB(k + i, l + j)).getRed();
						window++;
					}
				}
				grayColor = new Color(img.getRGB(k , l)).getRed();
				valuesInWindow =  (grayColor - valuesInWindow)/(window-1);
				matrix[grayColor] = Math.abs(grayColor-valuesInWindow);
			}
		}
		
	}
}
