package com.vokaltest.vokaltestapp.model;

/**
 * Created by deepak on 6/4/17.
 */

public class Word implements Comparable<Word> {
    private String text;
    private int count;

    public Word(String text, int count) {
        this.text = text;
        this.count = count;
    }

    @Override
    public int compareTo(Word o) {
        return this.count - o.count;
    }

    @Override
    public String toString() {
        return text + ": " + count;
    }

    public int getCount() {
        return count;
    }
}
