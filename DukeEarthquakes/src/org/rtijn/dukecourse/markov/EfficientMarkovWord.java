package org.rtijn.dukecourse.markov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovWord implements IMarkovModel {

    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> followersMap = new HashMap<>();

    public EfficientMarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom.setSeed(seed);
    }

    private void buildMap() {
        for (int i = 0; i < myText.length - myOrder + 1; i++) {
            WordGram w = new WordGram(myText, i, myOrder);
            if (followersMap.containsKey(w)) {
                break;
            }
            ArrayList<String> result = new ArrayList<>();
            int pos = 0;
            while (true) {
                pos = indexOf(myText, w, pos);
                if (pos == -1) {
                    break;
                }
                result.add(myText[pos + w.length()]);
                pos = pos + myOrder + 1;
            }
            followersMap.put(w, result);
        }
    }

    @Override
    public String getRandomText(int numWords) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);  // random word to start with
        WordGram w = new WordGram(myText, index, myOrder);
        sb.append(w.toString());
        sb.append(" ");
        for (int k = 0; k < numWords - myOrder; k++) {
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

    public void setTraining(String text) {
        myText = text.split("\\s+");
        buildMap();
    }

    public int indexOf(String[] words, WordGram target, int start) {

        /* The indexOf method has three parameters, a String array of all the words
        in the training text named words, a WordGram named target, and an integer
        named start indicating where to start looking for a WordGram match in words.
        This method should return the first position from start that has words in the
        array words that match the WordGram target. If there is no such match then return -1. */

        for (int i = start; i < words.length - target.length(); i++) {
            WordGram candidate = new WordGram(words, i, target.length());
            if (candidate.equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<String> getFollows(WordGram kGram) {
        return followersMap.get(kGram);
    }

    public void printHashMapInfo() {
        if (followersMap.size() < 10) {
            System.out.println(followersMap);
        }
        System.out.println("number of keys: " + followersMap.size());
        int max = 0;
        ArrayList<WordGram> maxGrams = new ArrayList<>();
        for (WordGram w : followersMap.keySet()) {
            int localMax = followersMap.get(w).size();
            if (max < localMax) {
                max = localMax;
            }
        }
        for (WordGram w : followersMap.keySet()) {
            int thisSize = followersMap.get(w).size();
            if (thisSize == max) {
                maxGrams.add(w);
            }
        }
        System.out.println("largest followers list has length " + max);
        System.out.println("wordgram with largest follower is " + maxGrams);
    }

}
