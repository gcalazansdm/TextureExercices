import java.awt.Color;
import java.awt.image.BufferedImage;

public class AbordagensEstatiscas {
	public static double media(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		double value = 0.0;
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				value += new Color(img.getRGB(i, j)).getRed();
			}
		}
		value /= width*height;
		return value;
	}
	public static double variancia(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		double media = media(img);
		double value = 0.0;
		double tempValue = 0.0;
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				tempValue = (new Color(img.getRGB(i, j)).getRed() - media);
				tempValue = tempValue * tempValue;
				value += tempValue;
			}
		}
		value /= width*height;
		return value;
	}
	public static double assimetria(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		double media = media(img);
		double value = 0.0;
		double variancia = 0.0;
		double tempValue = 0.0;
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				tempValue = (new Color(img.getRGB(i, j)).getRed() - media);
				variancia += tempValue * tempValue;
				tempValue = tempValue * tempValue * tempValue;
				value += tempValue;
			}
		}
		variancia /= width*height;
		value /= width*height*Math.pow(Math.sqrt(variancia), 3);
		return value;
	}
	public static double curtose(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		double media = media(img);
		double value = 0.0;
		double variancia = 0.0;
		double tempValue = 0.0;
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				tempValue = (new Color(img.getRGB(i, j)).getRed() - media);
				tempValue = tempValue * tempValue;
				variancia += tempValue;
				tempValue = tempValue * tempValue;
				value += tempValue - 3;
			}
		}
		variancia /= width*height;
		value /= width*height*variancia*variancia;
		return value;
	}
}
