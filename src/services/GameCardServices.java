package services;

import entities.Card;
import entities.Player;
import org.jetbrains.annotations.NotNull;
import utils.OutputColors;

import java.util.*;

public class GameCardServices {
    public List<Card> cards;
    public List<Player> players;
    public Scanner sc;

    public GameCardServices() {
        cards = new ArrayList<>();
        this.players = new ArrayList<>();
        this.sc = new Scanner(System.in);

    }

    //    Loops through each suit and number and assigns it to the card
    public void createCards() {
        String suits = "♥♦♣♠";
        String numbers = "123456789JQKA";
        int a = 0;

        for (int i = 0; i < suits.length(); i++) {
            a++;
            int x = 0;
            for (int j = 0; j < numbers.length(); j++) {
                x++;
                String suit = suits.substring(i, a);
                String number = numbers.substring(j, x);
                cards.add(new Card(number, suit));
            }

        }

    }

    //      Shows a message that the cards are starting to shuffle
    public void shufleCards() {
        int puntos = 0;
        int count = 0;
        System.out.println();
        System.out.print("Wait we are shuffling the deck of cards ");

        while (count < 5) {
            System.out.print(OutputColors.ANSI_GREEN + "." + OutputColors.ANSI_RESET);
            puntos++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            count++;
        }

//        Shuffle the cards
        Collections.shuffle(cards);
    }

    public void createPlayer(int numberPlayers) {
        String namePlayer = "";

        for (int i = 0; i < numberPlayers; i++) {
            do {
                System.out.print("Enter your name player " + (i + 1) + ": ");
                namePlayer = sc.nextLine().toUpperCase();
            } while (namePlayer.isEmpty());

            players.add(new Player(i, namePlayer));

        }
    }

    //    Distribute two cards to each user.
    public void cardsUser(int numberPlayers) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < numberPlayers; j++) {
                Card a = cards.get(0);
                players.get(j).setcardsPlayer(a);
                cards.remove(0);
            }
        }

        for (Player p : players) {
            sumCardsPlayers(p);
        }
    }

    //      If the dealt cards add up to 21 it indicates that you win and if
//  they exceed more than 21 it indicates that you lose
    public void nextCard() {
        String respUser = "";
        Iterator<Card> it = cards.iterator();

        for (int i = 0; i < players.size(); i++) {

            do {
                int sumacartas = sumCardsPlayers(players.get(i));
                if (sumacartas == 21) {
                    System.out.println();
                    System.out.println(players.get(i).toString());
                    System.out.println(OutputColors.ANSI_GREEN_BACKGROUND + "***** You win *****" + OutputColors.ANSI_RESET);
                    System.out.println();
                    break;
                } else if (sumacartas > 21) {
                    System.out.println();
                    System.out.println(players.get(i).toString());
                    System.out.println(OutputColors.ANSI_RED_BACKGROUND + "***** You lose *****" + OutputColors.ANSI_RESET);
                    System.out.println();
                    break;
                } else {
                    System.out.println(players.get(i));

                    do {
                        System.out.print("Do you want one more card? YES/NO ");
                        respUser = sc.nextLine();
                    } while (respUser.isEmpty());


                    if (respUser.equalsIgnoreCase("yes")) {

                        if (it.hasNext()) {
                            Card a = cards.get(0);
                            players.get(i).setcardsPlayer(a);
                            sumCardsPlayers(players.get(i));
                            cards.remove(0);
                            System.out.println();

                        } else {
                            System.out.println("There are no more cards");
                        }

                    } else {
                        System.out.println();
                        System.out.println();

                        if (i != players.size() - 1) {
                            System.out.println(OutputColors.ANSI_RED_BACKGROUND + "NEXT USER!!!" + OutputColors.ANSI_RESET);
                        }

                        System.out.println();
                    }
                    System.out.println();
                }
            } while (respUser.equalsIgnoreCase("yes"));
        }
    }

    //      Add up the cards and store the total in the player attribute
//  and return the total for that attribute
    public int sumCardsPlayers(@NotNull Player p) {
        int total = 0;
        int numCard = 0;
        String number = "";

        for (int i = 0; i < p.getcardsPlayer().size(); i++) {
            number = p.getcardsPlayer().get(i).getNumberCard();
            switch (number) {
                case "J":
                    numCard = 11;
                    break;
                case "Q":
                    numCard = 12;
                    break;
                case "K":
                    numCard = 13;
                    break;
                case "A":
                    numCard = 14;
                    break;
                default:
                    numCard = Integer.parseInt(number);
                    break;
            }

            total += numCard;

            numCard = 0;
        }
        p.setsumCards(total);

        return p.getsumCards();
    }

    //    Indicate if there are winners
    public void winners() {
        int greater = 0;
        boolean win = false;

        for (Player p : players) {

            if (p.getsumCards() == 21) {
                win = true;
                System.out.println();
                System.out.println(p + " ");
                System.out.println("Sum of your cards ->  " + p.getsumCards());
                System.out.println(OutputColors.ANSI_GREEN_BACKGROUND + "   ***** You win! *****" + OutputColors.ANSI_RESET);
            } else if (p.getsumCards() < 21) {
                if (p.getsumCards() > greater) {
                    greater = p.getsumCards();
                }
            }
        }

        if (!win) {
            for (Player p : players) {
                if (p.getsumCards() == greater) {
                    System.out.println();
                    System.out.println(p + " ");
                    System.out.println("Sum of your cards ->  " + p.getsumCards());
                    System.out.println(OutputColors.ANSI_GREEN_BACKGROUND + "   ***** You win! *****" + OutputColors.ANSI_RESET);
                }
            }
        }

    }

}
