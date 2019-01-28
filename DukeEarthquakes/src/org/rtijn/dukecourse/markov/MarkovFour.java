package org.rtijn.dukecourse.markov;

import java.util.ArrayList;

/**
 * Write a description of class MarkovZero here.
 *
 * @author Duke Software
 * @version 1.0
 */

import java.util.Random;


public class MarkovFour extends AbstractMarkovModel implements IMarkovModel {

	public MarkovFour() {
		myRandom = new Random();
	}


	public void setRandom(int seed) {
		myRandom = new Random(seed);
	}


	public void setTraining(String s) {
		myText = s.trim();
	}


	public String getRandomText(int numChars) {
		if (myText == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length() - 4);
		String key = myText.substring(index, index + 4);
//		System.out.println("First key is " + key);
		sb.append(key);
		for (int k = 0; k < numChars - 4; k++) {
//			System.out.println("going to find followers for " + key);
			// get followers for key
			ArrayList<String> followers = this.getFollows(key);
			if (followers.size() == 0) {
				// break if none found
				break;
			}
			// select random item from follower list
			index = myRandom.nextInt(followers.size());
			String selectedFollower = followers.get(index);
			// and append it to the stringbuilder
			sb.append(selectedFollower);
			key = key.substring(1) + selectedFollower;
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return "Markov Model of order 4";
	}
}
