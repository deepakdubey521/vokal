package com.vokaltest.vokaltestapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deepak on 6/4/17.
 */

public class Card implements Comparable<Card> {
    private List<Word> wordList = new ArrayList<>();
    private Range range;

    public Card(Range range) {
        this.range = range;
    }

    public void addWordsData(Word word) {
        wordList.add(word);
    }

    public Range getRange() {
        return range;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        // builder.append("Range: ").append(range.from).append("-").append(range.to);
        for (Word word : wordList) {
            builder.append(word).append("\n");
        }
        return builder.toString();
    }

    @Override
    public int compareTo(Card o) {
        return this.range.to - o.range.to;
    }

    public static class Range {
        public int from;
        public int to;

        public Range(int from, int to) {
            this.from = from;
            this.to = to;
        }

        public static Range getRangeForValue(int value) {
            int from, to;
            if (value < 10) {
                from = 1;
                to = 10;
            } else if (value % 10 == 0) {
                to = (value / 10) * 10;
                from = to - 9;
            } else {
                from = (value / 10) * 10 + 1;
                to = from + 9;
            }
            return new Range(from, to);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Range)) return false;
            if (((Range) obj).from == from && ((Range) obj).to == to) return true;
            return false;
        }

        @Override
        public int hashCode() {
            int prime = 31;
            int result = 1;
            result = prime * result + from;
            result = prime * result + to;
            return result;
        }

        @Override
        public String toString() {
            return from + "-" + to;
        }
    }
}
