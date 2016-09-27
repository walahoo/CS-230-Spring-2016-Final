/* CS230 Project
 * GinRummyInterface.java
 * Primarily authored by Olivia Ondeck
 * An interface for the Gin Rummy game
 * */

public interface GinRummyInterface {
  
   /******************************************************************
     Deals 10 cards to each player in the game. Sets the player's
     hand to the vector of 10 cards. After dealing the cards to the player
     the top card left on the main deck of cards is removed and turned into
     the discard pile. The rest of the deck is turned into the stock pile. 
    *****************************************************************/
  public void deal();
  
  
  
   
  /******************************************************************
    A card is randomly drawn from the discard pile or the stock pile
    and added to the computer player's hand. A card in the computer's 
    hand that is considered a 'deadweight card' is added to the 
    discard pile. 
    *****************************************************************/
  public void computerTurn();
  
  
  /******************************************************************
    Human player selects whether they want to draw from the draw
    pile or discard pile. A card from the pile chosen is added to 
    the human player's hand. Human player then selects which card to 
    discard. 
    *****************************************************************/
  public void humanTurn();
  
   
  /******************************************************************
    Sets the input player's score. 
    Looks at the deadweight cards of the other players.
    For each set of deadweight cards, checks isRun and isSet 
    against the input player's sets and runs, and adds the cards to 
    the sets and runs in the player's hand.
    Records the score for each player. 
    Player with lowest score nwins incremented by 1. 
    gameOver set to true, so the game ends. 
    *****************************************************************/
  public void knock(Player p);
  
   
  /******************************************************************
    Checks if each computer player's score is 0. 
    Sets ginStatus() to true if so 
    *****************************************************************/
  public boolean ginStatus();
  
   
  /******************************************************************
    Starts one game until gameOver is true.
    Uses a queue of players to switch turns.
    Ends game while playAgain is true.
    *****************************************************************/
  public void playGame();
  
  
  
  
  
  
  
  
  
  
  
  
  
}