package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import UI.App;
import UI.Screen;


public class Input implements KeyListener, MouseListener, MouseMotionListener{
   
   public App app;
   
   public Input(App app){
      this.app = app;
   }
   
   // *** MOUSE MOVEMENT *** //
   
   @Override
   public void mouseDragged(MouseEvent e) {
      int x = e.getX();
      int y = e.getY();
   }

   @Override
   public void mouseMoved(MouseEvent e) {
      
   }

   @Override
   public void mouseEntered(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseExited(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }
   
   // *** MOUSE CLICKS *** //

   @Override
   public void mouseClicked(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mousePressed(MouseEvent e) {
      int x = e.getX();
      int y = e.getY();
   }

   @Override
   public void mouseReleased(MouseEvent e) {
      
   }
   
   // *** KEYS *** //

   @Override
   public void keyPressed(KeyEvent e) {
      int key = e.getKeyCode();
      char content = e.getKeyChar();
      if (key == KeyEvent.VK_LEFT){
         Screen.x += 5;
      }if (key == KeyEvent.VK_RIGHT){
         Screen.x -= 5;
      }if (key == KeyEvent.VK_DOWN){
         Screen.y += 5;
      }if (key == KeyEvent.VK_UP){
         Screen.y -= 5;
      }
   }

   @Override
   public void keyReleased(KeyEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void keyTyped(KeyEvent e) {
      
   }
   
   
   
}
