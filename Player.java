/* CS230 Project
 * Player.java
 * Primarily authored by Olivia Ondeck
 * Represents a player in a game
 * A player has a name, hand, type, score, number of wins
 * */
import java.util.*;

public class Player implements Comparable<Player> {
  
  private String name;
  private Vector<Card> hand;
  private Vector<Card> dummyHand;
  private Vector<Card> deadweights;
  private String type;
  private int[] valCounts = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  private boolean taken, won;
  private int deadweightScore;
  private int score;
  private int nwins;
  
  /**
 * Constructor: Creates a player of the given type 
 * @param  String type 
 */
  public Player (String t) {
    type=t;
    if (type.toLowerCase().equals("human")) {name="You";}
    else {name="Computer";}
    
    
    hand = new Vector<Card>();
    deadweights = new Vector<Card>();
    deadweightScore=0;
    score = 0;
    nwins = 0;
    won=false;
    
  }
  
    /**
 * Getters and setters for:
 * name, hand, type, final deadweight score,
 * deadweight score, wins, deadweights 
 */
  public String getName() {
    return name;
  }
  
  public void setName(String n) {
    name=n;
  }
  
  public void setWin() {
    won=true;
  }
  
  public boolean won() {
    return won;
  }
  
  public Vector<Card> getHand() {
    return hand;
  }
  
  public void setHand (Vector<Card> h) {
    hand = h;
    
  }
  
  public void addToHand(Card c) {
    hand.add(c);
  }
  
  public String getType() {
    return type;
  }
  
  public void setType(String t) {
    type = t;
  }
  
  public int getScore() {
    return score;
  }
  
  public void setFinalDeadweightScore(int s) {
    deadweightScore=s;
  }
  
  
  public void setDeadweightScore() {
    deadweightScore=0;
    //allocateHand();
    
    for (int i=0; i<hand.size(); i++) {
      if (! (hand.get(i).inSet() || hand.get(i).inRun())) {
        deadweightScore += (hand.get(i).getValueIndex() + 2);
      }
    }
    
    
  }
  
  public int getDeadweightScore() {
    return deadweightScore;
  }
  
  public int getWins() {
    return nwins;
  }
  
  public void addWin() {
    nwins++;
  }
  
  public void setDeadweights () {
    //allocateHand();
    //System.out.println("DEADWEIGHTS: " + deadweights);
    //for (int j=0; j<hand.size(); j++) System.out.println(hand.get(j) + "in run?: " + hand.get(j).inRun() + ", " + hand.get(j) + "in set?: " + hand.get(j).inSet());
    for (int i=0; i<hand.size(); i++) {
      //System.out.println("In run?: " + hand.get(i).inRun());
      //System.out.println("In set?: " + hand.get(i).inSet());
      
      
      if (! (hand.get(i).inRun() || hand.get(i).inSet())) 
        deadweights.add(hand.get(i));
      
      
    }
    
  }
  
  public Vector<Card> getDeadweights () {
    return deadweights;
  }
  
   /**
 * compareTo: returns -1 if this player's deadweight score is 
 * less than the other player's deadweight score, 1 if it is larger and 0 if
 * they are equal 
 * @param  Player other 
 * @return      one of three integer values 
 */
  public int compareTo (Player other) {
    
    if (deadweightScore < other.deadweightScore) return -1;
    if (deadweightScore > other.deadweightScore) return 1;
    else return 0;
    
  }
  
  /**
 * gets index of max deadweight card in hand
 * @param  none
 * @return int index 
 */
   public int getMaxDWInt () {
  
   setDeadweights();
   Card maxVal = deadweights.get(0);
   int maxIndex = 0;
   for (int j=1; j<deadweights.size(); j++) {
   if (deadweights.get(j).getValueIndex() > maxVal.getValueIndex()) maxVal = deadweights.get(j);
   }
   
   for (int i = 0; i < hand.size(); i++){
   if (hand.elementAt(i) == maxVal) maxIndex = i;
   }
   
   return maxIndex;
   
   }
  
  /**
 * gets the maximum deadweight card in a player's hand. Useful if the computer
 * wants to always discard the maximum value. 
 * @param  none
 * @return Card maxDeadweight 
 */
  public Card getMaxDeadweight () {
    //does this require cards to be allocated correctly in hand? 
    setDeadweights();
    Card maxVal = deadweights.get(0);
    for (int j=1; j<deadweights.size(); j++) {
      if (deadweights.get(j).getValueIndex() > maxVal.getValueIndex()) maxVal = deadweights.get(j);
    }
    
    return maxVal;
    
  }
  
  /**
 * Takes a player's hand and finds any sets of cards that
 * are three or more of the same value and marks them as 
 * part of a set as long as the cards are not part of a run. 
 * @param  none
 * @return none 
 */
  public void getSets () {
    //reset valCounts array before checking each card in the hand 
    //System.out.println("GETTING SETS");
    for (int i=0; i<valCounts.length; i++) valCounts[i]=0; 
    
    //if there is a hand
    if (!hand.isEmpty()) {
      for (int i=0; i<hand.size(); i++) {
        //only check cards that are not already in runs 
        if (!hand.get(i).inRun()) {
          valCounts[hand.get(i).getValueIndex()]++;
        }
      }
    }
    //update the inSet variable for each card depending on whether it is in a set
    for (int i=0; i<valCounts.length; i++) {
      if (valCounts[i]>=3) {
        for (int j=0; j<hand.size(); j++) {
          if (hand.get(j).getValueIndex()==i) hand.get(j).set();
        }
      }
    }
    //for (int n=0; n<hand.size(); n++) System.out.println(hand.get(n) + ": " + hand.get(n).inSet());
  }
  
    /**
 * Helper method to find two cards of the same suit
 * in a row. Will help the getRuns method to find three or more in 
 * the same suit in increasing order. 
 * @param  index of a card in a hand, i. 
 * @return true if the card after the index is the same suit 
 * and one greater than it, false otherwise 
 */
  private boolean runsHelper (int i) {
    if ( (hand.size()<3) || i==(hand.size()-1)) return false;
    Collections.sort(hand);
    if (hand.get(i).inSet() || hand.get(i+1).inSet()) return false;
    if (hand.get(i).getValueIndex() == (hand.get(i+1).getValueIndex() - 1) && hand.get(i).getSuit().equals(hand.get(i+1).getSuit())){
      
      return true;
    }
    
    return false;
  }
  
   
   /**
 * In a player's hand, finds three or more cards of the same suit in increasing
 * order. (Ex: 3 of clubs, 4 of clubs, 5 of clubs). Marks the cards as part of a run as long
 * as they are not marked as part of a set. 
 * @param  none
 * @return none 
 */
  public void getRuns() {
    //System.out.println("GETTING RUNS");
    //if (hand.size()==0) return;
    //Collections.sort(hand);
    
    //System.out.println("SORTED HAND: " + hand);
    int i=0;
    while (i<hand.size()-1) {
      
      
      //if there are two in a row
      if (runsHelper(i)) {
        //System.out.println("i is: " + i + " after 2 in a row");
        //if there are three in a row, mark all cards as in a run
        if (runsHelper(i+1)) {
          //System.out.println("i is: " + i + " after 3 in a row");
          hand.get(i).run();
          hand.get(i+1).run();
          hand.get(i+2).run();
          
          //increment i to check the next card
          i+=2;
          //System.out.println("Incremented i by 2: " + i);
          
          //check for fourth
          if (runsHelper(i)) {
            //System.out.println("i is: " + i + " after 4 in a row");
            
            hand.get(i+1).run();
            i++;
            //check for fifth 
            if (runsHelper(i)) {
              //System.out.println("i is: " + i + " after five in a row");
              hand.get(i+1).run();
              i++;
              
              //check for sixth 
              if (runsHelper(i)) {
                //System.out.println("i is: " + i + " after six in a row");
                hand.get(i+1).run();
                i++;
                
                //check for seventh
                if (runsHelper(i)) {
                  //System.out.println("i is: " + i + " after seven in a row");
                  hand.get(i+1).run();
                  i++;
                  
                  //check for eighth
                  if (runsHelper(i)) {
                    //System.out.println("i is: " + i + " after eight in a row");
                    hand.get(i+1).run();
                    i++;
                    
                    //check for ninth 
                    if (runsHelper(i)) {
                      //System.out.println("i is: " + i + " after nine in a row");
                      hand.get(i+1).run();
                      i++;
                      
                      //check for tenth 
                      if (runsHelper(i)) {
                        //System.out.println("i is: " + i + " after ten in a row");
                        hand.get(i+1).run();
                        i++;
                        
                      }
                      i++;
                    }
                    i++;
                  }
                  i++;
                }
                i++;
              }
              i++;
            }
            i++;
          }
          i+=2;
        }
        i++;
      }
      
      i++;
      //System.out.println("i is: " + i + " after being incremented on the bottom.");
    }
    
    
    //for (int j=0; j<hand.size(); j++) 
    //System.out.println(hand.get(j) + ": " + hand.get(j).inRun());
    
    
  }
  
   /**
 * private helper method for allocate hand. Resets all cards' boolean
 * values of inRun and inSet to false so the hand can be rechecked for 
 * sets or runs. 
 * @param  none
 * @return none
 */
  public void resetHand() {
    if (hand.size()==0) return;
    
    for (int i=0; i<hand.size(); i++) {
      hand.get(i).reset();
    }
  }
  
    /**
 * allocates a player's hand into runs and then sets
 * and calculates deadweight score. Resets hand and then 
 * allocates into sets and then runs. If the second allocation
 * yields a lower score, keeps that allocation. Else, resets hand 
 * again and allocates the first way. Ensures that players
 * have the smallest deadweight score possible given their hand. 
 * @param  Player other 
 * @return      one of three integer values 
 */
  public void allocateHand() {
    int score1 = 0;
    int score2 = 0;
    
    if (hand.size()==0) return;
    
    getRuns();
    getSets();
    for (int i=0; i<hand.size(); i++) {
      if (! (hand.get(i).inSet() || hand.get(i).inRun())) {
        score1+= (hand.get(i).getValueIndex() + 2);
      }
    }
    
    
    resetHand();
    
    getSets();
    getRuns();
    for (int j=0; j<hand.size(); j++) {
      if (! (hand.get(j).inSet() || hand.get(j).inRun())) {
        score2+= (hand.get(j).getValueIndex() + 2);
      }
    }
    //System.out.println("Score 1: " + score1);
    //System.out.println("Score 2: " + score2);
    if (score1!=score2) {
      int minScore = Math.min(score1, score2);
      if (minScore==score1) {
        resetHand();
        getRuns();
        getSets();
        
      }
      
      
    }
    
    
    
  }
  
  /**
 * checks if a player's deadweight score is 0. 
 * @param none. 
 * @return true if player's deadweight score is 0. False otherwise. 
 */
  public boolean hasGin () {
    
    return getDeadweightScore()==0;
    
  }
  
  /**
 * checks if a player's deadweight score is less than 10.
 * @param none. 
 * @return true if player's deadweight score is less than 10. 
 * False otherwise. 
 */
  public boolean hasKnock() {
    return getDeadweightScore() <= 10;
  }
  
   /**
 * Returns a string representation of a player including 
 * name, type, score, and number of wins. 
 * @param  none
 * @return String representation of player. 
 */
  public String toString() {
    return ("Name: " + name + "\n" + "Type: " + type + "\n" + 
            "Score: " + deadweightScore + "\n" + "Number of wins: " + nwins + "\n");
  }
  
  //testing method 
  public static void main (String[] args) {
    /* Player p1 = new Player("Human");
     Player p2 = new Player("Human");
     System.out.println(p1);
     Vector<Card> h1 = new Vector<Card>();
     Deck d1 = new Deck();
     for (int i=0; i<10; i++) {
     h1.add(d1.randomShuffle().getTop());
     }
     Deck d2 = new Deck();
     Vector<Card> h2 = new Vector<Card>();
     for (int i=0; i<10; i++) {
     h2.add(d2.getTop()); 
     }
     System.out.println("PERF: ");
     Collections.sort(h2);
     System.out.println("H1: " + h1);
     System.out.println("H2: " + h2);
     
     Card c1 = new Card("2", "clubs");
     Card c2 = new Card("4", "clubs");
     Card c3 = new Card("ace", "clubs");
     Card c4 = new Card("5", "diamonds");
     Card c5 = new Card("7", "diamonds");
     Card c6 = new Card("9", "diamonds");
     Card c7 = new Card("3", "hearts");
     Card c8 = new Card("queen", "spades");
     Card c9 = new Card("king", "spades");
     Card c10 = new Card("ace", "spades");
     Vector<Card> h3 = new Vector<Card>();
     h3.add(c1);
     h3.add(c2);
     h3.add(c3);
     h3.add(c4);
     h3.add(c5);
     h3.add(c6);
     h3.add(c7);
     h3.add(c8);
     h3.add(c9);
     h3.add(c10);
     
     Collections.sort(h3);
     System.out.println("H3: " + h3);
     Player p3 = new Player("computer");
     p3.setHand(h3);
     p3.getRuns();
     
     
     p1.setHand(h1);
     p2.setHand(h2);
     //System.out.println(p1.getHand());
     //p1.getSets();
     System.out.println();
     //for (int i=0; i<p1.valCounts.length; i++) System.out.println(p1.valCounts[i]);
     //for (int i=0; i<p1.getHand().size(); i++) System.out.println(p1.getHand().get(i).inSet());
     //p1.getRuns();
     
     p2.getRuns();
     
     */
    
    
  }
}

