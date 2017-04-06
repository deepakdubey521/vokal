package com.vokaltest.vokaltestapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deepak on 6/4/17.
 */

public class CardObject {
    public String range;
    public  List<WordsData> wordsDatas;

    public CardObject(String range) {
        this.range = range;
        wordsDatas = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
      //  builder.append("range: ").append(range).append(" - ");
        for (WordsData word : wordsDatas) {
            builder.append(word).append("\n");
        }
        return builder.toString();
    }
}
