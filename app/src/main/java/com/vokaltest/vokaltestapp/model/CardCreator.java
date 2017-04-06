package com.vokaltest.vokaltestapp.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by deepak on 6/4/17.
 */

public class CardCreator {

    private static CardCreator INSTANCE;

    private HashMap<Card.Range, Card> cardMap = new HashMap<>();

    private CardCreator() {

    }

    public static CardCreator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CardCreator();
        }
        return INSTANCE;
    }

    public Card getCardObject(Card.Range range) {
        Card card = cardMap.get(range);
        if (card == null) {
            card = new Card(range);
            cardMap.put(range, card);
        }
        return card;
    }

    public Map<Card.Range, Card> getCards() {
        return cardMap;
    }

}
