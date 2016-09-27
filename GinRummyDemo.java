/*GinRummyDemo.java
 * Primarily authored by Renee Huang 
 * 
 */

import javax.swing.*; 

public class GinRummyDemo{

  
  public static void main (String[] args){
    
    JFrame frame = new JFrame ("Gin Rummy");        
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    
    JTabbedPane tp = new JTabbedPane();  
    tp.addTab("About/Instructions", new AboutPanel());
    tp.addTab ("Game", new Game());
  

    frame.getContentPane().add(tp);
    
    frame.pack();
    frame.setVisible(true);
  }
}