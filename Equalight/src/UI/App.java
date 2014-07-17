package UI;


import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JFrame;

import Input.Input;



public class App extends Applet {
   
   private static final long serialVersionUID = 1L;
   
   public static final int WIDTH = 1000;
   public static final int HEIGHT = 700;
   
   public Screen screen;
   public Input input;
   
   public App(){
      screen = new Screen(this);
   }
   
   public void init() {
      setSize(WIDTH, HEIGHT);
      setLayout(new BorderLayout());
      try {
         setImages();
      } catch (IOException e) {
         e.printStackTrace();
      }
      add(screen, BorderLayout.CENTER);
      
      //Input
      input = new Input(this);
      addMouseListener(input);
      addKeyListener(input);
      addMouseMotionListener(input);
      screen.addMouseListener(input);
      screen.addKeyListener(input);
      screen.addMouseMotionListener(input);
   }
   
   public void setImages() throws IOException{
      Screen.BACKGROUND = getImage(getCodeBase(), "img/4.jpg");
   }
   
   public static void main(String[] args) throws IOException{
      App game = new App();
      JFrame window = new JFrame("Character Sheets");
      
      game.setPreferredSize(new Dimension(App.WIDTH, App.HEIGHT));
      game.setMinimumSize(new Dimension(App.WIDTH, App.HEIGHT));
      game.setMaximumSize(new Dimension(App.WIDTH, App.HEIGHT));
      
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.setLayout(new BorderLayout());
      window.add(game);
      window.pack();
      window.setResizable(false);
      window.setLocationRelativeTo(null);
      window.setVisible(true);
      
      game.init();
      
   }
   
}
