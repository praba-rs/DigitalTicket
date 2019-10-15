import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Poster {

	public void imageProcessAppend() throws IOException {

		String posterPath = "C:\\Data\\poster\\";

		String inputpath = posterPath + "input\\";
		String headerfooterpath = posterPath + "headerfooter\\";

		String outputpath = posterPath + "output\\";

		File folder = new File(inputpath);
		File[] listOfFiles = folder.listFiles();
		Integer ctr = 0;
		for (File file : listOfFiles) {
			if (file.isFile()) {

				System.out.println("working on " + file.getName());
				System.out.println(++ctr);
				BufferedImage posterimg = ImageIO.read(new File(inputpath + file.getName()));
				BufferedImage headerimg = ImageIO.read(new File(headerfooterpath + "header.png"));
				BufferedImage footerimg = ImageIO.read(new File(headerfooterpath + "footer.png"));
				BufferedImage joinedImg = joinBufferedImage(headerimg, posterimg, footerimg);
				ImageIO.write(joinedImg, "jpg", new File(outputpath + getBaseName( file.getName()) + ".jpg"));
			}
		}

	}

	public static String getBaseName(String name) {
		int pos = name.lastIndexOf(".");
		return pos > 0 ? name.substring(0, pos) : name;
	}

	public static BufferedImage joinBufferedImage(BufferedImage header, BufferedImage poster, BufferedImage footer) {
		int offset = 2;
		int width = Math.max(header.getWidth(), poster.getWidth()) + offset;
		int height = header.getHeight() + poster.getHeight() + footer.getHeight() + offset;
		BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = newImage.createGraphics();
		Color oldColor = g2.getColor();
		g2.setPaint(Color.WHITE);
		g2.fillRect(0, 0, width, height);
		g2.setColor(oldColor);
		g2.drawImage(header, null, 0, 0);
		g2.drawImage(poster, null, 0, header.getHeight());
		g2.drawImage(footer, null, 0, header.getHeight() + poster.getHeight());
		g2.dispose();
		
		
		int w = newImage.getWidth();
		int h = newImage.getHeight();
		BufferedImage newImagergb = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		int[] rgb = newImage.getRGB(0, 0, w, h, null, 0, w);
		newImagergb.setRGB(0, 0, w, h, rgb, 0, w);
		
		
		return newImagergb;
	}
}
