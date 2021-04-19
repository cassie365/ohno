package com.cassie365.ohno;

import com.cassie365.ohno.Play;
import com.cassie365.ohno.exceptions.LessThanMinimumPlayersException;
import com.cassie365.ohno.exceptions.MaxPlayersExceededException;
import com.cassie365.ohno.flavors.Game;
import com.cassie365.ohno.flavors.OhNo;
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
    public void shouldHandExceptionIfOver10Players() throws LessThanMinimumPlayersException, MaxPlayersExceededException {
        List<Player> p = new ArrayList<Player>();
        for(int i = 0; i < 11; i++)
            p.add(new Player(Integer.toString(i)));
        Game g = new OhNo(p);
    }

    @Test(expected = LessThanMinimumPlayersException.class)
    public void shouldPreventStartIfLessThan2Players() throws LessThanMinimumPlayersException, MaxPlayersExceededException {
        List<Player> p = new ArrayList<>();
        p.add(new Player("Bob"));
        Game g = new OhNo(p);
    }

    @Test
    public void shouldSkipPlayer(){

    }

    @Test
    public void shouldReversePlayOrder(){

    }

    @Test
    public void shouldDeclareWinner(){

    }

}
