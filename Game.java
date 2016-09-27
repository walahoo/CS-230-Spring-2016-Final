//********************************************************************
// GAME.java 
// Primarily worked on by Renee Huang
// Demonstrates the use of text fields.
//********************************************************************
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import javax.imageio.*;
import java.util.*;
import java.io.*;
import java.util.Vector;
import javax.swing.JComponent;
import javafoundations.exceptions.EmptyCollectionException;

public class Game extends JPanel
{
  private JButton stockPileButton, discardPileButton, gin, knock, newGame, score, deal, newCard;//buttons for all cards
  private JLabel name, scoreLabel; 
  private JPanel top, mid, bot, computer, cardPiles, stockPanel, discardPanel, messagePanel, handPanel, actionPanel;
  private JTextField messageText;
  private  JButton[] cardsButton = new JButton[11];
  private  GinRummy g1 = new GinRummy(2);//will always/only have two players, 1 comp & 1 human
  private int scoreCount = 0; 
  //private Player p1 = new Player("Human");//human player
  
  
  //-----------------------------------------------------------------
  // Constructor: Sets up the main GUI components.
  //-----------------------------------------------------------------
  public Game()
  {
    
    TempListener listener = new TempListener();//listneer
    
    //setting layout
    setLayout (new GridLayout(3,0));
    
    top = new JPanel();
    top.setBackground(new Color(51, 102, 0));
    
    mid = new JPanel();
    mid.setBackground(new Color(51, 102, 0));
    //cardpiles panel going onto mid panel
    cardPiles = new JPanel();
    cardPiles.setBackground(new Color(51, 102, 0));
    
    
    bot = new JPanel();
    
    
    //initializing stock/discard/message pile panels
    stockPanel = new JPanel();
    discardPanel = new JPanel();
    messagePanel = new JPanel();
    
    
    //**mid contents**// (Stock Pile, Discard Pile, and Message Panel)
    //stockpile & discardPile buttons, w/ caption, add to stock/discardPanel
    stockPileButton = new JButton();
    stockPileButton.setEnabled(false);
    //stockPileButton.setDisabledIcon(false);//don't let people click randomly/getlistener
    JLabel stockLabel = new JLabel("Stock Pile",SwingConstants.CENTER);//don't know how to center/not working
    stockPileButton.addActionListener(listener);
    discardPileButton = new JButton();
    discardPileButton.setEnabled(false);
    JLabel discardLabel = new JLabel("Discard Pile", SwingConstants.CENTER);
    discardPileButton.addActionListener(listener);
    //setting image to stockpile and discardPile buttons
    try{
      Image image = ImageIO.read(getClass().getResource("cards.gif"));
      image = image.getScaledInstance(75,75, Image.SCALE_DEFAULT);
      
      stockPileButton.setIcon(new ImageIcon(image));
      discardPileButton.setIcon(new ImageIcon(image));
      
    } catch(IOException e){
    }
    
    
    //add stock pile button & label to stockPanel
    stockPanel.add(stockPileButton);
    stockPanel.add(stockLabel);
    
    //add discard pile button & label to discardPanel
    discardPanel.add(discardPileButton);
    discardPanel.add(discardLabel);
    
    //settting background color to stock/discard panels
    stockPanel.setBackground(new Color(51, 102, 0));
    discardPanel.setBackground(new Color(51, 102, 0));
    
    
    //setting layout for each stock/discard panel
    stockPanel.setLayout(new BoxLayout(stockPanel, BoxLayout.Y_AXIS));
    discardPanel.setLayout(new BoxLayout(discardPanel, BoxLayout.Y_AXIS));
    
    
    messageText = new JTextField("Welcome to Gin Rummy!");
    messageText.setEditable(false);
    messageText.setPreferredSize(new Dimension(550,25));
    messageText.setHorizontalAlignment(JTextField.CENTER);
       //add listener to textfield 
    messagePanel.add(messageText);
    messagePanel.setBackground(new Color(51, 102, 0));
    
    
    
    //**bottom contents**//(Cards Player has in hand, and Gin, Knock, New Game Button)
    //initializing/declaring all buttons
    gin = new JButton("Gin");
    gin.setEnabled(false);
    knock = new JButton("Knock");
    knock.setEnabled(false);
    newGame = new JButton("New Game");
    deal = new JButton("Deal");
    
    scoreLabel = new JLabel ("Deadweight Score: " + scoreCount);
    
    //add buttons to actionPanel, as well as listeners to the buttons
    actionPanel = new JPanel();
    actionPanel.add(deal);
    deal.addActionListener(listener);
    deal.setEnabled(false);//only enabled after clicking new game
    
    actionPanel.add(gin);
    gin.addActionListener(listener);
    actionPanel.add(knock);
    knock.addActionListener(listener);
    actionPanel.add(newGame);
    newGame.addActionListener(listener);
    actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));
    // actionPanel.add(score); 
    actionPanel.add(scoreLabel);
    
    
    handPanel = new JPanel();//panel to hold cards
    handPanel.setLayout(new BoxLayout(handPanel, BoxLayout.X_AXIS));
    
    //adding cards to start game (cards you start w/ originally)
    
    String cardString = "/PMG-cards-1.3";//doesn't work when file placed in phase3 folder, but works on desktop    
    
    
    for (int card = 0; card < 10; card++){ 
     cardsButton[card] = new JButton();
      
      try{//add image to button, back of poker image
      
        cardString = "/pokerback2.jpg";
        Image image = ImageIO.read(getClass().getResource(cardString));
        image = image.getScaledInstance(50,70, Image.SCALE_DEFAULT);
        
        cardsButton[card].setIcon(new ImageIcon(image));
      }catch(IOException e){
      }
      
      cardsButton[card].addActionListener(listener);//add listener to button
      handPanel.add(cardsButton[card]);//add button to hands panel
      
      cardsButton[card].setEnabled(false);//so players can't click
    }
    
    for(int i = 0; i <10; i++){
      cardsButton[i].addActionListener(listener);
    }
    
    //setbackground color to panels in bot panel
    
    handPanel.setBackground(new Color(51,102,0));
    actionPanel.setBackground(new Color(51,102,0));
    
    //make top mid bottom panels align by y axis
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    
    //adding upper most panels to frame
    add(top);
    add(mid);
    add(bot);
    
    //make sure cardpiles are on same x-axis
    cardPiles.setLayout(new BoxLayout(cardPiles, BoxLayout.X_AXIS));
    
    top.add(Box.createRigidArea(new Dimension(0,70)));
    
    //top--> name
    //add name-label (gin rummy) to top panel
    name = new JLabel("Gin Rummy");
    //setting font
    Font font = new Font("Monotype Corsiva", Font.BOLD, 50);
    name.setFont(font);
    name.setHorizontalAlignment(JLabel.CENTER);
    
    name.setSize(400,70);
    
    
    
    top.add(Box.createRigidArea(new Dimension(0,30)));
    top.add(name);
    top.setSize(500,70);
    
    for(int i = 0; i <10; i++){
      cardsButton[i].addActionListener(listener);
    }
    
    //add cardpiles & message panel to mid panel
    mid.add(cardPiles);
    mid.add(Box.createRigidArea(new Dimension(0,30)));
    mid.add(messagePanel);
    
    
    //add stock & discardpanel to cardPiles
    cardPiles.add(stockPanel);
    //add rigid area for spacing
    cardPiles.add(Box.createRigidArea(new Dimension(40,0)));
    cardPiles.add(discardPanel);
    
    
    //panels contained in mid panel align 
    mid.setLayout(new BoxLayout(mid, BoxLayout.Y_AXIS));
    
    
    //panel for computer player
    computer = new JPanel();
    //.add(computer);
    
    
    bot.add(handPanel);
    bot.add(Box.createRigidArea(new Dimension(10,0)));
    
    for(int i = 0; i <10; i++){
      cardsButton[i].addActionListener(listener);
    }
    
    
    
    bot.add(Box.createRigidArea(new Dimension(10,0)));
    bot.add(Box.createRigidArea(new Dimension(0,20)));
    bot.add(actionPanel);
    bot.add(Box.createRigidArea(new Dimension(0,10)));
    
    bot.setBackground(new Color(51,102,0));
    bot.setLayout(new BoxLayout(bot, BoxLayout.Y_AXIS));
    
    
    
    
    setBackground(new Color(51,102,0));
  }
  //*****************************************************************
  // Represents an action listener.
  //*****************************************************************
  private class TempListener implements ActionListener
  {
    
    //--------------------------------------------------------------
    // Performs the conversion when the enter key is pressed in
    // the text field.(need to change to add button)
    //--------------------------------------------------------------
    public void actionPerformed (ActionEvent event) throws EmptyCollectionException
    {
      TempListener listener = new TempListener();
      
      if (event.getSource() == newGame){//determines that new game button is pressed
        //create vector card, and add cards from deck to vector
        
        messageText.setText("Welcome to Gin Rummy!");
        handPanel.removeAll();//hand needs to be cleared for new game
        
        String cardString = "/PNG-cards-1.3/";//doesn't work when file placed in phase3 folder, but works on desktop    
        g1.newGame();
        g1.you.allocateHand();
        Vector<Card> h1 = g1.you.getHand();
        
        
        for (int card = 0; card < 10; card++){
          //make sure handPanel is cleared
          //get card from hand
          //update string of card image
          //add image to button
          //return to loop again  
          
          cardsButton[card] = new JButton();
          
          cardString = cardString +  h1.elementAt(card)  + ".png";
          try{//add image to button
            Image image = ImageIO.read(getClass().getResource(cardString));
            image = image.getScaledInstance(50,70, Image.SCALE_DEFAULT);
            
            cardsButton[card].setIcon(new ImageIcon(image));
          }catch(IOException e){
          }
          handPanel.add(cardsButton[card]);//add button to hands panel
          cardString = "/PNG-cards-1.3/";//reset cardstrings
          
          deal.setEnabled(true);
          gin.setEnabled(true);
          knock.setEnabled(true);
          
          g1.you.setDeadweightScore();
          scoreCount =  g1.you.getDeadweightScore();
          scoreLabel.setText("Deadweight Score: " + scoreCount);
          
        }
        
        
      }
      
      
//if player does not press knock, gin, or stockpile is not empty
      //if(event.getSource()!= knock || event.getSource() != gin || !g1.stockpile.isEmpty()){
      if(!g1.stockpile.isEmpty()){
        
        //deal 
        if (event.getSource() == deal){
          //deal button will setenabled to false 
          String cardString = "/PNG-cards-1.3/";//doesn't work when file placed in phase3 folder, but works on desktop    
          //g1.deal();//so that we can deal cards from it
          
          // g1.you.allocateHand();
          Vector<Card> h1 = g1.you.getHand();
          
          handPanel.removeAll();
          for (int card = 0; card < 10; card++){
            //make sure handPanel is cleared
            //get card from hand
            //update string of card image
            //add image to button
            //return to loop again  
            
            cardsButton[card] = new JButton();
            
            cardString = cardString +  h1.elementAt(card)  + ".png";
            try{//add image to button
              Image image = ImageIO.read(getClass().getResource(cardString));
              image = image.getScaledInstance(50,70, Image.SCALE_DEFAULT);
              
              cardsButton[card].setIcon(new ImageIcon(image));
            }catch(IOException e){
            }
            handPanel.add(cardsButton[card]);//add button to hands panel
            cardString = "/PNG-cards-1.3/";//reset cardstrings
            
            deal.setEnabled(true);
          }
          deal.setEnabled(false);//do not let the user click deal again
          
          g1.computerTurn();//get computer to go, and tell player to click stock or discard pile
          System.out.println("Is game over?" + g1.isGameOver());
          if (g1.isGameOver()) {messageText.setText(g1.getWinner().getName() + " won!");}
          else {
          messageText.setText("Please click which pile you would like your card from (stock or discard)");
          //stockpilebutton & discardPilebutton originally false, set to true so players may press
          stockPileButton.setEnabled(true); 
          discardPileButton.setEnabled(true);  
          gin.setEnabled(true);
          //update score
          
          g1.you.setDeadweightScore();
          scoreCount =  g1.you.getDeadweightScore();
          }
        }
        
        
        if (event.getSource() == stockPileButton){
          stockPileButton.setEnabled(false);discardPileButton.setEnabled(false);//so no multiple clicks
          //set boolean to true so when humanTurn method may work with stockPile 
          
          Card topCard = g1.stockpile.seeTop();
          //g1.you.addToHand(topCard); 
          //set boolean to true so when humanTurn method may work with stockPile 
          g1.choseSPile = true;
          g1.humanTurn();
          if (g1.isGameOver()) messageText.setText(g1.getWinner() + " won!");
          System.out.println("Your hand: " + g1.you.getHand());
          System.out.println("Stock pile after grabbing from it: " + g1.stockpile);
          System.out.println("Is game over? Human: " + g1.isGameOver());
          if (g1.isGameOver()) {
            messageText.setText(g1.getWinner().getName() + " won!");
          }
          else {
          //add image of new card
          String cardString = "/PNG-cards-1.3/";
          
          cardString = cardString +  topCard  + ".png";
          try{//add image to button
            Image image = ImageIO.read(getClass().getResource(cardString));
            image = image.getScaledInstance(50,70, Image.SCALE_DEFAULT);
            
            cardsButton[10] = new JButton("New Card");//add new card caption later
            cardsButton[10].setIcon(new ImageIcon(image));
          }catch(IOException e){
          }
          handPanel.add(cardsButton[10]);//add button to hands panel
          cardString = "/PNG-cards-1.3/";//reset cardstrings
          System.out.println("Is game over?" + g1.isGameOver());
          if (g1.isGameOver()) messageText.setText(g1.getWinner() + " won!");
          //instructions/swap card
          messageText.setText("Please choose the card to change with the new card (" + topCard.stringCardID() + ").");
          
          cardsButton[10].setEnabled(false);
          //make all buttons enabled (true)
          for(int i = 0; i < 10; i++){
            cardsButton[i].setEnabled(true);
            cardsButton[i].addActionListener(listener);
          }
          
          
        }
        }
        if (event.getSource() == discardPileButton){
          stockPileButton.setEnabled(false);discardPileButton.setEnabled(false);
          try{
            Card topCard = g1.discard.seeTop();
            
            //g1.you.addToHand(topCard);
            
            //set boolean to true so when humanTurn method may work with discardPile 
            
            g1.choseDPile = true;
            g1.humanTurn();//gets card from discard or stock pile & adds it to human's hand
            if (g1.isGameOver()) {
              messageText.setText(g1.getWinner().getName() + " won!");
            }
            else {
            //add image of new card to hands panel
            String cardString = "/PNG-cards-1.3/";
            
            cardString = cardString +  topCard  + ".png";
            try{//add image to button
              Image image = ImageIO.read(getClass().getResource(cardString));
              image = image.getScaledInstance(50,70, Image.SCALE_DEFAULT);
              
              cardsButton[10] = new JButton("New Card");//add new card caption later
              cardsButton[10].setIcon(new ImageIcon(image));
            }catch(IOException e){
            }
            handPanel.add(cardsButton[10]);//add button to hands panel
            cardString = "/PNG-cards-1.3/";//reset cardstrings
            
            //ask player which card they would like to discard
            String cardStringName =  topCard.stringCardID();
            messageText.setText("Please choose card to swap with: " + cardStringName);
            //make new card false (not enabled) so players can't click on it
            cardsButton[10].setEnabled(false);
            //make all othfer buttons enabled (true)
            for(int i = 0; i < 10; i++){
              cardsButton[i].setEnabled(true);
              cardsButton[i].addActionListener(listener);
            }
          }
          }catch(EmptyCollectionException e){
            messageText.setText("Discard pile is empty, please start new game or click stock pile.");
            deal.setEnabled(false);
            discardPileButton.setEnabled(false); 
            stockPileButton.setEnabled(true);
          }
        }
        
        
        
        
        for (int i=0; i<10; i++) {
          
          /*if you click on a certain card (only can click after getting new card (enabled(true)), 
           * then you need to discard. Otherwise, you don't. Furthermore,*/
          
          
          if (event.getSource()==cardsButton[i]) {
            //card you clicked to remove is at index i 
            Vector<Card> hand = g1.you.getHand();
            //card clicked to indicate one the player would like to discard
            Card unwantedCard = g1.you.getHand().get(i);
            
            String cardString = "/PNG-cards-1.3/";
            handPanel.removeAll();//hand needs to be cleared for new updated hand
            
            //i'm inserting before i remove the card 
            //change to card value/id
            String removeCardID = g1.you.getHand().get(i).stringCardID();
            messageText.setText("Discarded card: " + removeCardID);
            
            //new card you received will be as last element of vector
            //Card newCard = hand.elementAt(10);
            //discard the card with that index and add to discard pile
            //g1.discard.addCard(unwantedCard);
            g1.you.getHand().remove(unwantedCard);
            System.out.println("Removed the unwanted card: " + unwantedCard + " from your hand.");
            g1.discard.addCard(unwantedCard);
            System.out.println("Added the unwanted card: " + unwantedCard + " to the discard pile.");
            System.out.println("Your hand after removing the unwanted card: " + g1.you.getHand());
            
            System.out.println("Discard pile after adding unwanted card to the discard pile: " + g1.discard);
            //update hand (swapping new card w/ selected card and update hand panel          
            //g1.you.getHand().remove(10);
            //g1.you.getHand().setElementAt(newCard,i);
            g1.you.allocateHand();
            g1.you.setDeadweightScore();
            Vector<Card> h1 = g1.you.getHand();
            
            for (int card = 0; card < 10; card++){
              //make sure handPanel is cleared
              //get card from hand
              //update string of card image
              //add image to button
              //return to loop again  
              
              cardsButton[card] = new JButton();
              
              cardString = cardString +  h1.elementAt(card)  + ".png";
              try{//add image to button
                Image image = ImageIO.read(getClass().getResource(cardString));
                image = image.getScaledInstance(50,70, Image.SCALE_DEFAULT);
                
                cardsButton[card].setIcon(new ImageIcon(image));
              }catch(IOException e){
              }
              handPanel.add(cardsButton[card]);//add button to hands panel
              cardString = "/PNG-cards-1.3/";//reset cardstrings
              
            }
            
            //update score: 
            g1.you.setDeadweightScore();
            scoreCount =  g1.you.getDeadweightScore();
            scoreLabel.setText("Deadweight Score: " + scoreCount);
            deal.setEnabled(true);
          }
        }
        
        
        if (event.getSource() == gin){
          if (g1.you.hasGin()){
            messageText.setText("Congratulations! You win!");
          }
          else{//if player doesn't have gin and wrongly presses it... tell them
            messageText.setText("You don't have Gin...You drinking coffee or something?!?");
          }
        }
        
        
        if (event.getSource() == knock){
          if (g1.you.hasKnock()){//check if hand player has has less than ten deadweight score
            g1.knock(g1.you);//call knock method, which gets computer player to add any deadweight cards to player's hand
            if (g1.you.getName().equals(g1.getWinner().getName())){
              messageText.setText("Congratulations! You win.");
            }else{//winner is not you :( 
              messageText.setText("Sorry, computer player won.");
            }
          }
          else{//if player doesn't have gin and wrongly presses it... tell them
            messageText.setText("You don't have less than 10 deadweight points, so you can't knock!");
          }
        }
      }
    }
  }
}












