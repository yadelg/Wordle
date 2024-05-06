package finalProject;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Random;

public class Word {
	
	private String answer;
	
 public Word() {
	 this.answer = createWord();
 }
 
 public String getWord() {
	 return answer;
 }
 
 //Method checks if a guess is a word or not by checking if its in the list of possible words.
 public static boolean isWord(String guess) {
	 Wordlist list = new Wordlist();
	 if (list.getListofWords().contains(guess)) {
		 return true;
	 }
	 return false;
 }


 /*
  * Creates a word by scanning a random amount of lines within the file and stopping at the randomly
  * generated number.
  */
 private String createWord() {
	 answer = "";
	 Random rand = new Random();
	 Scanner sc;
	try {
		
		File file=new File("C:\\Users\\gbky2\\eclipse-workspace\\finalProject\\src\\finalProject\\AllWords\\");
        sc = new Scanner(file);
		int wordPosition = rand.nextInt(2309);
		//loop through until last word
		for(int i = 0; i < wordPosition; i++) {
			 if (i == wordPosition - 1) {
				 //update answer only if at the last word
				 answer = sc.nextLine();
			 }
			 else {
				 sc.nextLine();
			 }
		}
		
		answer = answer.substring(3, 8);
	} 
	
	catch (IOException e) {
		e.printStackTrace();
	}
	

	 return answer;
 }
 
 
 //Method finds the amount of times one letter appears in the word.
 public int getOccurences(String letter) {
	 int num = 0;
	 for(int i = 0; i < answer.length(); i++) {
		 if (String.valueOf(answer.charAt(i)).equals(letter)) {
			 num++;
		 }
	 }
	 return num;
 }

 
 /*
  *Below is the code for the main Wordle algorithm.
  *Essentially, the program takes into account the amount of letters each has and how many time each
  *letter pops up through a hashmap. After storing each letter as the key with the occurrences of each
  *letter as the return value, the second loop finds all the letters in the correct spot (green letters)
  *and decrements the amount of occurrences by one. The finalAnswer array at the same time makes the
  *corresponding number equal to 0, which signifies a green letter (if the letter is not green, the value is
  *stored as 2 instead). The third loop accounts for any yellow positions keeping in mind the amount of
  *Occurrences of each letter (and how many times they've already been selected green or yellow).
  *
  * Example 
  * Guess = sissy
  * Answer = sadly
  * 
  * Would return: [0, 2, 2, 2, 2]
  * Instead of: [0, 2, 1, 1, 2] as "s", only appears once in the word
  * 
  * 1 = yellow, 0 = green, 2 = gray.
  */
public ArrayList<Integer> correct(String guess) {
     HashMap<String, Integer> initialGuess = new HashMap<String, Integer>(); 
     ArrayList<Integer> finalAnswer = new ArrayList<Integer>();
	 
     //initial loop (storing letters and occurrences into hashmap).
	 for (int i = 0; i < guess.length(); i++) {
		 String current = String.valueOf(guess.charAt(i));
		 initialGuess.put(current, getOccurences(String.valueOf(guess.charAt(i))));
	 }
	 
	 //Gets all the green letters.
	 for (int i = 0; i < guess.length(); i++) {
		 String current = String.valueOf(guess.charAt(i));
		 if (current.equals(String.valueOf(String.valueOf(answer.charAt(i))))) {
			 finalAnswer.add(0);
				 initialGuess.replace(current, initialGuess.get(current) - 1);		 
		 }
		 else {
			 finalAnswer.add(2);
		 }
	 }
	 
	 //Gets the yellow letters.
	 for (int i = 0; i < guess.length(); i++) {
		 String current = String.valueOf(guess.charAt(i));
		 if (initialGuess.get(current) >= 1 && finalAnswer.get(i) != 0) {
			 finalAnswer.set(i, 1);
			 initialGuess.replace(current, initialGuess.get(current)- 1);
			}
		}
	 
	return finalAnswer; 
 }
 
}
