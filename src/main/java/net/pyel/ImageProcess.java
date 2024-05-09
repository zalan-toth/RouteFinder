package net.pyel;

import javafx.scene.image.Image;
import net.pyel.models.Coordinate;

public class ImageProcess {

	public static void processMe(Coordinate[] coordinates, Image image) {

		/*for (int v = 0; v < coordinates.length; v++) {
			int currentX = coordinates[v].getX();
			int currentY = coordinates[v].getY();
			if ()
		}
		WritableImage gray = new WritableImage(width, height);
		PixelReader pr = image.getPixelReader();
		PixelWriter pwGray = gray.getPixelWriter();
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				int v = pr.getArgb(x, y);
				int a = (v >> 24) & 0xFF;
				int r = (v >> 16) & 0xFF;
				int g = (v >> 8) & 0xFF;
				int b = v & 0xFF;
				int avg = (r + g + b) / 3;
				//System.out.println(a + " " + r + " " + g + " " + b);
				pwGray.setArgb(x, y, (a << 24 | avg << 16 | avg << 8 | avg));
				pwRed.setArgb(x, y, (a << 24 | r << 16));
				pwGreen.setArgb(x, y, (a << 24 | g << 8));
				pwBlue.setArgb(x, y, (a << 24 | b));
				redFrequency[r]++;
				greenFrequency[g]++;
				blueFrequency[b]++;
				//System.out.println(redFrequency[a]);
			}
		}*/

	}
}
