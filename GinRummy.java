/*CS230 Project
 * Authors: Olivia Ondeck, Renee Huang, Ngina Kariuki 
 * GinRummy.java
 * Plays the actual Gin Rummy game
 * */

import java.util.*;
import javafoundations.*;

public class GinRummy {
  
  private int numPlayers, disCard;
  protected boolean gameOver, choseDPile, choseSPile, hitKnock, calledGin, blockKnock;
  private BoundedQueue<Player> players;
  private Player p, winner;//might need to change access
  public Player you;
  
  protected Deck discard;
  protected Deck stockpile; 
  
  
  public GinRummy (int n) {
    
    numPlayers = n; 
    players = new BoundedQueue<Player>(numPlayers);
    
    
    
    for (int i=0; i<(numPlayers-1); i++) {
      p = new Player("Computer");
      players.enqueue(p);
    }
    
    you = new Player("Human");
    players.enqueue(you);
    
    choseDPile=false;
    boolean choseSPile=false;
    int disCard = -1;
    boolean hitKnock=false;
    boolean calledGin=false;
    boolean blockKnock=false;
    boolean gameOver=false;
    
  }
  
  /*getters and setters for 
   * players (player queue)
   * choseDPile
   * choseSPile
   * hitKnock
   * calledGin
   * blockKnock
   * disCard
   * winner
   * */
  
  public BoundedQueue<Player> getPlayerQueue() {
    return players;
  }
  
  public boolean isGameOver() {
    return gameOver;
  }
  
  public Player getWinner() {
    return winner;
  }
  
  public boolean getChoseDPile() {
    return choseDPile;
  }
  
  public void setChoseDPile() {
    choseDPile=true;
  }
  
  public boolean getChoseSPile() {
    return choseSPile;
  }
  
  public void setChoseSPile() {
    choseSPile=true;
  }
  
  public boolean getHitKnock() {
    return hitKnock;
  }
  
  public void setHitKnock() {
    hitKnock=true;
  }
  
  public boolean getCalledGin() {
    return calledGin;
  }
  
  public void setCalledGin() {
    calledGin=true;
  } 
  
  public boolean getBlockKnock() {
    return blockKnock;
  }
  
  public void setBlockKnock() {
    blockKnock=true;
  }
  
  public int getDisCard() {
    return disCard;
  }
  
  public void setDisCard(int n) {
    disCard=n;
  }
  
  public void setNewStockpile(){
    Deck d = new Deck();
    stockpile = d.randomShuffle();
  }
  
  /******************************************************************
     Sets up a new game, deals out cards and sets up hands for 
     the players 
     @param  none
     @return none
    *****************************************************************/
  public void newGame(){
    Deck d = new Deck();//create new deck to be shuffled
    d = d.randomShuffle();
    for (int i=0; i<players.size(); i++) {
      Player p = players.dequeue();
      Vector<Card> h = new Vector<Card>();
      for (int j=0; j<10; j++) {//add cards from shuffled deck to hand
        h.add(d.getTop());
      }//set hand to player
      p.setHand(h);
      System.out.println("Player: " + i + ", Hand: " + p.getHand());
      players.enqueue(p);
      
    }
    //System.out.println("Stockpile size: " + (d.size()-1));
    //System.out.println("Discard pile size: " + "1");
    discard = new Deck(0);//discard pile takes first off of stockpile (after giving cards to player)
    discard.addCard(d.getTop());
    stockpile = d; //set deck to stockpile (remaining cards)
    System.out.println("DISCARD: " + discard);
    System.out.println("STOCKPILE: " + stockpile);
    //System.out.println("Player queue: " + players);
  }
  
  
  /******************************************************************
     Deals discard pile and stock pile 
     @param  none
     @return none
    *****************************************************************/
  public void deal() {
    for (int i=0; i<players.size(); i++) {
      Player p = players.dequeue();
      Vector<Card> h = new Vector<Card>();
      //for (int j=0; j<10; j++) {
      //  h.add(stockpile.getTop());
      // }
      //p.setHand(h);
      // System.out.println("Player: " + i + ", Hand: " + p.getHand());
      // players.enqueue(p);
    }
    //System.out.println("Stockpile size: " + (d.size()-1));
    //System.out.println("Discard pile size: " + "1");
    discard = new Deck(0);
    discard.addCard(stockpile.getTop());
    //stockpile = d; 
    System.out.println("DISCARD: " + discard);
    System.out.println("STOCKPILE: " + stockpile);
    //System.out.println("Player queue: " + players);
  }
  
  /******************************************************************
     Called when stockpile is empty. Decides winner based on which player
     has the lowest score. 
     @param  none
     @return none
    *****************************************************************/
  public void determineWinner () {
    Vector<Player> rankedPlayers = new Vector<Player>();
    
    while (!players.isEmpty()) {
      Player p1 = players.dequeue();
      p1.allocateHand();
      p1.setDeadweightScore();
      rankedPlayers.add(p1);
    }
    Collections.sort(rankedPlayers);
    rankedPlayers.get(0).addWin();
    winner=rankedPlayers.get(0);
    gameOver=true;
    rankedPlayers.get(0).setWin();
    System.out.println("Winner variable: " + winner);
    System.out.println(rankedPlayers.get(0).getName() + " won!");
    
    
  }
  
  /******************************************************************
    A card is randomly drawn from the discard pile or the stock pile
    and added to the computer player's hand. A card in the computer's 
    hand that is considered a 'deadweight card' is added to the 
    discard pile. 
    @param  none
    @return none
    *****************************************************************/
  public void computerTurn() {
    System.out.println(players.first().getType());
    
    if (players.first().getType().equals("human")) 
      return;
    
    p = players.dequeue();
    
    Random rand = new Random();
    int r = rand.nextInt(2);
    if (r==0) 
    {
      Card topCard = discard.getTop();
      p.addToHand(topCard);
    }
    else if (r==1){
      Card topCard = stockpile.getTop();
      p.addToHand(topCard);
    }
    p.setDeadweightScore();
    
    Vector<Card> hand = p.getHand();
    
    //index of card to remove
    int unwantedInt = p.getMaxDWInt();
    
    //card to remove which is at the specified index in the vector 
    Card unwantedCard = p.getHand().get(unwantedInt);
    
    //new card just drawn
    Card newCard = hand.lastElement();
    
    //remove unwanted card from hand 
    p.getHand().remove(unwantedCard);
    
    //add removed card to the discard pile 
    discard.addCard(unwantedCard);
   
    System.out.println("Unwanted card: " + unwantedCard);
   
    p.allocateHand();
    
    
    if (p.getDeadweightScore()==0) {
      winner=p;
      gameOver=true;
      p.setWin();
      p.addWin();
    }
    if (stockpile.isEmpty()) determineWinner();
    
    players.enqueue(p);//think I added this
    
   }
  
  
  /******************************************************************
    Human player selects whether they want to draw from the draw
    pile or discard pile. A card from the pile chosen is added to 
    the human player's hand. Human player then selects which card to 
    discard. 
    @param  none
    @return none
    *****************************************************************/
  public void humanTurn() {
  //gets card from discard or stock pile
    
    if (players.first().getType().equals("computer")) return;
    
    you = players.dequeue();
    //get card from pile
    if (choseDPile) you.addToHand(discard.getTop());
    if (choseSPile) you.addToHand(stockpile.getTop());
    
    //you.setDeadweightScore();    
    
    if (stockpile.isEmpty()) determineWinner();
    players.enqueue(you);
    
  }
  
  /******************************************************************
    Sets the input player's score. 
    Looks at the deadweight cards of the other players.
    For each set of deadweight cards, checks isRun and isSet 
    against the input player's sets and runs, and adds the cards to 
    the sets and runs in the player's hand.
    Records the score for each player. 
    Player with lowest score nwins incremented by 1. 
    gameOver set to true, so the game ends. 
    @param  Player p, the one knocking 
    @return none
    *****************************************************************/
  public void knock (Player p1) {
    /* Until the player queue is empty, dequeue each player. Each time a 
     * player is dequeued, get their deadweights (a vector). Add them to p's
     * hand. Set p's deadweight score. Recalculate player's score. 
     */
    Vector<Player> rankedPlayers = new Vector<Player>();
    Vector<Card> setsOrRuns = new Vector<Card>();
    int finalScore = p.getDeadweightScore();
    p1.setFinalDeadweightScore(finalScore);
    rankedPlayers.add(p1);
    //System.out.println("Player p's deadweight score: this is FINAL. " + finalScore);
    
    
    
    for (int ind=0; ind<p.getHand().size(); ind++) {
      if (p1.getHand().get(ind).inRun() || p1.getHand().get(ind).inSet()) setsOrRuns.add(p1.getHand().get(ind));
    }
    
    //Player p is done and their final score known. Now we can add the cards of other players to 
    //their sets and runs. 
    
    while (!players.isEmpty()) {
      
      p1.setHand(setsOrRuns);
      //System.out.println("p's cards in sets or runs: " + setsOrRuns);
      
      
      
      Player opponent = players.dequeue();
      opponent.allocateHand();
      opponent.setDeadweightScore();
      
      //System.out.println("Opponent's deadweight score: " + opponent.getDeadweightScore());
      opponent.setDeadweights();
      Vector<Card> deads = opponent.getDeadweights();
      for (int i=0; i<deads.size(); i++) {
        p1.addToHand(deads.get(i));
      }
      p1.allocateHand();
      p1.setDeadweightScore();
      opponent.setDeadweightScore();
      //System.out.println("Player p's deadweight score after knock: " + p.getDeadweightScore());
      System.out.println("Opponent's deadweight score after knock: " + opponent.getDeadweightScore());
      rankedPlayers.add(opponent);
    }
    
    
    
    Collections.sort(rankedPlayers);
    
    rankedPlayers.get(0).addWin();
    System.out.println(rankedPlayers.get(0) + " won!");
    
    
    gameOver=true;
  }
  
  /******************************************************************
    Starts one game until gameOver is true.
    Uses a queue of players to switch turns.
    Ends game while playAgain is true.
    @param  none
    @return none
    *****************************************************************/
  public void playGame () {
    deal();
    while (!gameOver) {
      if (players.first().getType().equals("human")) humanTurn();
      else computerTurn();
    }
    
  }
  

  //testing method 
  public static void main (String[] args) {
    GinRummy g1 = new GinRummy(2);
    
    System.out.println (g1.players.size());
    Player p1 = new Player("Human");
    System.out.println(g1.players);
    
    
    
    
    
  }
  
  
  
 }