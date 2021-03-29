package com.cassie365.ohno.flavors;

import com.cassie365.ohno.Player;
import com.cassie365.ohno.flavors.Game;
import com.cassie365.ohno.objects.Card;
import com.cassie365.ohno.utils.DeckInitializer;

import java.util.*;

/**
 * Default game of OhNo.
 * Follows same rules as UNO
 */
public class OhNo implements Game {
    private final int HAND_SIZE = 7;

    private List<Player> players = new ArrayList();
    private Deque<Player> playOrder = new ArrayDeque<Player>();
    private Deque<Card> discard = new ArrayDeque<Card>();
    private Deque<Card> draw = new ArrayDeque<Card>();

    boolean isReversed = false;


    public OhNo(Player[] players){
        Collections.addAll(this.players,players);
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
        DeckInitializer d = new DeckInitializer();
        draw = d.generateArrayDequeCards();

        //Pop first card and place into discard pile
        discard.push(draw.pop());

        //Pop 7 cards to all players
        for(Player p : players){
            for(int i = 0; i<HAND_SIZE; i++){
                draw(p);
            }
        }
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
        player.addCard(draw.pop());
    }

    /**
     * Removes next player and places at bottom of queue
     */
    public void skip(){
        playOrder.push(playOrder.pop());
    }

}
