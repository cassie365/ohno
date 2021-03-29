package com.cassie365.ohno;

import com.cassie365.ohno.flavors.Game;
import com.cassie365.ohno.flavors.OhNo;
import com.cassie365.ohno.objects.Card;
import com.cassie365.ohno.utils.DeckInitializer;

import java.util.Deque;

/**
 * Game Runner
 */
public class Play {
    public static void main(String[] args) {
        Player player1 = new Player("Cassie");
        Player player2 = new Player("John");
        Player[] players = {player1,player2};

        Game game = new OhNo(players);

        DeckInitializer d = new DeckInitializer();
        Deque<Card> dd = d.generateArrayDequeCards();

        for(Card x : dd){
            System.out.println(x.toString());
        }
    }
}
