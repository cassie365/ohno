package com.cassie365.ohno.flavors;

import com.cassie365.ohno.Play;
import com.cassie365.ohno.exceptions.LessThanMinimumPlayersException;
import com.cassie365.ohno.exceptions.MaxPlayersExceededException;
import com.cassie365.ohno.objects.Player;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OhNoTests {
    @Test
    public void shouldAddPlayers(){
        Player player1 = new Player("Cassie");
        Player player2 = new Player("John");
        Player player3 = new Player("Nancy");
        Player[] players = {player1,player2,player3};
    }

    @Test(expected = MaxPlayersExceededException.class)
    public void shouldHandExceptionIfOver10Players(){
        List<Player> p = new ArrayList<Player>();
        for(int i = 0; i < 11; i++)
            p.add(new Player(Integer.toString(i)));
    }

    @Test(expected = LessThanMinimumPlayersException.class)
    public void shouldPreventStartIfLessThan2Players(){

    }

    @Test
    public void shouldPreventPlayersFromBeingAddedOnceGameStarts(){

    }

    @Test
    public void shouldPrintPlayerHandToConsole(){

    }

}
