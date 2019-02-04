package org.rtijn.dukecourse.markov;
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
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
		int index = myRandom.nextInt(myText.length-1);  // random word to start with
		String key = myText[index];
		sb.append(key);
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key);
		    if (follows.size() == 0) {
		        break;
		    }
//			System.out.println(key + ": " + follows);
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = next;
		}
		
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(String key) {
	    ArrayList<String> follows = new ArrayList<String>();
	    int pos = 0;
	    while (true) {
	    	int next = indexOf(myText, key, pos);
	    	if (next == -1) {
	    		break;
			}
	    	if (next == myText.length - 1) {
	    		break;
			}
	    	follows.add(myText[next + 1]);
	    	pos = next + 1;
		}
	    return follows;
    }

    private int indexOf(String[] words, String target, int start) {
		for (int i = start; i < words.length ; i++) {
			if (words[i].equals(target)) {
				return i;
			}
		}
		return -1;
	}

	public void testIndexOf() {
		String testString = "this is just a test yes this is a simple test";
		this.setTraining(testString);
		System.out.println("this at 0: " + this.indexOf(myText, "this", 0));
		System.out.println("this at 3: " + this.indexOf(myText, "this", 3));
		System.out.println("frog at 0: " + this.indexOf(myText, "frog", 0));
		System.out.println("frog at 5: " + this.indexOf(myText, "frog", 5));
		System.out.println("simple at 2: " + this.indexOf(myText, "simple", 2));
		System.out.println("test at 5: " + this.indexOf(myText, "test", 5));
	}

}
