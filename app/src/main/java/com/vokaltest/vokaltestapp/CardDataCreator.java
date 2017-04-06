package com.vokaltest.vokaltestapp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by deepak on 6/4/17.
 */

public class CardDataCreator {

    HashMap<String, CardObject> map = new HashMap<>();

    public CardObject getCardObject(String range) {
        CardObject cardObject = map.get(range);
        if (cardObject == null) {
            cardObject = new CardObject(range);
            map.put(range, cardObject);
        }
        return cardObject;
    }

    public Map<String, CardObject> getCards() {
        return map;
    }

}
