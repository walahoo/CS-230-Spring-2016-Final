/* CS230 Project
 * GinRummyDriver.java
 * Authored by Olivia Ondeck/ Ngina Kariuki/Renee Huang 
 * Used to test everything and anything related GinRummy.java
 * */

public class GinRummyDriver {
  
  public static void main (String[] args) {
    
    //testing unique rank values
    
    //Deck d1 = new Deck(4);
    //System.out.println("Creating new deck of 4 cards: \n" + d1);
    
    //System.out.println("Randomly shuffling the deck of 4 cards:" + d1.randomShuffle());
    
    //Deck d2 = new Deck();
    //System.out.println("Random shuffle of a full deck of cards" + d2.randomShuffle());
   // while (!d2.isEmpty()) {
    //  System.out.println(d2.seeTop() + ": " + d2.getTop().getID());
    //}
    
    GinRummy g1 = new GinRummy(2);
   //g1.deal();
   //System.out.println("MAX DEADWEIGHT: " + g1.getMaxDeadweight(g1.getPlayerQueue().first()));
   //g1.computerTurn(); 
   g1.deal();
   g1.humanTurn();
   g1.computerTurn();/*
   System.out.println("Computer: " + g1.p.getHand());
   System.out.println("You: " + g1.you.getHand());
   g1.humanTurn();
   g1.computerTurn();
   System.out.println("\nComputer: " + g1.p.getHand());

   g1.humanTurn();
   g1.computerTurn();
   System.out.println("\nComputer: " + g1.p.getHand());

   g1.humanTurn();
   g1.computerTurn();
   System.out.println("\nComputer: " + g1.p.getHand());

   g1.humanTurn();
   g1.computerTurn();
   System.out.println("\nComputer: " + g1.p.getHand());

    */
    
    
    
  }
  
}