/*CS230 Project
 * Deck.java
 * Primarily authored by Olivia Ondeck
 * Creates a deck of cards: 
 * Either a standard 52 deck or a deck of a chosen size
 * Has a random shuffle method to shuffle the deck of cards
 * */

import java.util.*;
import javafoundations.ArrayStack;

public class Deck {
  
  //instance variable: stack that serves as a container for the collection of cards 
  private ArrayStack<Card> cardDeck = new ArrayStack<Card>();
  private final int NOT_FOUND = -1; 
  
  
  
   /**
 * constructor: Create a standard deck of 52 ordered cards 
 * @param  none
 * @return none
 */
  public Deck () {
    for (int i=1; i<=52; i++) {
      
      Card c = new Card(i);
      cardDeck.push(c);
      
    }
    
  }
  
   /**
 * second constructor: Create a deck of n ordered cards 
 * @param  none
 * @return none
 */
  public Deck (int n) {
    if (n == 0) cardDeck=cardDeck;
    
    else 
    {
    for (int i=1; i<=n; i++) {
      
      Card c = new Card(i);
      cardDeck.push(c);
      
    }
    }
  }

  
  /**
 * add a card to the Deck 
 * @param Card c 
 * @return none 
 */
  public void addCard (Card c) { 
    
    c = new Card(c.getValue(), c.getSuit());
    cardDeck.push(c);
    
   }
  
  /**
 * Peek at the card at the top of a Deck 
 * @param none
 * @return Card top
 */
  public Card seeTop () {
    
    return cardDeck.peek();
    
  }
  
  /**
 * Pop top card on deck: remove the Card and return it 
 * @param  Player other 
 * @return Card top
 */
  public Card getTop () {
    
    return cardDeck.pop();
  
  }
  
    
   /**
 * Create a clone of a deck 
 * @param none
 * @return Deck clone 
 */
  public Deck clone () {
    
    Vector<Card> temp = new Vector<Card>();
    Deck clone = new Deck(0);
    
    while (!this.isEmpty()) {
      
      Card c = cardDeck.pop();
      //add each card in this deck to the vector
      temp.add(c);
      
      }
    
    for (int i=0; i<temp.size(); i++) {
      
      //add cards to clone in the same order as they were in this deck
      clone.addCard(temp.get(i)); 
      //add the cards back to the original cardDeck, preserving the order 
      cardDeck.push(temp.get(i));
      
    }
    
    return clone;
    
  }
  
   /**
 * Randomly shuffle a deck of cards 
 * @param none
 * @return randomly shuffled Deck 
 */
  public Deck randomShuffle () {
    Vector<Integer> intVec = new Vector<Integer>();
    Vector<Card> temp = new Vector<Card>();
    int size = cardDeck.size();
 
    while (!cardDeck.isEmpty()) {
      temp.add(cardDeck.pop());
    }

    Random rand = new Random();

    while (intVec.size()<size) {
      int i = (rand.nextInt(size));

      if (!intVec.contains(i)) {
      cardDeck.push(temp.get(i));
      intVec.add(i);

      }
      
    }
    
    return this;
    
  }
    
  
  
  /**
 * return size of card deck 
 * @param  none
 * @return int size 
 */
  public int size () {
    
    return cardDeck.size();
    
  }
  
  /**
 * check whether the Deck is empty 
 * @param none 
 * @return true if deck is empty and false otherwise 
 */
  public boolean isEmpty () {
    
    return cardDeck.isEmpty();
    
  }
  
 /**
 * creates String representation of the Deck 
 * @param  none 
 * @return String cardDeck 
 */
  public String toString () {
    
    return cardDeck.toString();
    
  }
  
  //testing method 
  public static void main (String[] args) {
    
    }
  
  
  
  
  
  
  
}