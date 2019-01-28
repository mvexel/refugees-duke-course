package org.rtijn.dukecourse.markov;

import java.util.ArrayList;
import java.util.Random;

public class MarkovWord implements IMarkovModel {

    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public MarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom.setSeed(seed);
    }

    @Override
    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - numWords);  // random word to start with
        WordGram w = new WordGram(myText, index, myOrder);
        sb.append(w.toString());
        sb.append(" ");
        for(int k=0; k < numWords - myOrder; k++){
            ArrayList<String> follows = getFollows(w);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            w = w.shiftAdd(next);
        }
        return sb.toString().trim();
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public int indexOf(String[] words, WordGram target, int start) {
        for (int i = start; i < words.length - target.length() ; i++) {
            WordGram candidate = new WordGram(words, i, target.length());
            if (candidate.equals(target)) { return i; }
        }
        return -1;
    }

    public ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> result = new ArrayList<>();
        int pos = 0;
        while (true) {
            pos = indexOf(myText, kGram, pos);
            if (pos == -1) {
                break;
            }
            result.add(myText[pos + kGram.length() + 1]);
            pos = pos + myOrder + 1;
        }
        return result;
    }

}
