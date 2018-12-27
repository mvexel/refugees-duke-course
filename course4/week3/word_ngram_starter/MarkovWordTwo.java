
/**
 * Markov word prediction, order 2
 * 
 * @author Martijn van Exel
 * @version 0.0.1
 */

import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;

    public MarkovWordTwo() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index + 1];
        sb.append(key1 + " " + key2);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key1, key2);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            // System.out.println("Keys: '" + key1 + "', '" + key2 + "', follows: " + follows);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
            // System.out.println("keys are now: '" + key1 + "', '" + key2 + "'");
        }

        return sb.toString().trim();
    }

    public void testIndexOf() {
        setTraining("this is just a test yes this is a simple test");
        String word1 = "this";
        String word2 = "is";
        int pos = 2;
        int found = indexOf(myText, word1, word2, pos);
        System.out.println(word1 + " " + word2 + " found at " + found + " starting at " + pos);
    }

    public void testGetFollows() {
        setTraining("this is just a test yes this is a simple test");
        String word1 = "just";
        String word2 = "a";
        ArrayList<String> follows = getFollows(word1, word2);
        System.out.println(word1 + " " + word2 + " followers:  " + follows);
    }

    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length) {
            int start = indexOf(myText, key1, key2, pos);
            if (start == -1) {
                break;
            }
            if (start >= myText.length - 2) {
                break;
            }
            String next = myText[start + 2];
            follows.add(next);
            pos = start + 2;
        }
        return follows;
    }

    private int indexOf(String[] words, String target1, String target2, int start) {
        for (int i = start; i < words.length - 1; i++) {
            if (words[i].equals(target1) && words[i+1].equals(target2)) {
                return i;
            }
        }
        return -1;
    }
}

