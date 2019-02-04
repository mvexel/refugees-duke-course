package org.rtijn.dukecourse.markov;

public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt " + index);
        }
        return myWords[index];
    }

    public int length() {
        return myWords.length;
    }

    public String toString() {
        String ret = String.join(" ", myWords);
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram that = (WordGram) o;
        return that.toString().equals(this.toString());
    }

    public WordGram shiftAdd(String word) {
        String[] thatWords = new String[myWords.length];
        System.arraycopy(myWords, 1, thatWords, 0, myWords.length - 1);
        thatWords[thatWords.length - 1] = word;
        WordGram out = new WordGram(thatWords, 0, thatWords.length);
        return out;
    }
}