import services.GameCardServices;
import utils.OutputColors;

import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println();
        System.out.println("♥♦" + OutputColors.ANSI_CYAN + " Welcome to Black Jack " + OutputColors.ANSI_RESET + "♣♠");
        GameCardServices gameServ = new GameCardServices();
        gameServ.createCards();
        gameServ.shufleCards();
        System.out.println();

        System.out.print("How many players want to play? ");
        int numberPlayers = sc.nextInt();
        System.out.println();

        gameServ.createPlayer(numberPlayers);
        System.out.println();
        gameServ.cardsUser(numberPlayers);

        System.out.println();
        System.out.println();

        gameServ.nextCard();

        gameServ.winners();

    }

}
