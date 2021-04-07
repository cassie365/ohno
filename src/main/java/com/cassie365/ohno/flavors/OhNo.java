package com.cassie365.ohno.flavors;

import com.cassie365.ohno.objects.Player;
import com.cassie365.ohno.objects.Card;
import com.cassie365.ohno.utils.DeckInitializer;

import java.util.*;

/**
 * Default game of OhNo.
 * Follows same rules as UNO
 */
public class OhNo implements Game {
    private final int HAND_SIZE = 7;

    Scanner scanner = new Scanner(System.in);

    private List<Player> players = new ArrayList();
    private Deque<Player> playOrder = new ArrayDeque<Player>();
    private Deque<Card> discard = new ArrayDeque<Card>();
    private Deque<Card> draw = new ArrayDeque<Card>();


    public OhNo(Player[] players){
        this.players.addAll(Arrays.asList(players));
    }

    /**
     * Begin the game
     */
    @Override
    public void start(){
        setup();
        Card lastTop = null;

        play: while(!draw.isEmpty()){
            //Remove player from top of stack
            Player player = nextPlayer();

            //ANy gameplay effects occur (Draw, skip)
            Card card = discard.peek();

            if(card!=lastTop){
                System.out.println("New Card");
                lastTop = card;
                switch(card.getText()){
                    case "Reverse":
                        reverse();
                        break;
                    case "Skip":
                        System.out.println("Skipping "+player.getName());
                        continue play;
                    case "Draw 2":
                        draw(player,2);
                        break;
                    case "Wild +4":
                        draw(player, 4);
                        break;
                }
            }

            //Player is given control and place cards
            startTurn(player,card);
        }
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
            draw(p,7);
            playOrder.push(p);
        }
    }

    /**
     * Reverse play order
     */
    public void reverse(){
        Deque<Player> players = new ArrayDeque<Player>();
        Iterator<Player> i = playOrder.descendingIterator();
        while(i.hasNext()){
            players.offer(playOrder.pop());
        }
        playOrder = players;
    }

    /**
     * Draw cards and give card to player
     */
    public void draw(Player player,int num){
        System.out.println("Drawing "+num+" for "+player.getName());
        for(int i = 0; i<num; i++){
            player.addCard(draw.pop());
        }
    }

    /**
     * Removes next player and places at bottom of queue
     */
    public Player nextPlayer(){
        Player p = playOrder.pop();
        System.out.println("Removed "+p.getName()+" from top of stack");
        playOrder.offer(p);
        return p;
    }

    public void play(Player player, int card){
        Card c = player.getHand().get(card);
        if(player.removeCard(c)){
            discard.push(c);
        }
    }

    public List<Card> getPlayable(Player player, Card top){
        List<Card> playable = new ArrayList<>();
        for(Card c:player.getHand()){
            if(c.getValue() >=0 && c.getValue()+1 == top.getValue() || c.getValue()-1 == top.getValue())
                playable.add(c);
            else if (c.getValue() == top.getValue())
                playable.add(c);
            else if (c.getColor().equals(top.getColor()))
                playable.add(c);
        }

        return playable;
    }

    public void showPlayable(Player player,List<Card> playable){
        for(int i = 1; i<=player.getHand().size(); i++){
            if(playable.contains(player.getHand().get(i-1)))
                System.out.println("("+i+") "+player.getHand().get(i-1));
        }
    }

    public void showTop(){
        System.out.println("-----------------------------------\n"+discard.peekFirst().toString()+"\n-----------------------------------");
    }

    public void startTurn(Player player, Card top){
        showTop();
        List<Card> playable = new ArrayList<>();
        System.out.println("Player: "+player.getName());
        System.out.println("Currently Playable");
        if(top.getColor() == "Wild")
            playable.addAll(player.getHand());
        else{
            playable = getPlayable(player,top);
            if(playable.size()<=0){
                System.out.println("No available cards... drawing...");
                draw(player,1);
                playable = getPlayable(player,top);
                if(playable.size()<=0) {
                    System.out.println("No playable cards, next turn");
                    return;
                }
                else{
                    showPlayable(player,playable);
                }
            }
            else{
                showPlayable(player,playable);
            }
        }

        boolean end = false;
        while(!end){
            System.out.print("Your Selection: ");
            String command = scanner.next();
            switch(command.toLowerCase()){
                case "play":
                    int i = Integer.parseInt(scanner.next());
                    play(player,i-1);
                    end = true;
                    break;
                case "hand":
                    player.showHand();
                    System.out.println("-------------------");
                    showPlayable(player,playable);
                    end = false;
                    break;
                default:
                    System.out.println("Unknown action "+command);
                    end = false;
            }
        }
    }

}
