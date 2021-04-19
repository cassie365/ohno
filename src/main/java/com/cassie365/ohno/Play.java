package com.cassie365.ohno;

import com.cassie365.ohno.exceptions.LessThanMinimumPlayersException;
import com.cassie365.ohno.exceptions.MaxPlayersExceededException;
import com.cassie365.ohno.flavors.Game;
import com.cassie365.ohno.flavors.OhNo;
import com.cassie365.ohno.objects.Player;

/**
 * Game Runner
 */
public class Play {
    public static void main(String[] args) {
        Player player1 = new Player("Cassie");
        Player player2 = new Player("John");
        Player[] players = {player1,player2};

        try{
            Game game = new OhNo(players);
            game.start();
        }
        catch (MaxPlayersExceededException | LessThanMinimumPlayersException e) {
            e.printStackTrace();
        }
    }
}
