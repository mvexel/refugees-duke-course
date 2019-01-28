package org.rtijn.dukecourse.markov;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import edu.duke.*;


public class MarkovRunner {

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}


	public void runMarkovZero() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovZero markov = new MarkovZero();
		markov.setRandom(88);
		markov.setTraining(st);
		for (int k = 0; k < 3; k++) {
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}


	public void runMarkovOne() throws IOException {
//		FileResource fr = new FileResource();
//		String st = fr.asString();
		String st = readFile("data/confucius.txt", StandardCharsets.UTF_8);
		st = st.replace('\n', ' ');
//		String st = "this is a test yes this is a test.";
		MarkovOne markov = new MarkovOne();
		markov.setRandom(273);
		markov.setTraining(st);
		for (int k = 0; k < 3; k++) {
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}


	public void runMarkovFour() throws IOException {
		String st = readFile("data/confucius.txt", StandardCharsets.UTF_8);
		st = st.replace('\n', ' ');
//		String st = "this is a test yes this is a test a simple test that can tell us about the rest.";
		MarkovFour markov = new MarkovFour();
		markov.setRandom(371);
		markov.setTraining(st);
		for (int k = 0; k < 3; k++) {
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}

	public void runMarkovModel() throws Exception {
		String st = readFile("data/confucius.txt", StandardCharsets.UTF_8);
		st = st.replace('\n', ' ');
		MarkovModel markov = new MarkovModel(8);
		markov.setRandom(365);
		markov.setTraining(st);
		for (int k = 0; k < 3; k++) {
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}

	public void runMarkovWord() throws Exception {
		String st = readFile("data/confucius.txt", StandardCharsets.UTF_8);
		st = st.replace('\n', ' ');
		MarkovWord markov = new MarkovWord(3);
		markov.setTraining(st);
		markov.setRandom(643);
		String text = markov.getRandomText(500);
		printOut(text);
	}


	private void printOut(String s) {
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for (int k = 0; k < words.length; k++) {
			System.out.print(words[k] + " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}


	public static void main(String[] args) throws Exception {
		MarkovRunner runner = new MarkovRunner();
//		runner.runMarkovZero();
//		runner.runMarkovOne();
//		runner.runMarkovFour();
//		runner.runMarkovModel();
		runner.runMarkovWord();
	}
}
