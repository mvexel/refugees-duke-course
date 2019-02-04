package org.rtijn.dukecourse.markov;

import java.util.*;

public class WordGramTester {
	public void testWordGram(){
		String source = "this is a test this is a test this is a test of words";
		String[] words = source.split("\\s+");
		int size = 4;
		for(int index = 0; index <= words.length - size; index += 1) {
			WordGram wg = new WordGram(words,index,size);
			System.out.println(index+"\t"+wg.length()+"\t"+wg);
		}
	}
	
	public void testWordGramEquals(){
		String source = "this is a test this is a test this is a test of words";
		String[] words = source.split("\\s+");
		ArrayList<WordGram> list = new ArrayList<WordGram>();
		int size = 4;
		for(int index = 0; index <= words.length - size; index += 1) {
			WordGram wg = new WordGram(words,index,size);
			list.add(wg);
		}
		WordGram first = list.get(0);
		System.out.println("checking "+first);
		for(int k=0; k < list.size(); k++){
			//if (first == list.get(k)) {
			  if (first.equals(list.get(k))) {
				System.out.println("matched at "+k+" "+list.get(k));
			}
		}
	}

	public void testShiftAdd() {
		String[] testArray = {"this", "is", "just", "a", "test"};
		WordGram w = new WordGram(testArray, 0, testArray.length);
		String newWord = "understood";
		System.out.println("original wordgram: " + w);
		System.out.println("extra word to shift: " + newWord);
		WordGram w1 = w.shiftAdd(newWord);
		System.out.println(w1);
	}

	public void testIndexOf() {
		String[] testArray = {"this", "is", "just", "a", "test"};
		WordGram w = new WordGram(testArray, 0, testArray.length);

	}

	public static void main(String[] args) {
		WordGramTester tester = new WordGramTester();
		tester.testShiftAdd();
		tester.testWordGram();
		tester.testWordGramEquals();
	}
}
