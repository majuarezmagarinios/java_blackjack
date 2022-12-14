package entities;

public class Card {
    private final String numberCard;
    private String suiteCard;

    public Card(String numberCard, String suiteCard) {
        this.numberCard = numberCard;
        this.suiteCard = suiteCard;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public String getSuiteCard() {
        return suiteCard;
    }

    public void setSuiteCard(String suiteCard) {
        this.suiteCard = suiteCard;
    }

    @Override
    public String toString() {
        return numberCard + " " + suiteCard;
    }
}
