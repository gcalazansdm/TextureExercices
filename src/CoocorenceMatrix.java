import java.awt.Color;
import java.awt.image.BufferedImage;

public class CoocorenceMatrix {
	private double[][] coocorenceMatrix;
	private int numberOfValues;

	public CoocorenceMatrix(BufferedImage img) {
		coocorenceMatrix = new double[256][256];
		int width = img.getWidth();
		int height = img.getHeight();
		numberOfValues = height * (width - 1);
		int colorIndex = 0;
		int neghboorColorIndex = 0;
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width - 1; i++) {
				colorIndex = new Color(img.getRGB(i, j)).getRed();
				neghboorColorIndex = new Color(img.getRGB(i + 1, j)).getRed();
				coocorenceMatrix[colorIndex][neghboorColorIndex]++;
			}
		}
	}

	public double probability(int i, int j) {
		double rValue = coocorenceMatrix[i][j];
		rValue = rValue / ((float) numberOfValues);
		return rValue;
	}

	public double meanX() {
		double rValue = 0;
		double tempValue = 0;
		for (int i = 0; i < coocorenceMatrix.length; i++) {
			tempValue = 0;

			for (int j = 0; j < coocorenceMatrix[i].length; j++) {
				tempValue += probability(i, j);
			}

			rValue += tempValue * i;
		}

		return rValue;
	}

	public double meanY() {
		double rValue = 0;
		double tempValue = 0;
		for (int j = 0; j < coocorenceMatrix[0].length; j++) {
			tempValue = 0;
			for (int i = 0; i < coocorenceMatrix.length; i++) {

				tempValue += probability(i, j);
			}

			rValue += tempValue * j;
		}

		return rValue;
	}

	public double varianceX() {
		double rValue = 0;
		double tempValue = 0;
		double mean = meanX();
		for (int i = 0; i < coocorenceMatrix.length; i++) {
			tempValue = 0;

			for (int j = 0; j < coocorenceMatrix[i].length; j++) {
				tempValue += probability(i, j);
			}

			rValue += tempValue * (i - mean) * (i - mean);
		}

		return rValue;
	}

	public double varianceY() {
		double rValue = 0;
		double tempValue = 0;
		double mean = meanY();
		for (int j = 0; j < coocorenceMatrix[0].length; j++) {
			tempValue = 0;
			for (int i = 0; i < coocorenceMatrix.length; i++) {

				tempValue += probability(i, j);
			}

			rValue += tempValue * (j - mean) * (j - mean);
		}

		return rValue;
	}

	public double maxProbability() {
		double rValue = -1 * 0xFFFFFF;
		double tempValue = 0;
		for (int j = 0; j < coocorenceMatrix[0].length; j++) {
			tempValue = 0;
			for (int i = 0; i < coocorenceMatrix.length; i++) {

				tempValue = probability(i, j);
				if (tempValue > rValue) {
					rValue = tempValue;
				}
			}
		}

		return rValue;
	}

	public double minProbability() {
		double rValue = 0xFFFFFF;
		double tempValue = 0;
		for (int j = 0; j < coocorenceMatrix[0].length; j++) {
			tempValue = 0;
			for (int i = 0; i < coocorenceMatrix.length; i++) {
				tempValue = probability(i, j);
				if (tempValue < rValue && tempValue > 0.0f) {
					rValue = tempValue;
				}
			}
		}
		return rValue;
	}

	public double correlation() {
		double rValue = 0;
		double tempValue = 0;
		double meanX = meanX();
		double meanY = meanY();
		double varianceX = varianceX();
		double varianceY = varianceY();
		if (varianceY != 0 && varianceX != 0) {
			for (int j = 0; j < coocorenceMatrix[0].length; j++) {
				for (int i = 0; i < coocorenceMatrix.length; i++) {
					tempValue += (i - meanX) * (j - meanY) * probability(i, j) / (varianceX * varianceY);
				}
			}
			rValue = tempValue;
		}
		return rValue;
	}

	public double contrast() {
		float rValue = 0;
		float tempValue = 0;
		for (int j = 0; j < coocorenceMatrix[0].length; j++) {
			for (int i = 0; i < coocorenceMatrix.length; i++) {
				tempValue += (i - j) * (i - j) * probability(i, j);
			}
		}
		rValue = tempValue;
		return rValue;
	}

	public double uniformity() {
		double rValue = 0;
		double tempValue = 0;
		for (int j = 0; j < coocorenceMatrix[0].length; j++) {
			for (int i = 0; i < coocorenceMatrix.length; i++) {
				tempValue = probability(i, j);
				rValue += tempValue* tempValue;
			}
		}
		return rValue;
	}
	
	public double homogeneity() {
		double rValue = 0;
		double tempValue = 0;
		for (int j = 0; j < coocorenceMatrix[0].length; j++) {
			for (int i = 0; i < coocorenceMatrix.length; i++) {
				tempValue += probability(i, j)/(1+Math.abs(i-j));
			}
		}
		rValue = tempValue;
		return rValue;
	}
	//FIX-ME:
	public double entropy() {
		double rValue = 0;
		double tempValue = 0;
		double log2 = Math.log(2);
		for (int j = 0; j < coocorenceMatrix[0].length; j++) {
			for (int i = 0; i < coocorenceMatrix.length; i++) {
				tempValue = probability(j, i);
				rValue += tempValue * Math.log(tempValue) /log2;
			}
		}
		rValue = -1 * rValue;
		return rValue;
	}
}
