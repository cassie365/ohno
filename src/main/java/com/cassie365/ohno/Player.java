package com.cassie365.ohno;


import com.cassie365.ohno.objects.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a player in the game of OhNo.
 * Holds information on player hand
 */
public class Player {
    private List<Card> hand;
    private String name;

    public Player(String name){
        this.name = name;
        hand = new ArrayList<Card>();
    }

    /**
     * Print the hand of a player to the screen
     */
    public void showHand(){

    }


    /**
     * Play a card based on the index
     * @param cardNum
     * @return the card
     */
    public Card playCard(int cardNum){
        return null;
    }

    /**
     * Recieve cards from the game
     */
    public void addCards(Card card){
            hand.add(card);
    }
}
