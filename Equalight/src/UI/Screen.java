package UI;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Screen extends Canvas {

   private static final long serialVersionUID = 1L;
   
   public static final Color BACK = new Color(140, 10, 10, 150);
   public static Image BACKGROUND;
   
   public static final int MAX_WIDTH = 4000;
   public static final int MAX_HEIGHT = 4000;
   public static int x = 0;
   public static int y = 0;
   
   //For double buffering
   public static BufferedImage image = new BufferedImage(MAX_WIDTH, MAX_HEIGHT, BufferedImage.TYPE_INT_RGB);
   
   public App app;
   
   public Screen(App app){
      this.app = app;
   }
   
   public void paint(Graphics g){
      //Graphics gr = image.getGraphics();
      //render(gr);
      image = set(BACK);
      if (image != null) {
         g.drawImage(image, 0, 0, null);
      }
   }
   
   public void render(Graphics g){
      g.setColor(BACK);
      g.fillRect(0, 0, App.WIDTH, App.HEIGHT);
      g.drawImage(BACKGROUND, 0, 0, this);
   }
   
   public BufferedImage set(Color c) {
      int width = BACKGROUND.getWidth(this);
      int height = BACKGROUND.getHeight(this);
      if (width < 0 || height < 0) {
         return null;
      }
      BufferedImage redo = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      Graphics canvas = redo.getGraphics();
      canvas.drawImage(BACKGROUND, 0, 0, this);
      
      //get image info
      double r = 0, g = 0, b = 0;
      for (int x = 0; x < width; x++) {
         for (int y = 0; y < height; y++) {
            int pix = redo.getRGB(x, y);
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
            int pix = redo.getRGB(x, y);
            Color col = new Color(pix);
            rr = col.getRed() * red / rgb;
            gg = col.getGreen() * green / rgb;
            bb = col.getBlue() * blue / rgb;
            col = new Color(Math.min(Math.max((int)rr, 0), 255), Math.min(Math.max((int)gg, 0), 255), Math.min(Math.max((int)bb, 0), 255));
            
            redo.setRGB(x, y, col.getRGB());
         }
      }
      
      try {
         File outputfile = new File("output/out.jpg");
         ImageIO.write(redo, "jpg", outputfile);
      } catch (IOException e) {
         e.printStackTrace();
      }
      
      return redo;
   }
   
}
