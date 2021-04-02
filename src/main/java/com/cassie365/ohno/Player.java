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

    public List<Card> getHand() {
        return hand;
    }

    public boolean removeCard(Card card){
        hand.remove(card);
        return true;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Print the hand of a player to the screen
     */
    public void showHand(){
        StringBuilder s = new StringBuilder();
        s.append("Player: "+name+"\n");
        for(int i = 0; i<hand.size();i++){
            Card c = hand.get(i);
            s.append("\n("+(i+1)+") "+c.toString());
        }
        System.out.println(s.toString());
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
    public void addCard(Card card){
            hand.add(card);
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("Player: "+name);
        for(Card c:hand){
            s.append("\n"+c.toString());
        }
        return s.toString();
    }
}
