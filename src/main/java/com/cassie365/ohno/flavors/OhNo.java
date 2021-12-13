package com.cassie365.ohno.flavors;

import com.cassie365.ohno.exceptions.LessThanMinimumPlayersException;
import com.cassie365.ohno.exceptions.MaxPlayersExceededException;
import com.cassie365.ohno.objects.Player;
import com.cassie365.ohno.objects.Card;
import com.cassie365.ohno.utils.DeckInitializer;
import org.apache.logging.log4j.LogBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * Default game of OhNo.
 * Follows same rules as UNO
 */
public class OhNo implements Game {
    private Logger logger = LogManager.getLogger(Game.class);
    private final int HAND_SIZE = 7;
    private final int MAX_PLAYERS = 10;
    private final int MIN_PLAYERS = 2;
    private int turns = 0;

    Scanner scanner = new Scanner(System.in);

    private List<Player> players = new ArrayList();
    private Deque<Player> playOrder = new ArrayDeque<Player>();
    private Deque<Card> discard = new ArrayDeque<Card>();
    private Deque<Card> inPlay = new ArrayDeque<Card>(1);
    private Deque<Card> draw = new ArrayDeque<Card>();


    public OhNo(Player[] players) throws LessThanMinimumPlayersException, MaxPlayersExceededException {
        this.players.addAll(checkPlayers(Arrays.asList(players)));
    }

    public OhNo(List<Player> players) throws LessThanMinimumPlayersException, MaxPlayersExceededException {
        this.players.addAll(checkPlayers(players));
    }

    /**
     * Begin the game
     */
    @Override
    public void start(){
        logger.info("Started Game");
        setup();

        play: while(!draw.isEmpty()){
            turns++;
            logger.info("STARTING TURN "+turns);
            logger.info("CARDS LEFT IN DRAW "+draw.size());
            //Remove player from top of stack
            Player player = nextPlayer();

            //ANy gameplay effects occur (Draw, skip)
            Card card = inPlay.pop();

            if(card!=null){
                switch(card.getText()){
                    case "Reverse":
                        if(turns == 1)
                            break;
                        logger.info("Reversing...");
                        reverse();
                        break;
                    case "Skip":
                        logger.info("Skipping");
                        System.out.println("Skipping "+player.getName());
                        continue play;
                    case "Draw 2":
                        logger.info("Drawing two to player "+player.getName());
                        draw(player,2);
                        break;
                    case "Wild +4":
                        logger.info("Wild +4, drawing 4 to "+player.getName());
                        draw(player, 4);
                        break;
                }
            }
            else
                logger.info("Last card matches, resuming without preliminary steps");

            discard.push(card);

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
        logger.info("DECK INITIALIZED WITH "+draw.size());

        //Pop first card and place into discard pile
        inPlay.push(draw.pop());

        //Pop 7 cards to all players
        for(Player p : players){
            draw(p,HAND_SIZE);
            playOrder.push(p);
        }
    }

    /**
     * Reverse play order
     */
    public void reverse(){
        logger.info("Reversed play order, size "+playOrder.size());
        Player[] p = new Player[10];
        List<Player> players = Arrays.asList(playOrder.toArray(p));
        Collections.reverse(players);
        playOrder = new ArrayDeque<>(players);
    }

    /**
     * Draw cards and give card to player
     */
    public void draw(Player player,int num){
        logger.info("Drawing "+num+" for "+player.getName());
        for(int i = 0; i<num; i++){
            player.addCard(draw.pop());
        }
    }

    /**
     * Removes next player and places at bottom of queue
     */
    public Player nextPlayer(){
        Player p = playOrder.pop();
        playOrder.offer(p);
        logger.info("Player Now "+p.getName());
        return p;
    }

    public void play(Player player, int card){
        Card c = player.getHand().get(card);
        logger.info(player.getName()+"Playing "+c.toString());
        if(player.removeCard(c)){
            discard.push(c);
            logger.info("Successfully discarded "+c.toString());
        }else
            logger.warn("Unsuccessful discard of "+c.toString());

    }

    public List<Integer> getPlayableCards(Player player, Card top){
        List<Integer> playable = new ArrayList<>();
        Card[] hand = (Card[]) player.getHand().toArray();
        for(int i = 0; i<hand.length; i++){
            if(hand[i].getValue()+1 == top.getValue() ||
                    hand[i].getValue()-1 == top.getValue() ||
                    hand[i].getColor() == top.getColor()){
                playable.add(i);
            }
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

    private List<Player> checkPlayers(List<Player> players) throws MaxPlayersExceededException, LessThanMinimumPlayersException {
        if(players.size()>MAX_PLAYERS)
            throw new MaxPlayersExceededException("Too many players!");
        else if (players.size()<MIN_PLAYERS)
            throw new LessThanMinimumPlayersException("Not enough players!");
        return players;
    }

    public void startTurn(Player player, Card top){
        //Show the top card

        //Get playable cards
        List<Card> playable = getPlayable(player, top);

        //If no playable cards, draw 1
        if(!playable.isEmpty()){
            draw(player,1);
            playable = getPlayable(player,top);
            //If no playable cards after drawing, return.
            if(playable.isEmpty())
                return;
        }

        //Allow player to choose the next card to play


    }

    public void getPlayerMove(Player player){
        //If is a bot, randomly select a card that can be played
        if(player.isBot()){

        }else{

        }
    }

    public List<Card> getPlayable(Player player, Card top){
        List<Card> playable = new ArrayList<>();
        if(top.getColor() == "Wild") {
            playable.addAll(player.getHand());
            showPlayable(player,playable);
        }
        else{
            playable = getPlayable(player,top);
            if(playable.size()<=0){
                System.out.println("No available cards... drawing...");
                logger.info("Player "+player.getName()+" has no cards able to be played, drawing card");
                draw(player,1);
                playable = getPlayable(player,top);
                if(playable.size()<=0) {
                    System.out.println("No playable cards, next turn");
                    logger.info("Player "+player.getName()+" has no cards able to be played, ending turn");
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
    }

    public void getPlayerInput(Player player, Card){
        boolean end = false;
        while(!end){
            System.out.print("Your Selection: ");
            String command = scanner.next();
            logger.info("\""+command+"\" input by "+player.getName());
            switch(command.toLowerCase()){
                case "play":
                    logger.info("Recognized command \""+command+"\"");
                    int i = Integer.parseInt(scanner.next());
                    play(player,i-1);
                    end = true;
                    break;
                case "hand":
                    logger.info("Recognized command \""+command+"\"");
                    player.showHand();
                    System.out.println("-------------------");
                    showPlayable(player,playable);
                    end = false;
                    break;
                default:
                    logger.info("Unrecognized command \""+command+"\"");
                    System.out.println("Unknown action "+command);
                    end = false;
            }
        }
    }

}
