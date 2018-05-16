import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) {
		try {
			String imagePath = "C:/Users/Gabriel/Desktop/OrganiZen/Nova pasta (3)/01_01.jpg";
			String outputPath = imagePath.substring(0, imagePath.lastIndexOf('.')) + ".txt";
			System.out.println(outputPath);
			String rVAlues = "";
			File imageFile = new File(imagePath);

			BufferedImage image = ImageIO.read(imageFile);

			rVAlues += "Abordagens estatisticas";
			rVAlues += "\nMedia\n";
			rVAlues += AbordagensEstatiscas.media(image);
			rVAlues += "\nCurtose\n";
			rVAlues += AbordagensEstatiscas.curtose(image);
			rVAlues += "\nVariancia\n";
			rVAlues += AbordagensEstatiscas.variancia(image);
			rVAlues += "\nAssimetria\n";
			rVAlues += AbordagensEstatiscas.assimetria(image);

			rVAlues += "\n\nAutocorrelacao\n";
			rVAlues += Autocorrelacao.run(image.getWidth()/2,image.getHeight()/2,image);

			rVAlues += "\n\nMatriz de co-ocorencia\n";
			CoocorenceMatrix co = new CoocorenceMatrix(image);
			rVAlues += "\nContraste\n";
			rVAlues += co.contrast();
			rVAlues += "\nCorelacao\n";
			rVAlues += co.correlation();
			rVAlues += "\nHomogenidade\n";
			rVAlues += co.homogeneity();
			rVAlues += "\nProbabilidade Maxima\n";
			rVAlues += co.maxProbability();
			rVAlues += "\nMedia em X\n";
			rVAlues += co.meanX();
			rVAlues += "\nMedia em Y\n";
			rVAlues += co.meanY();
			rVAlues += "\nUniformidade\n";
			rVAlues += co.uniformity();
			rVAlues += "\nVariancia X\n";
			rVAlues += co.varianceX();
			rVAlues += "\nVariancia Y\n";
			rVAlues += co.varianceY();
			
			rVAlues += "\n\nHistograma\n";
			Histograma hist = new Histograma(image);
			rVAlues += "\nAssimetria\n";
			rVAlues += hist.assimetry();
			rVAlues += "\nMedia\n";
			rVAlues += hist.mean();
			rVAlues += "\nEnergia\n";
			rVAlues += hist.energy();
			rVAlues += "\nEntropia\n";
			rVAlues += hist.entropy();
			rVAlues += "\nMomento\n";
			rVAlues += hist.moment(9);
			
			FileOutputStream fos = new FileOutputStream(new File(outputPath));
			fos.write(rVAlues.getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
