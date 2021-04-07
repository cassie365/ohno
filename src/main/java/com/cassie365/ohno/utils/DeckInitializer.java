package com.cassie365.ohno.utils;

import com.cassie365.ohno.objects.Card;

import java.util.*;

/**
 * Acts as a factory to create a deck of cards.
 * Should read available cards from a file somewhere and return the Deck
 */
public class DeckInitializer {
    private String[] colors = {"Red","Yellow","Green","Blue"};
    private String[] colorCards = {"0","1","2","3","4","5","6","7","8","9","Reverse","Skip","Draw 2"};
    private int[] values = {0,1,2,3,4,5,6,7,8,9,-1,-2,-3};
    private int[] numKinds = {1,2,2,2,2,2,2,2,2,2,2,2,2};

    public Deque<Card> generateArrayDequeCards(){
        List<Card> deck = new ArrayList<Card>();
        //Generate color cards
        for(int i = 0; i<colors.length; i++){
            for(int j = 0; j<numKinds.length;j++){
                for(int x = 0; x<numKinds[j]; x++){
                    Card card = new Card(colorCards[j],colors[i],values[j]);
                    deck.add(card);
                }
            }
        }

        //Generate Wild cards
        for(int i = 0; i<4;i++){
            Card card = new Card("Wild","Wild",-1);
            deck.add(card);
        }

        //Generate Wild +4 Card
        for(int i = 0; i<4;i++){
            Card card = new Card("Wild +4","Wild",-1);
            deck.add(card);
        }

        Random rand = new Random();

        for(int i = 0; i<deck.size(); i++){
            int n = rand.nextInt(deck.size()-1);

            Card temp = deck.get(i);
            Card temp2 = deck.get(n);
            deck.set(i, temp2);
            deck.set(n,temp);
        }

        Deque<Card> shuffled = new ArrayDeque<Card>(deck);

        return shuffled;
    }
}
