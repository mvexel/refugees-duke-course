
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        return String.join(" ", myWords);
    }

    public boolean equals(Object o) {
        final WordGram other = (WordGram) o;
        if (other.length() != this.length()) {
            return false;
        }
        boolean isEqual = false;
        for (int i = 0; i < length(); i++) {
            isEqual = isEqual || this.wordAt(i).equals(other.wordAt(i));
        }
        return isEqual;
    }

    public WordGram shiftAdd(String word) { 
        // Initialize String array to hold the new Wordgram words
        String[] otherWords = new String[myWords.length];
        // Add words 
        for (int i = 0; i < myWords.length; i++) {
            otherWords[i] = myWords[i+1];
            System.out.println("otherWords added: " + otherWords[i] + " at position " + i);
        }
        otherWords[otherWords.length - 1] = word;
        WordGram out = new WordGram(otherWords, 0, otherWords.length);
        return out;
    }

}