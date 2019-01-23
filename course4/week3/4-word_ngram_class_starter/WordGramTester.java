import java.util.*;
import edu.duke.*;

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

    public void wordGramShiftTester() {
        String source = "this is a test this is a test this is a test of words";        
        String[] words = source.split("\\s+");
        ArrayList<WordGram> list = new ArrayList<WordGram>();
        int size = 4;
        int index = 0;
        WordGram wg = new WordGram(words,index,size);
        System.out.println("checking "+ wg);
        for (index = size; index < words.length; index++) {
            System.out.println("index: " + index);
            WordGram shifted = wg.shiftAdd(words[index]);
            System.out.println("new word: '" + words[index] + "', new WordGram: '" + shifted + "'");
            wg = shifted;
        }
    }

    public void MarkovWordTester() {
        // FileResource fr = new FileResource(); 
        // String myText = fr.asString();
        String myText = "When I find myself in times of trouble Mother Mary comes to me Speaking words of wisdom, let it be And in my hour of darkness She is standing right in front of me Speaking words of wisdom, let it be";
        int seed = 42;
        int length = 100;
        for (int order = 0; order < 5; order++) {
            System.out.println("Running MarkovWord with order " + order);
            MarkovWord mw = new MarkovWord(order);
            mw.setRandom(seed);
            mw.setTraining(myText);
            String randomText = mw.getRandomText(length);
            System.out.println(randomText);
        }
    }
}


