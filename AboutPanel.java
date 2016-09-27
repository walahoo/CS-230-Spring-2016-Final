/* Olivia Ondeck, Renee Huang and Ngina Kariuki
 * Primarily done by Renee H & Ngina Kariuki
 * Gin Rummy GUI: AboutPanel.java
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;

public class AboutPanel extends JPanel {
  //instance variables
  private JTextArea textArea, aboutText;
  private final static String newline = "\n";
  private JLabel objTitle, aboutTitle;
  private JPanel objPanel, aboutPanel; 
  
  public AboutPanel() 
  {
    objTitle = new JLabel("Objective");
    aboutTitle = new JLabel("About");
    objTitle.setAlignmentX(CENTER_ALIGNMENT);aboutTitle.setAlignmentX(CENTER_ALIGNMENT);
    
    Border paneEdge = BorderFactory.createEmptyBorder(0,10,10,10);
       Border blackline, raisedetched, loweredetched,
               raisedbevel, loweredbevel, empty;
    
    objPanel = new JPanel();
    objPanel.setBorder(paneEdge);
    objPanel.setLayout(new BoxLayout(objPanel, BoxLayout.Y_AXIS));
    //addCompForBorder(blackline, "line border", simpleBorders);
    
    aboutPanel = new JPanel();
    aboutPanel.setBorder(paneEdge);
    aboutPanel.setLayout(new BoxLayout(aboutPanel, BoxLayout.Y_AXIS));
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


    String text = "\tThe objective in gin rummy is to have the lowest deadweight score." + newline + newline +
      "The basic game strategy is to improve one's hand by forming melds and eliminating deadweight." + 
      newline + newline + "Melds are: sets of 3 or 4 cards sharing the same rank (ex. 3, 3, 3) and runs are 3 or more cards of the same suit.(ex. 3 spades, 4 spades, 5 spades)" + newline + newline + 
      "\tAt each round, you can choose to pick up a card from the Discard Pile or the Stock Pile and then discard a card to the Discard Pile." 
      + newline + newline + "\tThe remaining cards in your hand that are not part of the meld are deadweight cards and they each add up up tp hte deadweight score." +
      newline + newline+ "\tThe goal of the game is to reduce your deadweight score." + newline + newline + 
      newline + "\tThe game is over when the stock pile is empty, or either you or the computer calls gin or knock." 
      + newline + "\tGin is when a player calls gin if their hand consists only of sets and runs (i.e. deadweight score = 0). A card can NOT count for both a set and a run." + newline
      + newline + newline + "\tKnocking is when Knocking: A player can knock if they have 10 points or fewer in deadweight cards! When a player knocks, the player’s opponent can add their own deadweight cards to" + 
      " the sets and runs of the player who knocked. Whoever has a lower score wins." ;
       
    String about = "\tGin rummy, or simply gin, is a two-player card game created in 1909 by Elwood T. Baker and his son" + 
     " C. Graham Baker.[1] According to John Scarne, Gin evolved from 19th-century Whiskey Poker and was created with the intention of" + 
      " being faster than standard rummy but less spontaneous than knock rummy.";
    
    aboutText =  new JTextArea(about);
    textArea = new JTextArea(text);
    textArea.setFont(new Font("Serif", Font.ITALIC, 16));
    aboutText.setFont(new Font("Serif", Font.ITALIC, 16));
    textArea.setForeground(new Color(224,221,11));
    aboutText.setForeground(new Color(224,221,11));
    textArea.setLineWrap(true); aboutText.setLineWrap(true);
    textArea.setWrapStyleWord(true);aboutText.setWrapStyleWord(true);
    //textArea.setPreferredSize(new Dimension(100, 100));
   // JScrollPane scrollPane = new JScrollPane(objPanel); 
   // scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    textArea.setEditable(false);
    textArea.setBackground(new Color(51,102,0)); aboutText.setBackground(new Color(51,102,0));
    
    textArea.setOpaque(true);
    
    
    objTitle.setForeground(new Color(255, 102, 0));
    
    //add title
    objPanel.add(objTitle);
    //add text & spacing
    objPanel.add(textArea);

    //add scrollpane to objPanel
    
    //add about title & set color
    aboutPanel.add(aboutTitle);
    aboutTitle.setForeground(new Color(255, 102, 0));
    aboutPanel.add(aboutText);
    aboutPanel.setLayout(new BoxLayout(aboutPanel, BoxLayout.Y_AXIS));

    add(aboutPanel);
    
    
    objPanel.setLayout(new BoxLayout(objPanel, BoxLayout.Y_AXIS));

    add(objPanel);
    
    Font font = new Font("Monotype Corsiva", Font.BOLD, 40);
    objTitle.setFont(font); aboutTitle.setFont(font); 
    objTitle.setHorizontalAlignment(JLabel.CENTER);aboutTitle.setHorizontalAlignment(JLabel.CENTER);

    
    //obj.setBackground(new Color(51,102,0));
    objPanel.setBackground(new Color(51,102,0)); aboutPanel.setBackground(new Color(51,102,0));
    setBackground(new Color(51,102,0));
   
   

  }
}