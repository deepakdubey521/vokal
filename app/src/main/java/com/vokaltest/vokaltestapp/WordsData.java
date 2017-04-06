package com.vokaltest.vokaltestapp;

/**
 * Created by deepak on 6/4/17.
 */

public class WordsData implements Comparable<WordsData> {
    String mWord;
    int mValue;

    public WordsData(String word, int value) {
        mWord = word;
        mValue = value;
    }

    @Override
    public int compareTo(WordsData o) {
        return this.mValue - o.mValue;
    }

    @Override
    public String toString() {
        return mWord + ": " + mValue;
    }
}
