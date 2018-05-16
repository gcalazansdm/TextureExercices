import java.awt.Color;
import java.awt.image.BufferedImage;

public class Histograma {
	private static double[] histograma;
	private static int width;
	private static int height;
	public Histograma(BufferedImage img){
		width = img.getWidth();
		height = img.getHeight();
		histograma = new double[256];
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				histograma[new Color(img.getRGB(i, j)).getRed()]++;
			}
		}
	}
	public double moment(int n){
		double value = 0;
		double mean = mean();
		for (int j = 0; j < histograma.length; j++) {
			value += Math.pow((j - mean), n) * (histograma[j]/n);
		}
		return value;
		
	}

	public double mean(){
		double value = 0;
		int n = width * height;
		for (int j = 0; j < histograma.length; j++) {
			value += j * (histograma[j]/n);
		}
		return value;
	}

		
	public double assimetry(){
		return moment(3);
	}
	
	public double energy(){
		double value = 0;
		int n = width * height;
		for (int j = 0; j < histograma.length; j++) {
			value += (histograma[j]/n) * (histograma[j]/n);
		}
		return value;
	}
	public double entropy(){
		double value = 0;
		int n = width * height;
		double log2 = Math.log(2);
		for (int j = 0; j < histograma.length; j++) {
			value += (histograma[j]/n) * Math.log(histograma[j]/n)/log2;
		}
		return -1 * value;
	}
}
