import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;

public class Equalight {
   
   public static void main(String[] args) throws IOException {
	   String dirPath = System.getProperty("user.dir") + "/";
	   if (args.length > 0) {
		   dirPath += args[0];
	   }
	   System.out.println("Creating modified images for images in directory: " + dirPath);
	   HashMap<String, BufferedImage> images = getAllImagesInDirectory(dirPath);
	   Iterator it = images.entrySet().iterator();
	   if (!new File(dirPath + "-mod").exists()) {
		   new File(dirPath + "-mod").mkdir();
	   }
	   while(it.hasNext()) {
		   Map.Entry pair = (Map.Entry)it.next();
		   modifyImageWithColor((BufferedImage)pair.getValue(), Color.red,dirPath + "-mod/" + (String)pair.getKey());
		   System.out.println("Created modified image for: " + pair.getKey());
	   }
   }
   
   public static HashMap<String, BufferedImage> getAllImagesInDirectory (String path) {
	   File directory = new File(path);
	   File[] listOfFiles = directory.listFiles();
	   HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();

       for (int i = 0; i < listOfFiles.length; i++) {
         if (listOfFiles[i].isFile()) {
           try {
			images.put(listOfFiles[i].getName(), ImageIO.read(new File(path + "/" + listOfFiles[i].getName())));
			} catch (IOException e) {
				System.out.println("Could not load file: " + listOfFiles[i].getName());
			}
         }
       }
	   return images;
   }
   

   public static BufferedImage modifyImageWithColor(BufferedImage image, Color c, String path) {
      int width = image.getWidth();
      int height = image.getHeight();
      
      //get image info
      double r = 0, g = 0, b = 0;
      for (int x = 0; x < width; x++) {
         for (int y = 0; y < height; y++) {
            int pix = image.getRGB(x, y);
            Color col = new Color(pix);
            
            r += col.getRed();
            g += col.getGreen();
            b += col.getBlue();
         }
      }
      r /= (width * height);
      g /= (width * height);
      b /= (width * height);
      
      double red = c.getRed(), green = c.getGreen(), blue = c.getBlue();
      double rgb = (r + g + b) / 3.0;
      r = g = b = rgb;
      if (c.getAlpha() > 0) {
         red = green = blue = c.getAlpha();
      }
      double rr, gg, bb;
      for (int x = 0; x < width; x++) {
         for (int y = 0; y < height; y++) {
            int pix = image.getRGB(x, y);
            Color col = new Color(pix);
            rr = col.getRed() * red / rgb;
            gg = col.getGreen() * green / rgb;
            bb = col.getBlue() * blue / rgb;
            col = new Color(Math.min(Math.max((int)rr, 0), 255), Math.min(Math.max((int)gg, 0), 255), Math.min(Math.max((int)bb, 0), 255));
            
            image.setRGB(x, y, col.getRGB());
         }
      }
      
      try {
         File outputfile = new File(path + ".jpg");
         ImageIO.write(image, "jpg", outputfile);
      } catch (IOException e) {
         e.printStackTrace();
      }
      
      return image;
   }
   
}
