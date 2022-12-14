package entities;

import utils.OutputColors;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Integer id;
    private String namePlayer;
    private final List<Card> cardsPlayer;
    private Integer sumCards = 0;

    public Player(Integer id, String namePlayer) {
        this.id = id;
        this.namePlayer = OutputColors.ANSI_YELLOW + namePlayer + OutputColors.ANSI_RESET;
        cardsPlayer = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public List<Card> getcardsPlayer() {
        return cardsPlayer;
    }

    public void setcardsPlayer(Card c) {
        cardsPlayer.add(c);
    }

    public Integer getsumCards() {
        return sumCards;
    }

    public void setsumCards(Integer sumCards) {
        this.sumCards = sumCards;
    }

    @Override
    public String toString() {
        System.out.print("PLAYER " + namePlayer + "  ->  ");
        System.out.print(OutputColors.ANSI_YELLOW + "CARDS" + OutputColors.ANSI_RESET + "[ ");

        for (int i = 0; i < cardsPlayer.size(); i++) {
            if (i == cardsPlayer.size() - 1) {
                System.out.print(cardsPlayer.get(i));
            } else {
                System.out.print(cardsPlayer.get(i) + ", ");
            }

        }
        return " ]";
    }

}
