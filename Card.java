/*CS230 Project
 * Card.java
 * */

public class Card implements Comparable<Card> {
  
  //instance variables 
  private String value, suit;
  
  private boolean inRun, inSet, toDiscard;

  private final int NOT_FOUND = -1;
  private final static String[] allVals = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};
  private final static String[] allSuits = {"clubs", "diamonds", "hearts", "spades"};
  
  
   /**
 * constructor: Makes a new instance of Card 
 * Initalize inRun, inSet, or toDiscard to false 
 * @param  value, suit 
 * @return n/a
 */
  public Card (String v, String s) {
    
    /*ensure that the given values for the variables are all 
    lower case to match the value and suit arrays */
    
    value = v.toLowerCase();
    
    suit = s.toLowerCase();
    
    inRun=false;
    inSet=false;
    toDiscard=false;
    
  }
  
 /**
 * Creates a card with a particular index 
 * @param  int index 
 * @return n/a
 */
  public Card (int o) {
    int index = o%13-1;
    
    if (index==-1) index=12;
    
    value = allVals[index];
    
    if (o>=1 && o<=13) suit = allSuits[0];
    
    if (o>=14 && o<=26) suit = allSuits[1];
    
    if (o>=27 && o<=39) suit = allSuits[2];
    
    if (o>=40 && o<=52) suit = allSuits[3];
    
    
   }
  
  /**
 * gets the card's value index in the allVals array
 * @param  none
 * @return index 
 */
  public int getValueIndex () {
    for (int i=0; i<allVals.length; i++) {
      if (allVals[i].equals(this.getValue())) return i;
    }
    return NOT_FOUND;
  }
      
    
  
   /**
 * get card's face value 
 * @param  none
 * @return String value 
 */ 
  public String getValue () {
    
    return value;
    
  }
  
   /**
 * get the index of the card's suit 
 * in the suit array 
 * @param  none
 * @return int index 
 */ 
  public int getSuitIndex () {
    
    for (int i=0; i<allSuits.length; i++) {
      if (allSuits[i].equals(suit)) return i; 
    }
    return NOT_FOUND;
    
  }
  
   /**
 * get card's overall index i.e. place in a deck
 * @param  none
 * @return int index 
 */
  public int getID () {
    if (!this.equals(null)) {
    int valID = (getValueIndex());
    int suitID = getSuitIndex();
    int overallID = (valID + 1) + (suitID*13);
    return overallID;
    }
    if (this.equals(null)) return NOT_FOUND;
    else return NOT_FOUND;
  }
  
   /**
 * check if card is in a run
 * @param  none
 * @return true if card is in run, false otherwise 
 */
  public boolean inRun() {
    return inRun;
  }
  
  /**
 * check if card is in a set 
 * @param none
 * @return true if card is in set, false otherwise 
 */
  public boolean inSet() {
    return inSet;
  }
  
   /**
 * mark a card as in a set 
 * @param none 
 * @return none
 */
  public void set() {
    inSet=true;
  }
  
    /**
 *mark a card as in a run 
 * @param none
 * @return none 
 */
  public void run() {
    inRun=true;
  }
  
  /**
 * check if a card is marked as a card to discard
 * @param none
 * @return true if card is marked as to discard, false otherwise 
 */
  public boolean getToDiscard() {
    return toDiscard;
  }
  
  /**
 * mark a card as one to discard
 * @param none
 * @return none 
 */
  public void setToDiscard() {
    toDiscard=true;
  }
  
   /**
 * reset a card so that it is not marked as
 * in a run or in a set 
 * @param none
 * @return none 
 */
  public void reset() {
    inSet=false;
    inRun=false;
  
  }
  
  
  /**
 * get a card's suit  
 * @param  none
 * @return String suit 
 */
  public String getSuit () {
    
    return suit;
    
  }
  
  /**
 * get array of card suits 
 * @param none
 * @return String[] suits 
 */
  public static String[] getAllSuits () {
    
    return allSuits;
    
  }
  
  /**
 * get array of card values 
 * @param none
 * @return String[] vals  
 */
  public String[] getAllVals () {
    
    return allVals;
    
  }
  
  /**
 * compareTo: returns -1 if this card's ID is 
 * less than the other card's ID, 1 if it is larger and 0 if
 * they are equal 
 * @param  Card other
 * @return one of three integer values 
 */
  public int compareTo (Card other) {
    
    if (this.getID()<other.getID()) return -1;
    if (this.getID()>other.getID()) return 1;
    return 0;
    
  }
 
  
   /**
 *String representation of card in same format as image names
 * of card images 
 * @param none
 * @return String representation of card
 */
  public String stringCardID(){
    return (getValue() + " of " + getSuit());
    
  }
  
   /**
 * string representation of card, normal 
 * @param  none 
 * @return String representation of card 
 */
  public String toString () {
    
    return (getValue() + "_of_" + getSuit());
    
  }
  
  //testing method 
  public static void main (String[] args) {
   
    Card c1 = new Card("2", "diamonds");
    System.out.println(c1.getID());
    
   
      
      
    
    
      
  }
  
}