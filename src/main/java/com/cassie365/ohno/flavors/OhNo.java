package com.cassie365.ohno.flavors;

import com.cassie365.ohno.Player;
import com.cassie365.ohno.flavors.Game;
import com.cassie365.ohno.objects.Card;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Default game of OhNo.
 * Follows same rules as UNO
 */
public class OhNo implements Game {
    private Deque<Player> players = new ArrayDeque<Player>();
    private Deque<Card> discard = new ArrayDeque<Card>();
    private Deque<Card> draw = new ArrayDeque<Card>();

    boolean isReversed = false;


    public OhNo(Player[] players){
        for(Player p:players){
            this.players.push(p);
        }
    }

    /**
     * Begin the game
     */
    @Override
    public void start(){
        setup();

        //Remove player from top of stack
        //ANy gameplay effects occur (Draw, skip)
        //Player is given control and place cards
        //PLayer then chooses when to return control
    }

    /**
     * Sets up the game
     */
    @Override
    public void setup(){
        //Add all available Uno cards to the draw deck and shuffle

        //Pop first card and place into discard pile

        //Pop 7 cards to all players
    }

    /**
     * Reverse play order
     */
    public void reverse(){
        isReversed = !isReversed;
    }

    /**
     * Draw cards and give card to player
     */
    public void draw(Player player){
        player.addCards(draw.pop());
    }

    /**
     * Removes next player and places at bottom of queue
     */
    public void skip(){

    }

}
